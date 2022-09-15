package com.safetynet.safetynetalerts;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.safetynetalerts.repository.GlobalRepository;

@SpringBootApplication
public class SafetyNetAlertsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
		// FireStationRepositoryImpl fireStationRepositoryImpl = new
		// FireStationRepositoryImpl();
		// fireStationRepositoryImpl.addFireStation();
		GlobalRepository globalRepository = new GlobalRepository();
	}

}
