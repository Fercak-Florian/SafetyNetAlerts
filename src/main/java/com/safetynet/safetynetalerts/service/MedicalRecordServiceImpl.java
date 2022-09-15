package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.IMedicalRecordRepository;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {
	/* APPELER LES METHODES DE LA CLASSE REPOSITORY */
	
	@Autowired
	IMedicalRecordRepository medicalRecordRepository;
	
	@Override
	public List<MedicalRecord> getMedicalRecord() {
		return medicalRecordRepository.getMedicalRecordList();
	}

}
