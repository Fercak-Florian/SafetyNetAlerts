package com.safetynet.safetynetalerts.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MedicalRecordTest {

	@Test
	public void getAgeTest() {
		/* GIVEN --> ARRANGE */
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Bacta");
		medications.add("Antalgiques");
		allergies.add("ewoks");
		MedicalRecord medicalRecord = new MedicalRecord("Han", "Solo", "01/02/1987", medications, allergies);

		/* WHEN --> ACT */
		int age = medicalRecord.getAge();
		/* THEN --> ASSERT */
		assertThat(age).isEqualTo(35);
	}
	
	@Test
	public void getAgeWithIncompleteBirthdate() {
		/* GIVEN --> ARRANGE */
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Bacta");
		medications.add("Antalgiques");
		allergies.add("ewoks");
		MedicalRecord medicalRecord = new MedicalRecord("Han", "Solo", "01/02", medications, allergies);

		/* WHEN --> ACT */
		int age = medicalRecord.getAge();
		/* THEN --> ASSERT */
		assertThat(age).isNotEqualTo(35);
	}
}
