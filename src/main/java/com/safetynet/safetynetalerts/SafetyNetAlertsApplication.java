package com.safetynet.safetynetalerts;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.safetynetalerts.repository.FireStationRepository;

@SpringBootApplication
public class SafetyNetAlertsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
		FireStationRepository fireStationRepository = new FireStationRepository();
		fireStationRepository.addFireStation();
	}

}
