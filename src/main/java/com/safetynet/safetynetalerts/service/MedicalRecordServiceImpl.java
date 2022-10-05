package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.data.IDataReader;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {

	@Autowired
	IDataReader dataReader;

	@Override
	public List<MedicalRecord> getMedicalRecords() {
		return dataReader.getMedicalRecords();
	}

	@Override
	public List<MedicalRecord> addMedicalRecordService(MedicalRecord medicalRecord) {
		return dataReader.addMedicalRecord(medicalRecord);
	}

	@Override
	public List<MedicalRecord> updateMedicalRecordService(MedicalRecord medicalRecord) {
		return dataReader.updateMedicalRecord(medicalRecord);
	}

	@Override
	public List<MedicalRecord> deleteMedicalRecordService(FirstNameAndLastName combination) {
		return dataReader.deleteMedicalRecord(combination);
	}
}
