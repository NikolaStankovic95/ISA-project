package com.pomoravskivrbaci.cinemareservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages={
"com.pomoravskivrbaci.cinemareservations"})

@EnableAsync
public class CinemareservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemareservationsApplication.class, args);
	}
}
