package com.pomoravskivrbaci.cinemareservations.controller;

import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.model.UserRole;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/inst_admin")
public class InstitutionAdminController {
    @Autowired
    InstitutionService institutionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String chooseInstitution(HttpServletRequest request){
        /*User loggedUser = (User)request.getSession().getAttribute("loggedUser");
        if(loggedUser == null || loggedUser.getRole() != UserRole.INSTADMIN){
            return "redirect:/index.jsp";
        }*/

        List<Institution> institutions = institutionService.findAll();
        request.setAttribute("institutions", institutions);
        return("forward:/inst_admin/inst_admin_homepage.jsp");
    }

    @RequestMapping(value = "/institution/{id}", method = RequestMethod.GET)
    private String getInstitutionInfo(@PathVariable("id")Long id, HttpServletRequest request) {
        Institution institution = institutionService.findById(id);
        request.setAttribute("institution", institution);
        return("forward:/inst_admin/inst_admin_institution.jsp");
    }
}
