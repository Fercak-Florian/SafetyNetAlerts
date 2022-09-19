package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.model.MedicalRecord;

public interface IMedicalRecordService {
	List<MedicalRecord> getMedicalRecords();
	List<MedicalRecord> addMedicalRecordService(MedicalRecord medicalRecord);
}
