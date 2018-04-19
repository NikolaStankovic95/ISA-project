package com.pomoravskivrbaci.cinemareservations.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.Reservation;
import com.pomoravskivrbaci.cinemareservations.model.User;


@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	
	@Async
	public void inviteFriend(User user,User friend,Reservation reservation) throws MailException,InterruptedException{
		Thread.sleep(10000);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Sistem bioskopa/pozorišta");
		mail.setText("Vas prijatelj "+friend.getName()+" "+friend.getSurname()+" Vas poziva na projekciju "+reservation.getProjection().getName()
				+" kliknite na sledeći link http://localhost:9000/invitationController/invitation/"+reservation.getId()+" i odgovorite mu da li dolazite");
		
		javaMailSender.send(mail);

	}
	
	@Async
	public void sendNotificaitionAsync(User user) throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
		Thread.sleep(10000);
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Sistem bioskopa/pozorišta");
		mail.setText("Kliknite na sledeći link http://localhost:9000/registrationController/activation/"+user.getId());
		
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}
	@Async
	public void notifyOwner(User owner, Reservation reservation) {
		// TODO Auto-generated method stub
	     LocalTime time = LocalDateTime.ofInstant(reservation.getPeriod().getDate().toInstant(), ZoneId.systemDefault()).toLocalTime();
	     LocalDate date = reservation.getPeriod().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	     System.out.println("Slanje emaila...");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(owner.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Sistem bioskopa/pozorišta");
		mail.setText("Uspešno ste rezervisali "+reservation.getSeat().getRegNumber()+" mesto za projekciju "+reservation.getProjection().getName()
				+" u "+reservation.getInstitution().getName()+" na dan "+" "+date+" "+time+ " u sali "+
				reservation.getHall().getName());
		
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	
	}
	@Async
	public void notifyAcceptedBider(User owner, Ad foundedAd){
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(owner.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Sistem bioskopa/pozorišta");
		mail.setText("Vaša ponuda za oglas "+foundedAd.getName()+" je prihvacena");
		
		javaMailSender.send(mail);
		System.out.println("Obavesten dobitnik");
	}
	@Async
	public void notifyRefussedBider(List<User> rejected, Ad foundedAd){
		for(User user : rejected){
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Sistem bioskopa/pozorišta");
		mail.setText("Vaša ponuda za oglas "+foundedAd.getName()+" nažalsot nije prihvacena");
		
		javaMailSender.send(mail);
		}
		System.out.println("Obavesteni gubitnik");
	}

	@Async
	public void notifyOwnersOfDeletedReservation(List<User> owners, String projectionName) {
		owners.forEach(owner -> {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(owner.getEmail());
			mail.setFrom(env.getProperty("spring.mail.username"));
			mail.setSubject("Sistem bioskopa/pozorišta");
			mail.setText("Projekcija " + projectionName + "je otkazana pa vam je rezervacija sedista ponistena, novac ce vam biti vracen.");
			javaMailSender.send(mail);
		});
	}

}
