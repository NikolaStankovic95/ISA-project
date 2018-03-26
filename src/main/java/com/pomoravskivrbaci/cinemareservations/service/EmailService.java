package com.pomoravskivrbaci.cinemareservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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


}
