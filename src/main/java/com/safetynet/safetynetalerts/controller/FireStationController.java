package com.safetynet.safetynetalerts.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	/* CRUD POUR FIRESTATIONS */

	@GetMapping("/firestations")
	public List<FireStation> getFireStationFromService() {
		return fireStationService.getFireStation();
	}

	@PostMapping("/firestation")
	public ResponseEntity<FireStation> postFirestation(@RequestBody FireStation firestation) {
		FireStation fs = fireStationService.addFirestationService(firestation);
		if (Objects.isNull(fs)) {
			return ResponseEntity.noContent().build();
		} else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/")
					.buildAndExpand(firestation.getStationNumber()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@PutMapping("/firestation")
	public ResponseEntity<FireStation> putFirestation(@RequestBody FireStation firestation) {
		FireStation fs = fireStationService.updateFirestationNumberService(firestation);
		if (Objects.isNull(fs)) {
			return ResponseEntity.noContent().build();
		} else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/")
					.buildAndExpand(firestation.getStationNumber()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@DeleteMapping("/firestation")
	public ResponseEntity<FireStation> deleteFirestation(@RequestBody FireStation firestation) {
		fireStationService.deleteFirestationService(firestation);
		return null;
	}
}