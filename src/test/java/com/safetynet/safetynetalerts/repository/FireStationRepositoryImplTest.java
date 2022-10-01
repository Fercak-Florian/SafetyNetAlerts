package com.safetynet.safetynetalerts.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jsoniter.spi.JsonException;
import com.safetynet.safetynetalerts.data.IDataReader;

@SpringBootTest
public class FireStationRepositoryImplTest {
	
	@Autowired
	FilePaths filePaths;
	
	@Test
	public void testGetFireStationsFromJson() throws Exception {
		/* GIVEN --> ARRANGE */
		IFireStationRepository fireStationRepository = new FireStationRepositoryImpl(filePaths.getProductionFilePath());
		
		/* WHEN --> ACT */
		String result = fireStationRepository.getFireStationList().get(0).getAddress();
		String expected = "1509 Culver St";

		/* THEN --> ASSERT */
		assertTrue(expected.equals(result));
	}

	@Disabled
	@Test
	public void testGetFireStationsFromJsonThrowsException() throws Exception {
		/* GIVEN --> ARRANGE */
		IFireStationRepository fireStationRepository = new FireStationRepositoryImpl(filePaths.getEmptyFilePath());
		/* WHEN --> ACT */
		/* THEN --> ASSERT */
		assertThrows(JsonException.class, () -> fireStationRepository.getFireStationList());
	}
}