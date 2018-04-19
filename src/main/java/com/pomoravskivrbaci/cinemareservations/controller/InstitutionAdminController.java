package com.pomoravskivrbaci.cinemareservations.controller;

import java.util.Calendar;
import java.util.Date;
import com.pomoravskivrbaci.cinemareservations.model.*;
import com.pomoravskivrbaci.cinemareservations.service.*;
import com.pomoravskivrbaci.cinemareservations.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/inst_admin")
public class InstitutionAdminController {
    @Autowired
    InstitutionService institutionService;
    @Autowired
    RepertoireService repertoireService;
    @Autowired
    ProjectionService projectionService;
    @Autowired
    HallService hallService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    PeriodService periodService;
    @Autowired
    SeatService seatService;
    @Autowired
    HallSegmentService hallSegmentService;

    @RequestMapping(method = RequestMethod.GET)
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
        List<Reservation> allReservations = reservationService.findAll();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Date dateBefore7Days = cal.getTime();
        cal.add(Calendar.DATE, -23);
        Date dateBefore30Days = cal.getTime();
        List<Reservation> dailyReservations = allReservations.stream()
                .filter(reservation -> reservation.getOwner() != null && DateUtils.isToday(reservation.getPeriod().getDate()))
                .collect(Collectors.toList());
        List<Reservation> weeklyReservations = allReservations.stream()
                .filter(reservation -> reservation.getOwner() != null &&
                        reservation.getPeriod().getDate().before(new Date()) &&
                        reservation.getPeriod().getDate().after(dateBefore7Days))
                .collect(Collectors.toList());
        List<Reservation> monthlyReservations = allReservations.stream()
                .filter(reservation -> reservation.getOwner() != null &&
                        reservation.getPeriod().getDate().before(new Date()) &&
                        reservation.getPeriod().getDate().after(dateBefore30Days))
                .collect(Collectors.toList());
        request.setAttribute("institution", institution);
        request.setAttribute("dailyReservations", dailyReservations);
        request.setAttribute("weeklyReservations", weeklyReservations);
        request.setAttribute("monthlyReservations", monthlyReservations);
        return("forward:/inst_admin/inst_admin_institution.jsp");
    }

    @RequestMapping(value = "/institution/{inst_id}/repertoire/{rep_id}", method = RequestMethod.GET)
    private String getRepertoireInfo(@PathVariable("inst_id")Long instId, @PathVariable("rep_id")Long repId, HttpServletRequest request) {
        Institution institution = institutionService.findById(instId);
        Repertoire repertoire = repertoireService.findById(repId);
        request.setAttribute("repertoire", repertoire);
        request.setAttribute("institution", institution);
        return("forward:/inst_admin/inst_admin_repertoire.jsp");
    }

    @RequestMapping(value = "/institution/{id}/create_repertoire")
    private String createRepertoirePage(@PathVariable("id")Long id, HttpServletRequest request) {
        Institution institution = institutionService.findById(id);
        request.setAttribute("institution", institution);
        return("forward:/inst_admin/create_repertoire.jsp");
    }

    @RequestMapping(value = "/institution/{inst_id}/projection/{id}", method = RequestMethod.GET)
    private String getProjectionInfo(@PathVariable("id")Long id, @PathVariable("inst_id")Long instId, HttpServletRequest request) {
        Projection projection = projectionService.findById(id);
        Institution institution = institutionService.findById(id);
        request.setAttribute("projection", projection);
        request.setAttribute("institution", institution);
        return("forward:/inst_admin/inst_admin_projection.jsp");
    }

    @RequestMapping(value = "/hall/{id}", method = RequestMethod.GET)
    private String getHallInfo(@PathVariable("id")Long id, HttpServletRequest request) {
        Hall hall = hallService.findById(id);
        request.setAttribute("hall", hall);
        return("forward:/inst_admin/inst_admin_hall.jsp");
    }

    @RequestMapping(value = "/institution/{id}/create_hall")
    private String createHallPage(@PathVariable("id")Long id, HttpServletRequest request) {
        Institution institution = institutionService.findById(id);
        request.setAttribute("institution", institution);
        return("forward:/inst_admin/create_hall.jsp");
    }

    @RequestMapping(value = "/institution/{inst_id}/repertoire/{rep_id}/create_projection")
    private String createProjectionPage(@PathVariable("inst_id")Long instId, @PathVariable("rep_id")Long repId, HttpServletRequest request) {
        Institution institution = institutionService.findById(instId);
        Repertoire repertoire = repertoireService.findById(repId);
        request.setAttribute("institution", institution);
        request.setAttribute("repertoire", repertoire);
        return("forward:/inst_admin/create_projection.jsp");
    }

    @RequestMapping(value="/fast_reservation/{proj_id}", method = RequestMethod.GET)
    private String showFastReservationPage(@PathVariable("proj_id")Long projId, HttpServletRequest request) {
        Projection projection = projectionService.findById(projId);
        request.setAttribute("projection", projection);
        return("forward:/inst_admin/fast_reservations.jsp");
    }


    @RequestMapping(value="/fast_reservation/projection/{proj_id}/hall/{hall_id}/segment/{seg_id}/period/{per_id}/seat/{seat_id}", method = RequestMethod.POST)
    private ResponseEntity createFastReservation(@PathVariable("proj_id")Long projId, @PathVariable("hall_id")Long hallId, @PathVariable("seg_id")Long segId, @PathVariable("per_id")Long perId, @PathVariable("seat_id")Long seatId, @RequestParam("discount")int discount) {
        Hall hall = hallService.findById(hallId);
        Reservation newReservation = new Reservation();
        newReservation.setAccepted(true);
        newReservation.setHall(hall);
        newReservation.setHallSegment(hallSegmentService.findById(segId));
        newReservation.setInstitution(hall.getInstitution());
        newReservation.setPeriod(periodService.findById(perId));
        newReservation.setProjection(projectionService.findById(projId));
        newReservation.setSeat(seatService.findById(seatId));
        newReservation.setDiscount(discount);
        reservationService.save(newReservation);
        return new ResponseEntity(HttpStatus.OK);
    }
}
