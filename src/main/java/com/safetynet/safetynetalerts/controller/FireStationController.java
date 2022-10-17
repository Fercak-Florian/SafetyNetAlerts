package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.safetynet.safetynetalerts.controller.PersonController.EmptyJsonResponse;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.IFireStationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FireStationController {

	@Autowired
	private IFireStationService fireStationService;

	/* CRUD POUR FIRESTATIONS */

	@GetMapping("/firestations")
	public List<FireStation> getFireStations() {
		log.info("Récuperation de toutes les casernes");
		return fireStationService.getFireStations();
	}

	@PostMapping("/firestation")
	public ResponseEntity<FireStation> postFireStation(@RequestBody(required = true) FireStation firestation) {
		if (StringUtils.isEmpty(firestation.getAddress()) && firestation.getStationNumber() == 0) {
			log.error("Impossible d'ajouter cette caserne");
			return ResponseEntity.badRequest().build();
		} else {
			List<FireStation> fireStations = fireStationService.addFireStation(firestation);
			if (fireStations.contains(firestation)) {
				log.info("La caserne suivante à été créee : {}", firestation);
				return ResponseEntity.status(HttpStatus.CREATED).body(firestation);
			} else {
				log.error("Erreur lors de la creation de la caserne");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(firestation);
			}
		}
	}

	@PutMapping("/firestation")
	public ResponseEntity<FireStation> putFireStation(@RequestBody(required = true) FireStation firestation) {
		if (StringUtils.isEmpty(firestation.getAddress()) && firestation.getStationNumber() == 0) {
			log.error("Erreur lors de la mise à jour");
			return ResponseEntity.badRequest().build();
		} else {
			List<FireStation> fireStations = fireStationService.updateFireStationNumber(firestation);
			if (fireStations.contains(firestation)) {
				log.info("Le numéro de la caserne à été modifié par : {}", firestation.getStationNumber());
				return ResponseEntity.status(HttpStatus.CREATED).body(firestation);
			}
			log.error("Erreur lors de la modification du numéro de caserne");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(firestation);
		}
	}

	@DeleteMapping("/firestation")
	public ResponseEntity<FireStation> deleteFireStation(@RequestBody(required = true) FireStation firestation) {
		if (StringUtils.isEmpty(firestation.getAddress()) && firestation.getStationNumber() == 0) {
			log.error("Impossible de supprimer cette caserne");
			return ResponseEntity.badRequest().build();
		} else {
			List<FireStation> fireStations = fireStationService.deleteFireStation(firestation);
			if (fireStations.contains(firestation)) {
				log.error("Erreur lors de la suppression de la caserne");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			log.info("La caserne suivante à été supprimée : {}", firestation);
			return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
		}
	}

	@JsonSerialize
	public class EmptyJsonResponse {
	}
}
