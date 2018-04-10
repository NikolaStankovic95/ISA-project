package com.pomoravskivrbaci.cinemareservations.controller;

import com.pomoravskivrbaci.cinemareservations.model.*;
import com.pomoravskivrbaci.cinemareservations.service.HallSegmentService;
import com.pomoravskivrbaci.cinemareservations.service.HallService;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HallController {

    @Autowired
    private HallService hallService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private HallSegmentService hallSegmentService;

    @RequestMapping(value = "institution/{institution_id}/hall", method = RequestMethod.POST)
    private ResponseEntity create(@PathVariable("institution_id")Long instId, @RequestBody Hall newHall) {
        Institution institution = institutionService.findById(instId);
        newHall.getHallSegments().forEach((hallSegment -> {
            int numberOfSeats = hallSegment.getNumberOfRows() * hallSegment.getNumberOfColumns();
            for(int i = 0; i < numberOfSeats; i++) {
                Seat seat = new Seat();
                seat.setHallSegment(hallSegment);
                hallSegment.addSeat(seat);
                hallSegment.setHall(newHall);
            }
        }));
        newHall.setInstitution(institution);
        institution.getHalls().add(newHall);
        institutionService.saveOrUpdate(institution);
        Hall savedHall = hallService.saveOrUpdate(newHall);
        return new ResponseEntity(savedHall, HttpStatus.OK);
    }

    @RequestMapping(value = "/hall/{id}", method = RequestMethod.PATCH)
    private ResponseEntity editProjectionInfo(@PathVariable("id")Long id, @RequestBody Hall newHall) {
        Hall hall = hallService.findById(id);
        newHall.getHallSegments().forEach(hallSegment -> {
            hallSegment.setHall(hall);
            hallSegmentService.saveOrUpdate(hallSegment);
        });
        hall.setName(newHall.getName());
        hallService.saveOrUpdate(hall);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/hall/{id}", method = RequestMethod.GET)
    private ResponseEntity<Hall> getHallById(@PathVariable("id")Long id) {
        return new ResponseEntity<>(hallService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/hall/{id}/free_periods", method = RequestMethod.GET)
    private ResponseEntity<List<Period>> getFreePeriods(@PathVariable("id")Long id) {
        Hall hall = hallService.findById(id);
        List<Period> freePeriods = new ArrayList<>();
        for (Period period : hall.getPeriods()) {
            if (period.getProjection() == null) {
                freePeriods.add(period);
            }
        }
        return new ResponseEntity<>(freePeriods, HttpStatus.OK);
    }
}
