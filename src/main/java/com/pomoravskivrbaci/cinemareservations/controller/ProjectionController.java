package com.pomoravskivrbaci.cinemareservations.controller;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.model.Projection;
import com.pomoravskivrbaci.cinemareservations.model.Repertoire;
import com.pomoravskivrbaci.cinemareservations.service.HallService;
import com.pomoravskivrbaci.cinemareservations.service.PeriodService;
import com.pomoravskivrbaci.cinemareservations.service.ProjectionService;
import com.pomoravskivrbaci.cinemareservations.service.RepertoireService;
import com.pomoravskivrbaci.cinemareservations.validation.ProjectionValidator;
import com.pomoravskivrbaci.cinemareservations.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectionController {

    @Autowired
    private ProjectionService projectionService;

    @Autowired
    private HallService hallService;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private RepertoireService repertoireService;

    @RequestMapping(value = "projection/{id}", method = RequestMethod.GET)
    private String showProjectionPage(@PathVariable("id")Long id, HttpServletRequest request) {
        Projection projection = projectionService.findById(id);
        request.setAttribute("projection", projection);
        return "forward:/projection_profile.jsp";
    }

    @RequestMapping(value = "projection/{id}", method = RequestMethod.PATCH)
    private ResponseEntity editProjectionInfo(@PathVariable("id")Long id, @RequestBody Projection projection) {
        Validator projectionValidator = new ProjectionValidator(projection);
        if(!projectionValidator.validate()) {
            System.out.println(projectionValidator.getResults());
            return new ResponseEntity(projectionValidator.getResults(), HttpStatus.BAD_REQUEST);
        }
        projectionService.setProjectionInfoById(id, projection.getName(), projection.getActors(), projection.getGenre(), projection.getDescription(), projection.getDirectorName(), projection.getRating(), projection.getPrice());
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "repertoire/{rep_id}/projection", method = RequestMethod.POST)
    private ResponseEntity<Projection> create(@RequestBody Projection projection, @PathVariable("rep_id")Long repId) {
        Validator projectionValidator = new ProjectionValidator(projection);
        List<Hall> chosenHalls = new ArrayList<>();
        List<Period> chosenPeriods = new ArrayList<>();
        Repertoire repertoire = repertoireService.findById(repId);
        projection.getHalls().forEach(hall ->
            chosenHalls.add(hallService.findById(hall.getId()))
        );
        projection.getPeriods().forEach(period ->
            chosenPeriods.add(periodService.findById(period.getId()))
        );
        chosenPeriods.forEach(period -> period.setProjection(projection));
        projection.setHalls(chosenHalls);
        projection.setPeriods(chosenPeriods);
        repertoire.addProjection(projection);
        projection.addRepertoire(repertoire);
        if(!projectionValidator.validate()) {
            System.out.println(projectionValidator.getResults());
            return new ResponseEntity(projectionValidator.getResults(), HttpStatus.BAD_REQUEST);
        }
        //repertoireService.saveOrUpdate(repertoire);
        return new ResponseEntity<>(projectionService.saveOrUpdate(projection), HttpStatus.OK);
    }

    @RequestMapping(value = "/projection/{id}", method = RequestMethod.DELETE)
    private ResponseEntity deleteProjection(@PathVariable("id")Long id) {
        Projection projection = projectionService.findById(id);
        projection.getPeriods().forEach(period -> period.setProjection(null));
        projection.getRepertoires().forEach(repertoire -> repertoire.getProjections().removeIf(projection1 -> projection1.getId().equals(projection.getId())));
        projectionService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
