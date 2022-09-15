package com.safetynet.safetynetalerts.repository;

import java.util.List;

import com.safetynet.safetynetalerts.model.MedicalRecord;

public interface IMedicalRecordRepository {
	List<MedicalRecord> getMedicalRecordList();
}
