package com.safetynet.safetynetalerts.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
public class FireStationRepositoryImplTest {

	FilePaths filePaths = new FilePaths();

	@Test
	public void testGetFireStationsFromJson() {
		/* GIVEN --> ARRANGE */
		IFireStationRepository fireStationRepository = new FireStationRepositoryImpl();

		/* WHEN --> ACT */
		String result = fireStationRepository.getFireStationList().get(0).getAddress();
		String expected = "1509 Culver St";

		/* THEN --> ASSERT */
		assertThat(expected).isEqualTo(result);
	}
}