package com.safetynet.safetynetalerts.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.MedicalRecord;

@SpringBootTest
public class GlobalRepositoryTest {

	@Autowired
	private GlobalRepository globalRepository;

	@Test
	public void testAgeCalculate() {
		/* GIVEN --> ARRANGE */
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Bacta");
		medications.add("Antalgiques");
		allergies.add("ewoks");
		MedicalRecord medicalRecord = new MedicalRecord("Han", "Solo", "01/02/6000", medications, allergies);
		
		/* WHEN --> ACT */
		int age = globalRepository.ageCalculate(medicalRecord);
		
		/* THEN --> ASSERT */
		assertThat(age).isNotNull();
	}
}
