package com.pomoravskivrbaci.cinemareservations.controller;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.model.Projection;
import com.pomoravskivrbaci.cinemareservations.model.Repertoire;
import com.pomoravskivrbaci.cinemareservations.service.HallService;
import com.pomoravskivrbaci.cinemareservations.service.PeriodService;
import com.pomoravskivrbaci.cinemareservations.service.ProjectionService;
import com.pomoravskivrbaci.cinemareservations.service.RepertoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "projection/{id}", method = RequestMethod.PATCH)
    private ResponseEntity editProjectionInfo(@PathVariable("id")Long id, @RequestBody Projection projection) {
        projectionService.setProjectionInfoById(id, projection.getName(), projection.getActors(), projection.getGenre(), projection.getDescription(), projection.getDirectorName(), projection.getRating(), projection.getPrice());
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "repertoire/{rep_id}/projection", method = RequestMethod.POST)
    private ResponseEntity<Projection> create(@RequestBody Projection projection, @PathVariable("rep_id")Long repId) {
        List<Hall> choosenHalls = new ArrayList<>();
        List<Period> choosenPeriods = new ArrayList<>();
        Repertoire repertoire = repertoireService.findById(repId);
        projection.getHalls().forEach(hall ->
            choosenHalls.add(hallService.findById(hall.getId()))
        );
        projection.getPeriods().forEach(period ->
            choosenPeriods.add(periodService.findById(period.getId()))
        );
        choosenPeriods.forEach(period -> period.setProjection(projection));
        projection.setHalls(choosenHalls);
        projection.setPeriods(choosenPeriods);
        repertoire.addProjection(projection);
        projection.addRepertoire(repertoire);
        //repertoireService.saveOrUpdate(repertoire);
        return new ResponseEntity<>(projectionService.saveOrUpdate(projection), HttpStatus.OK);
    }

}
