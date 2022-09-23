package com.safetynet.safetynetalerts.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FireStationRepositoryImplTest {

	@Autowired
	IFireStationRepository firestationRepository;

	@Test
	public void testGetFireStationsFromJson() throws Exception {
		/* GIVEN --> ARRANGE */

		/* WHEN --> ACT */
		String result = firestationRepository.getFireStationList().get(0).getAddress();
		String expected = "1509 Culver St";

		/* THEN --> ASSERT */
		assertTrue(expected.equals(result));
	}
}
