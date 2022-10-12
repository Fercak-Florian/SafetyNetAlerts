package com.safetynet.safetynetalerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.data.IDataReader;
import com.safetynet.safetynetalerts.model.FireStation;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceImplTest {

	private FireStationServiceImpl fireStationImpl;

	@Mock
	private IDataReader dataReader;

	@BeforeEach
	public void init() {
		fireStationImpl = new FireStationServiceImpl(dataReader);
	}

	@Test
	public void testGetFireStation() {
		when(dataReader.getFireStations()).thenReturn(Arrays.asList(new FireStation("2 rue de Paris", 6)));
		List<FireStation> result = fireStationImpl.getFireStations();
		assertThat(result.get(0).getStationNumber()).isEqualTo(6);
		verify(dataReader).getFireStations();
	}

	@Test
	public void testAddFireStation() {
		when(dataReader.addFireStationToRepository(new FireStation("1509 Culver St", 3)))
				.thenReturn(Arrays.asList(new FireStation("2 rue de Paris", 6)));
		List<FireStation> result = fireStationImpl.addFireStation(new FireStation("1509 Culver St", 3));
		assertThat(result.get(0).getStationNumber()).isEqualTo(6);
		verify(dataReader).addFireStationToRepository(new FireStation("1509 Culver St", 3));
	}

	@Test
	public void testUpdateFireStationNumberService() {
		when(dataReader.updateFirestationNumberToRepository(new FireStation("1509 Culver St", 3)))
				.thenReturn(Arrays.asList(new FireStation("2 rue de Paris", 6)));
		List<FireStation> result = fireStationImpl.updateFireStationNumber(new FireStation("1509 Culver St", 3));
		assertThat(result.get(0).getStationNumber()).isEqualTo(6);
		verify(dataReader).updateFirestationNumberToRepository(new FireStation("1509 Culver St", 3));
	}

	@Test
	public void testDeleteFireStation() {
		when(dataReader.deleteFirestationToRepository(new FireStation("1509 Culver St", 3)))
				.thenReturn(Arrays.asList(new FireStation("951 LoneTree Rd", 2)));
		List<FireStation> result = fireStationImpl.deleteFireStation(new FireStation("1509 Culver St", 3));
		assertThat(result.get(0).getStationNumber()).isEqualTo(2);
		verify(dataReader).deleteFirestationToRepository(new FireStation("1509 Culver St", 3));
	}
}
