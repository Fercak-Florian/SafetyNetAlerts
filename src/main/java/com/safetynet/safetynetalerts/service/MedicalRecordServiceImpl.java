package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.data.IDataReader;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {

	private IDataReader dataReader;
	
	public MedicalRecordServiceImpl(IDataReader dataReader) {
		this.dataReader = dataReader;
	}

	@Override
	public List<MedicalRecord> getMedicalRecords() {
		return dataReader.getMedicalRecords();
	}

	@Override
	public List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord) {
		return dataReader.addMedicalRecord(medicalRecord);
	}

	@Override
	public List<MedicalRecord> updateMedicalRecord(MedicalRecord medicalRecord) {
		return dataReader.updateMedicalRecord(medicalRecord);
	}

	@Override
	public List<MedicalRecord> deleteMedicalRecord(FirstNameAndLastName combination) {
		return dataReader.deleteMedicalRecord(combination);
	}
}
