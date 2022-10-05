package com.safetynet.safetynetalerts.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordRepositoryImplTest {

	FilePaths filePaths = new FilePaths();

	@Test
	public void testGetMedicalRecordsFromJson() {
		/* GIVEN --> ARRANGE */
		IMedicalRecordRepository medicalRecordRepository = new MedicalRecordRepositoryImpl();
		/* WHEN --> ACT */
		String result = medicalRecordRepository.getMedicalRecordList().get(1).getFirstName();
		String expected = "Jacob";

		/* THEN --> ASSERT */
		assertThat(expected).isEqualTo(result);
	}
}
