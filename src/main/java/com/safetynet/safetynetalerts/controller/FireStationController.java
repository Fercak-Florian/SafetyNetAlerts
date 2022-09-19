package com.safetynet.safetynetalerts.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.IFireStationService;

@RestController
public class FireStationController {
	/* APPELER DES METHODES DE LA CLASSE SERVICE */

	@Autowired
	private IFireStationService fireStationService;

	@GetMapping("/firestations")
	public List<FireStation> getFireStationFromService() {
		return fireStationService.getFireStation();
	}

	@PostMapping("/firestation")
	public ResponseEntity<FireStation> postFirestationController(@RequestBody FireStation firestation) {
		FireStation fs = fireStationService.addFirestationService(firestation);
		if (Objects.isNull(fs)) {
			return ResponseEntity.noContent().build();
		} else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/")
					.buildAndExpand(firestation.getStationNumber()).toUri();
			return ResponseEntity.created(location).build();
		}
	}
}