package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.IMedicalRecordService;

@RestController
public class MedicalRecordController {
	/* APPELRE LES METHODES DU SERVICE */
	@Autowired
	IMedicalRecordService medicalRecordService;

	@GetMapping("/medicalrecords")
	public List<MedicalRecord> getMedicalRecordFromService() {
		return medicalRecordService.getMedicalRecord();
	}
}
