package com.safetynet.safetynetalerts.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.FireStation;

@SpringBootTest
public class FireStationServiceImplIntegrationTest {

	@Autowired
	FireStationServiceImpl fireStationImpl;

	@Test
	public void testGetFireStation() {
		List<FireStation> result = fireStationImpl.getFireStation();
		assertThat(result).isNotNull();
	}

	@Test
	public void testAddFireStation() {
		/* GIVEN --> ARRANGE */
		FireStation fireStationToAdd = new FireStation("951 LoneTree Rd", 2);
		
		/* WHEN --> ACT */
		List<FireStation> result = fireStationImpl.addFireStation(fireStationToAdd);
		
		/* THEN --> ASSERT */
		assertThat(result).isNotNull();
	}

	@Test
	public void testDeleteFireStation() {
		/* GIVEN --> ARRANGE */
		FireStation fireStationToDelete = new FireStation("951 LoneTree Rd", 2);
		
		/* WHEN --> ACT */
		List<FireStation> result = fireStationImpl.deleteFireStation(fireStationToDelete);
		/* THEN --> ASSERT */
		assertThat(result).isNotNull();
	}

	@Test
	public void testUpdateFireStationNumberService() {
		/* GIVEN --> ARRANGE */
		FireStation fireStationToUpdate = new FireStation("951 LoneTree Rd", 2);
		
		/* WHEN --> ACT */
		List<FireStation> result = fireStationImpl.updateFireStationNumber(fireStationToUpdate);
		
		/* THEN --> ASSERT */
		assertThat(result).isNotNull();
	}
}
