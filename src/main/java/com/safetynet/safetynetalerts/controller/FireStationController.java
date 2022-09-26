package com.safetynet.safetynetalerts.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FireStationController {
	/* APPELER DES METHODES DE LA CLASSE SERVICE */

	// private static Logger logger =
	// LoggerFactory.getLogger(FireStationController.class);

	@Autowired
	private IFireStationService fireStationService;

	/* CRUD POUR FIRESTATIONS */

	@GetMapping("/firestations")
	public List<FireStation> getFireStationFromService() {
		log.info("Récuperation de toutes les casernes");
		return fireStationService.getFireStation();
	}

	@PostMapping("/firestation")
	public ResponseEntity<FireStation> postFirestation(@RequestBody FireStation firestation) {
		if (firestation.getAddress() == null || firestation.getStationNumber() == 0) {
			log.info("Impossible d'ajouter cette caserne");
			return ResponseEntity.badRequest().build();
		} else {
			List<FireStation> fsList = fireStationService.addFirestationService(firestation);
			if (fsList.contains(firestation)) {
				log.info("La caserne suivante à été créee : {}", firestation);
				return ResponseEntity.status(HttpStatus.CREATED).body(firestation);
			}
			log.info("Erreur lors de la creation de la caserne");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(firestation);
		}
	}

	@PutMapping("/firestation")
	public ResponseEntity<FireStation> putFirestation(@RequestBody FireStation firestation) {
		if (firestation.getAddress() == null || firestation.getStationNumber() == 0) {
			log.info("Erreur lors de la mise à jour");
			return ResponseEntity.badRequest().build(); // CRASHING HERE
		} else {
			List<FireStation> fsList = fireStationService.updateFirestationNumberService(firestation);
			if (fsList.contains(firestation)) {
				log.info("Le numéro de la caserne à été modifié par : {}", firestation.getStationNumber());
				return ResponseEntity.status(HttpStatus.CREATED).body(firestation);
			}
			log.info("Erreur lors de la modification du numérode caserne");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(firestation);
		}
	}

	@DeleteMapping("/firestation")
	public ResponseEntity<FireStation> deleteFirestation(@RequestBody FireStation firestation) {
		if (firestation.getAddress() == null || firestation.getStationNumber() == 0) {
			log.info("Impossible de supprimer cette caserne");
			return ResponseEntity.badRequest().build();
		} else {
			List<FireStation> fsList = fireStationService.deleteFirestationService(firestation);
			if (fsList.contains(firestation)) {
				log.info("Erreur lors de la suppression de la caserne");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			log.info("La caserne suivante à été supprimée : {}", firestation);
			return ResponseEntity.status(HttpStatus.OK).body(firestation);
		}
	}
}
