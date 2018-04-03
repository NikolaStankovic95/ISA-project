package com.pomoravskivrbaci.cinemareservations.messaging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/*
 * Izdvojena komponenta koja sadrzi atribut SimpMessagingTemplate
 * cije metode vrse slanje poruka sa servera na pretplacene klijente.
 */
@Component
public class Producer {

	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	/*
	 * Implementira SimpMessagesSendingOperations klasu koja sadrzi metode za slanje poruka korisnicima.
	 */
	@Autowired
	private SimpMessagingTemplate template;
	
	public void sendMessageTo(String topic, Long message) {
		/*
		 * metoda convertAndSend() vrsi slanje poruke sa servera na klijente koji
		 * su se pretplatili na odredjeni topic.
		 */
		System.out.println("Producer");
		this.template.convertAndSend("/topic/" + topic, message);
	}

}
