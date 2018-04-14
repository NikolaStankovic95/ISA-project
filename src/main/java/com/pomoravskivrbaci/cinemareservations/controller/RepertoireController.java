package com.pomoravskivrbaci.cinemareservations.controller;

import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.Repertoire;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionService;
import com.pomoravskivrbaci.cinemareservations.service.RepertoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RepertoireController {

    @Autowired
    private RepertoireService repertoireService;
    @Autowired
    private InstitutionService institutionService;

    @RequestMapping(value="/repertoire/{id}", method = RequestMethod.PATCH)
    private ResponseEntity editRepertoireName(@PathVariable("id")Long id, @RequestBody Repertoire repertoire) {
        repertoireService.setRepertoireInfoById(id, repertoire.getName());
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/institution/{id}/repertoire", method = RequestMethod.POST)
    private ResponseEntity createRepertoirePage(@PathVariable("id")Long id, @RequestBody Repertoire newRepertoire) {
        Institution institution = institutionService.findById(id);
        newRepertoire.addInstitution(institution);
        institution.setRepertorie(newRepertoire);
        repertoireService.saveOrUpdate(newRepertoire);
        return new ResponseEntity(HttpStatus.OK);
    }

}
