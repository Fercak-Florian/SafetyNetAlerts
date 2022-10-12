package com.safetynet.safetynetalerts.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import com.safetynet.safetynetalerts.model.FireStation;

public class FireStationRepositoryImplTest {

	@Test
	public void testGetFireStationsFromJson() {
		/* GIVEN --> ARRANGE */
		IFireStationRepository fireStationRepository = new FireStationRepositoryImpl("src/main/resources/data.json");

		/* WHEN --> ACT */
		String result = fireStationRepository.getFireStations().get(0).getAddress();
		String expected = "1509 Culver St";

		/* THEN --> ASSERT */
		assertThat(expected).isEqualTo(result);
	}

	@Test
	public void testGetFireStationsWithUnproperFilePath() {
		/* GIVEN --> ARRANGE */
		IFireStationRepository fireStationRepository = new FireStationRepositoryImpl("unproper/file/path");

		/* WHEN --> ACT */
		List<FireStation> result = fireStationRepository.getFireStations();

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}
}