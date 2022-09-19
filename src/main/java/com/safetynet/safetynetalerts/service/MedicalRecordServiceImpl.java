package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.IGlobalRepository;
import com.safetynet.safetynetalerts.repository.IMedicalRecordRepository;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {
	/* APPELER LES METHODES DE LA CLASSE REPOSITORY */

	@Autowired
	IGlobalRepository globalRepository;

	@Override
	public List<MedicalRecord> getMedicalRecords() {
		return globalRepository.getMedicalRecords();
	}

	@Override
	public List<MedicalRecord> addMedicalRecordService(MedicalRecord medicalRecord) {
		return globalRepository.addMedicalRecord(medicalRecord);
	}

}
