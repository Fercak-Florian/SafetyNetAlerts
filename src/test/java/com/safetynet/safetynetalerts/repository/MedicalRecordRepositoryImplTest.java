package com.safetynet.safetynetalerts.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MedicalRecordRepositoryImplTest {

	@Autowired
	FilePaths filePaths;

	@Test
	public void testGetMedicalRecordsFromJson() throws IOException {
		/* GIVEN --> ARRANGE */
		IMedicalRecordRepository medicalRecordRepository = new MedicalRecordRepositoryImpl();
		/* WHEN --> ACT */
		String result = medicalRecordRepository.getMedicalRecordList().get(1).getFirstName();
		String expected = "Jacob";

		/* THEN --> ASSERT */
		assertTrue(expected.equals(result));
	}
}
