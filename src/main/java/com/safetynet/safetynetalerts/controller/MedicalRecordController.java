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
		log.info("Récuperation de tous les dossiers medicaux");
		return medicalRecordService.getMedicalRecords();
	}

	@PostMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> postMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		List<MedicalRecord> mr = medicalRecordService.addMedicalRecordService(medicalRecord);
		if (Objects.isNull(mr)) {
			log.info("Le dossier medical passé en paramètre est vide");
			return ResponseEntity.notFound().build();
		} else {
			log.info("Le dossier medical suivant à été créé : {}", medicalRecord);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/")
					.buildAndExpand(medicalRecord.getFirstName()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@PutMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> putMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		List<MedicalRecord> mr = medicalRecordService.updateMedicalRecordService(medicalRecord);
		if (Objects.isNull(mr)) {
			log.info("Le dossier medical passé en paramètre est vide");
			return ResponseEntity.noContent().build();
		} else {
			log.info("Le dossier medical suivant à été mis à jour : {}", medicalRecord);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/")
					.buildAndExpand(medicalRecord.getFirstName()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@DeleteMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> deleteMedicalRecord(@RequestBody FirstNameAndLastName combination) {
		log.info("Le dossier medical de cette personne à été supprimé : {}", combination);
		medicalRecordService.deleteMedicalRecordService(combination);
		return null;
	}
}
