package com.safetynet.safetynetalerts.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.model.MedicalRecord;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordRepositoryImplTest {

	@Test
	public void testGetMedicalRecordsFromJson() {
		/* GIVEN --> ARRANGE */
		IMedicalRecordRepository medicalRecordRepository = new MedicalRecordRepositoryImpl("src/main/resources/data.json");

		/* WHEN --> ACT */
		String result = medicalRecordRepository.getMedicalRecords().get(1).getFirstName();
		String expected = "Jacob";

		/* THEN --> ASSERT */
		assertThat(expected).isEqualTo(result);
	}

	@Test
	public void testGetMedicalRecordsWithUnproperFilePath() {
		/* GIVEN --> ARRANGE */
		IMedicalRecordRepository medicalRecordRepository = new MedicalRecordRepositoryImpl("unproper/file/path");

		/* WHEN --> ACT */
		List<MedicalRecord> result = medicalRecordRepository.getMedicalRecords();

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}
}
