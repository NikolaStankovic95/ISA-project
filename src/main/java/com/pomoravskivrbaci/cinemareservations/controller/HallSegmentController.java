package com.pomoravskivrbaci.cinemareservations.controller;

import com.pomoravskivrbaci.cinemareservations.model.Reservation;
import com.pomoravskivrbaci.cinemareservations.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Date;

import java.util.List;

@Controller
public class HallSegmentController {
    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value = "/hall_segment/{id}/check_reservation", method = RequestMethod.HEAD)
    private ResponseEntity checkIfSegmentHasReservatedSeat(@PathVariable("id")Long id) {
        List<Reservation> allReservations = reservationService.findAll();
        for (Reservation reservation : allReservations) {
            if (reservation.getSeat().getHallSegment().getId() == id && reservation.getPeriod().getDate().after(new Date())) {
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
