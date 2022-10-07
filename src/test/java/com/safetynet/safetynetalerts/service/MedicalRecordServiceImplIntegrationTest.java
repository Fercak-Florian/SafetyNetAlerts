package com.safetynet.safetynetalerts.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;

@SpringBootTest
public class MedicalRecordServiceImplIntegrationTest {

	@Autowired
	MedicalRecordServiceImpl medicalRecordServiceImpl;

	@Test
	public void testGetMedicalRecords() {
		List<MedicalRecord> result = medicalRecordServiceImpl.getMedicalRecords();
		assertThat(result).isNotNull();
	}

	@Test
	public void testAddMedicalRecordService() {
		/* GIVEN --> ARRANGE */
		List<String> allergies = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		MedicalRecord medicalRecordToAdd = new MedicalRecord("Han", "Solo", "02/02/1985", medications, allergies);
		/* WHEN --> ACT */
		List<MedicalRecord> result = medicalRecordServiceImpl.addMedicalRecord(medicalRecordToAdd);
		/* THEN --> ASSERT */
		assertThat(result).isNotNull();
	}

	@Test
	public void testUpdateMedicalRecordService() {
		/* GIVEN --> ARRANGE */
		List<String> allergies = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		MedicalRecord medicalRecordToUpdate = new MedicalRecord("John", "Boyd", "02/02/1980", medications, allergies);
		/* WHEN --> ACT */
		// when(globalRepository.updateMedicalRecord(null)).thenReturn(medicalRecordsToReturn);
		List<MedicalRecord> result = medicalRecordServiceImpl.updateMedicalRecord(medicalRecordToUpdate);
		/* THEN --> ASSERT */
		assertThat(result).isNotNull();
	}

	@Test
	public void testDeleteMedicalRecordService() {
		/* GIVEN --> ARRANGE */
		FirstNameAndLastName firstNameAndLastName = new FirstNameAndLastName("John", "Boyd");
		/* WHEN --> ACT */
		List<MedicalRecord> result = medicalRecordServiceImpl.deleteMedicalRecord(firstNameAndLastName);
		/* THEN --> ASSERT */
		assertThat(result).isNotNull();
	}
}
