package com.safetynet.safetynetalerts.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.IMedicalRecordService;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MedicalRecordController {
	/* APPELLE LES METHODES DU SERVICE */
	@Autowired
	IMedicalRecordService medicalRecordService;

	/* CRUD POUR MEDICALRECORDS */

	@GetMapping("/medicalRecord")
	public List<MedicalRecord> getMedicalRecordFromService() {
		log.info("Récupération de tous les dossiers medicaux");
		return medicalRecordService.getMedicalRecords();
	}

	@PostMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> postMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		if (medicalRecord.getFirstName() == null || medicalRecord.getLastName() == null) {
			log.error("Impossible d'ajouter ce dossier medical : {}", medicalRecord);
			return ResponseEntity.badRequest().build();
		} else {
			List<MedicalRecord> mrList = medicalRecordService.addMedicalRecordService(medicalRecord);
			if (mrList.contains(medicalRecord)) {
				log.info("Le dossier medical suivant à été créé : {}", medicalRecord);
				return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecord);
			}
			log.error("Erreur lors de la creation du dossier medical");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(medicalRecord);
		}
	}

	@PutMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> putMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		if (medicalRecord.getFirstName() == null || medicalRecord.getLastName() == null) {
			log.error("Impossible de modifier le dossier medical");
			return ResponseEntity.badRequest().build();
		} else {
			List<MedicalRecord> mrList = medicalRecordService.updateMedicalRecordService(medicalRecord);
			if (mrList.contains(medicalRecord)) {
				log.info("Le dossier medical suivant à été mis à jour : {}", medicalRecord);
				return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecord);
			}
			log.error("Erreur lors de la modification du dossier medical");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(medicalRecord);
		}
	}

	@DeleteMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> deleteMedicalRecord(@RequestBody FirstNameAndLastName combination) {
		if (combination.getFirstName() == null || combination.getLastName() == null) {
			log.error("Impossible de supprimer le dossier medical");
			return ResponseEntity.badRequest().build();
		} else {
			List<MedicalRecord> mrList = medicalRecordService.deleteMedicalRecordService(combination);
			if (mrList.contains(combination)) {
				log.error("Erreur lors de la suppression du dossier medical");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		log.info("Le dossier medical de cette personne à été supprimé : {}", combination);
		return ResponseEntity.ok().build();
	}
}
