package com.pomoravskivrbaci.cinemareservations.controller;

import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.InstitutionRating;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionRatingService;
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
import javax.servlet.http.HttpSession;
import java.security.acl.LastOwnerException;

@Controller
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private InstitutionRatingService institutionRatingService;

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH)
    private ResponseEntity editInstitutionDescription(@PathVariable("id")Long id, @RequestBody Institution inst) {
        institutionService.setInstitutionInfoById(id, inst.getName(), inst.getAddress(), inst.getDescription());
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    private String showInstitutionProfile(@PathVariable("id")Long id, HttpServletRequest request) {
        Institution institution = institutionService.findById(id);
        request.setAttribute("institution", institution);
        return "forward:/institution_profile.jsp";
    }

    @RequestMapping(value="/{id}/rate", method = RequestMethod.POST)
    private ResponseEntity rate(@PathVariable("id")Long id, @RequestBody InstitutionRating institutionRating, HttpSession session) {
        User loggedUser = (User)session.getAttribute("loggedUser");
        if(loggedUser == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        for(InstitutionRating instRating : loggedUser.getInstitutionRatings()) {
            if(instRating.getInstitution().getId().equals(id)) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        Institution institution = institutionService.findById(id);
        institutionRating.setInstitution(institution);
        institution.addRating(institutionRating);
        institutionRating.setUser(loggedUser);
        loggedUser.addInstitutionRating(institutionRating);
        institutionRatingService.saveOrUpdate(institutionRating);
        return new ResponseEntity(HttpStatus.OK);
    }

}
