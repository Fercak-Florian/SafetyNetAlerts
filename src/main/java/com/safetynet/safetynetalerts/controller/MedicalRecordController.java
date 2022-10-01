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
	public ResponseEntity<MedicalRecord> postMedicalRecord(@RequestBody(required = true) MedicalRecord medicalRecord) {
		if (StringUtils.isEmpty(medicalRecord.getFirstName()) && StringUtils.isEmpty(medicalRecord.getLastName())) {
			log.error("Impossible d'ajouter ce dossier medical : {}", medicalRecord);
			return ResponseEntity.badRequest().build();
		} else {
			List<MedicalRecord> medicalRecords = medicalRecordService.addMedicalRecordService(medicalRecord);
			if (medicalRecords.contains(medicalRecord)) {
				log.info("Le dossier medical suivant à été créé : {}", medicalRecord);
				return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecord);
			}
			log.error("Erreur lors de la creation du dossier medical");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(medicalRecord);
		}
	}

	@PutMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> putMedicalRecord(@RequestBody(required = true) MedicalRecord medicalRecord) {
		if (StringUtils.isEmpty(medicalRecord.getFirstName()) && StringUtils.isEmpty(medicalRecord.getLastName())) {
			log.error("Impossible de modifier le dossier medical");
			return ResponseEntity.badRequest().build();
		} else {
			List<MedicalRecord> medicalRecords = medicalRecordService.updateMedicalRecordService(medicalRecord);
			if (medicalRecords.contains(medicalRecord)) {
				log.info("Le dossier medical suivant à été mis à jour : {}", medicalRecord);
				return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecord);
			}
			log.error("Erreur lors de la modification du dossier medical");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(medicalRecord);
		}
	}

	@DeleteMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> deleteMedicalRecord(
			@RequestBody(required = true) FirstNameAndLastName combination) {
		if (StringUtils.isEmpty(combination.getFirstName()) && StringUtils.isEmpty(combination.getLastName())) {
			log.error("Impossible de supprimer le dossier medical");
			return ResponseEntity.badRequest().build();
		} else {
			List<MedicalRecord> medicalRecords = medicalRecordService.deleteMedicalRecordService(combination);
			if (medicalRecords.contains(combination)) {
				log.error("Erreur lors de la suppression du dossier medical");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		log.info("Le dossier medical de cette personne à été supprimé : {}", combination);
		return ResponseEntity.ok().build();
	}
}
