package com.pomoravskivrbaci.cinemareservations.controller;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.service.HallService;
import com.pomoravskivrbaci.cinemareservations.service.PeriodService;
import com.pomoravskivrbaci.cinemareservations.validation.PeriodValidator;
import com.pomoravskivrbaci.cinemareservations.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PeriodController {

    @Autowired
    private HallService hallService;

    @Autowired
    private PeriodService periodService;

    @RequestMapping(value = "/hall/{id}", method = RequestMethod.POST)
    private ResponseEntity editProjectionInfo(@PathVariable("id")Long hall_id, @RequestBody Period newPeriod) {
        Validator periodValidator = new PeriodValidator(newPeriod);
        Hall hall = hallService.findById(hall_id);
        newPeriod.addHall(hall);
        if(!periodValidator.validate()) {
            return new ResponseEntity(periodValidator.getResults(), HttpStatus.BAD_REQUEST);
        }
        hall.addPeriod(newPeriod);
        periodService.saveOrUpdate(newPeriod);
        hallService.saveOrUpdate(hall);
        return new ResponseEntity(HttpStatus.OK);
    }

}
