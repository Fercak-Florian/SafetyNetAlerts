package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;

public interface IMedicalRecordService {
	List<MedicalRecord> getMedicalRecords();
	List<MedicalRecord> addMedicalRecordService(MedicalRecord medicalRecord);
	List<MedicalRecord> updateMedicalRecordService(MedicalRecord medicalRecord);
}
