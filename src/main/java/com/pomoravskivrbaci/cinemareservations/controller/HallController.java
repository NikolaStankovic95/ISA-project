package com.pomoravskivrbaci.cinemareservations.controller;

import com.pomoravskivrbaci.cinemareservations.model.*;
import com.pomoravskivrbaci.cinemareservations.service.HallSegmentService;
import com.pomoravskivrbaci.cinemareservations.service.HallService;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionService;
import com.pomoravskivrbaci.cinemareservations.validation.HallValidator;
import com.pomoravskivrbaci.cinemareservations.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Date;
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
        Validator hallValidator = new HallValidator(newHall);
        if(!hallValidator.validate()) {
            System.out.println(hallValidator.getResults());
            return new ResponseEntity(hallValidator.getResults(), HttpStatus.BAD_REQUEST);
        }
        Institution institution = institutionService.findById(instId);
        newHall.getHallSegments().forEach((hallSegment -> {
            for(int i = 0; i < hallSegment.getNumberOfRows(); i++) {
                for (int j = 0; j < hallSegment.getNumberOfColumns(); j++) {
                    Seat seat = new Seat();
                    seat.setRegNumber((i+1) + ", " + (j+1));
                    seat.setHallSegment(hallSegment);
                    hallSegment.addSeat(seat);
                    hallSegment.setHall(newHall);
                }
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
        Validator hallValidator = new HallValidator(newHall);
        if(!hallValidator.validate()) {
            System.out.println(hallValidator.getResults());
            return new ResponseEntity(hallValidator.getResults(), HttpStatus.BAD_REQUEST);
        }
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
            if (period.getProjection() == null && period.getDate().after(new Date())) {
                freePeriods.add(period);
            }
        }
        return new ResponseEntity<>(freePeriods, HttpStatus.OK);
    }
}
