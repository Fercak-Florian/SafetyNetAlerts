package com.safetynet.safetynetalerts.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class FireStationRepositoryImplTest {
	
	@Autowired
	FilePaths filePaths;
	
	@Test
	public void testGetFireStationsFromJson() throws Exception {
		/* GIVEN --> ARRANGE */
		IFireStationRepository fireStationRepository = new FireStationRepositoryImpl();
		
		/* WHEN --> ACT */
		String result = fireStationRepository.getFireStationList().get(0).getAddress();
		String expected = "1509 Culver St";

		/* THEN --> ASSERT */
		assertTrue(expected.equals(result));
	}

}