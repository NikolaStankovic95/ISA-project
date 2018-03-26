package com.pomoravskivrbaci.cinemareservations.controller;

import com.pomoravskivrbaci.cinemareservations.model.Repertoire;
import com.pomoravskivrbaci.cinemareservations.service.RepertoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "repertoire")
public class RepertoireController {

    @Autowired
    private RepertoireService repertoireService;

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH)
    private ResponseEntity editRepertoireName(@PathVariable("id")Long id, @RequestBody Repertoire repertoire) {
        repertoireService.setRepertoireInfoById(id, repertoire.getName());
        return new ResponseEntity(HttpStatus.OK);
    }

}
