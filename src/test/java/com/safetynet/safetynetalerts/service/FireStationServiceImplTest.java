package com.safetynet.safetynetalerts.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.FireStation;

@SpringBootTest
public class FireStationServiceImplTest {

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
		// List<FireStation> fireStationsToReturn = new ArrayList<>();
		FireStation fireStationToAdd = new FireStation("951 LoneTree Rd", 2);
		// fireStationsToReturn.add(new FireStation("1509 Culver St", 3));
		/* WHEN --> ACT */
		// when(globalRepository.addFireStationToRepository(null)).thenReturn(fireStationsToReturn);
		List<FireStation> result = fireStationImpl.addFirestationService(fireStationToAdd);
		/* THEN --> ASSERT */
		assertThat(result).isNotNull();
	}

	@Test
	public void testDeleteFireStation() {
		/* GIVEN --> ARRANGE */
		// List<FireStation> fireStationsToReturn = new ArrayList<>();
		FireStation fireStationToDelete = new FireStation("951 LoneTree Rd", 2);
		// fireStationsToReturn.add(new FireStation("1509 Culver St", 3));
		/* WHEN --> ACT */
		// when(globalRepository.deleteFirestationToRepository(null)).thenReturn(fireStationsToReturn);
		List<FireStation> result = fireStationImpl.deleteFirestationService(fireStationToDelete);
		/* THEN --> ASSERT */
		assertThat(result).isNotNull();
	}

	@Test
	public void testUpdateFireStationNumberService() {
		/* GIVEN --> ARRANGE */
		// List<FireStation> fireStationsToReturn = new ArrayList<>();
		FireStation fireStationToUpdate = new FireStation("951 LoneTree Rd", 2);
		// fireStationsToReturn.add(new FireStation("1509 Culver St", 3));
		/* WHEN --> ACT */
		// when(globalRepository.updateFirestationNumberToRepository(null)).thenReturn(fireStationsToReturn);
		List<FireStation> result = fireStationImpl.updateFirestationNumberService(fireStationToUpdate);
		/* THEN --> ASSERT */
		assertThat(result).isNotNull();
	}
}
