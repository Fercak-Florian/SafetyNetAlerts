package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;

public interface IMedicalRecordService {
	List<MedicalRecord> getMedicalRecords();
	List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord);
	List<MedicalRecord> updateMedicalRecord(MedicalRecord medicalRecord);
	List<MedicalRecord> deleteMedicalRecord(FirstNameAndLastName combination);
}
