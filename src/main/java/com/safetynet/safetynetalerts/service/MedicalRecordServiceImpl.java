package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.IGlobalRepository;
import com.safetynet.safetynetalerts.repository.IMedicalRecordRepository;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;

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

	@Override
	public List<MedicalRecord> updateMedicalRecordService(MedicalRecord medicalRecord) {
		return globalRepository.updateMedicalRecord(medicalRecord);
	}

	@Override
	public List<MedicalRecord> deleteMedicalRecordService(FirstNameAndLastName combination) {
		return globalRepository.deleteMedicalRecord(combination);
	}
}
