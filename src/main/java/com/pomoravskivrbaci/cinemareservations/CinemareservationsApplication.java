package com.pomoravskivrbaci.cinemareservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
"com.pomoravskivrbaci.cinemareservations"})
public class CinemareservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemareservationsApplication.class, args);
	}
}
