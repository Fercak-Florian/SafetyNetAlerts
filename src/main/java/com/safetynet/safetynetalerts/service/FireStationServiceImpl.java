package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.data.IDataReader;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.IFireStationRepository;

@Service
public class FireStationServiceImpl implements IFireStationService {

	@Autowired
	IDataReader dataReader;

	@Override
	public List<FireStation> getFireStation() {
		return dataReader.getFirestations();
	}

	@Override
	public List<FireStation> addFireStation(FireStation fireStation) {
		return dataReader.addFireStationToRepository(fireStation);
	}

	@Override
	public List<FireStation> updateFireStationNumber(FireStation fireStation) {
		return dataReader.updateFirestationNumberToRepository(fireStation);
	}

	@Override
	public List<FireStation> deleteFireStation(FireStation fireStation) {
		return dataReader.deleteFirestationToRepository(fireStation);
	}
}
