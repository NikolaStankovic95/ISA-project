package com.pomoravskivrbaci.cinemareservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.User;


@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	@Async
	public void sendNotificaitionAsync(User user) throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
		Thread.sleep(10000);
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Sistem bioskopa/pozorišta");
		mail.setText("Pozdrav " + user.getName() + ",\n\n.");
		mail.setText("Da bi ste se uspešno registrovali na sajt,morate aktivirati svoj nalog.");
		mail.setText("Kliknite na sledeći link http://localhost:9000/userController/activation/"+user.getId());
		
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}


}
