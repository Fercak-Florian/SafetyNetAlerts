package com.safetynet.safetynetalerts.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FireStationServiceImpl;
import com.safetynet.safetynetalerts.service.IFireStationService;

@RestController
public class FireStationController {
	// APPELER DES METHODES DE LA CLASSE SERVICE
	@Autowired
	private IFireStationService fireStationService;

	@GetMapping("/firestations")
	public List<FireStation> getFireStationFromService() throws IOException {
		return fireStationService.getFireStation();
	}

	@PostMapping("/request")
	public FireStation postController(@RequestBody FireStation fireStation) {
		return fireStation;
	}

}