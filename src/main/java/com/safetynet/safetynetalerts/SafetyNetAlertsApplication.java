package com.safetynet.safetynetalerts;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.safetynetalerts.repository.GlobalRepository;
import com.safetynet.safetynetalerts.repository.IGlobalRepository;

@SpringBootApplication
public class SafetyNetAlertsApplication {
	
	private static IGlobalRepository globalRepository;
	
	public static IGlobalRepository getGlobalRepository() {
		return globalRepository;
	}

	public static void main(String[] args) throws IOException {
		globalRepository = new GlobalRepository();
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
		// FireStationRepositoryImpl fireStationRepositoryImpl = new
		// FireStationRepositoryImpl();
		// fireStationRepositoryImpl.addFireStation();
	}

	public GlobalRepository methodeVariableGlobale() throws IOException  {
		GlobalRepository globalRepository = new GlobalRepository();
		return globalRepository;
	}
}
