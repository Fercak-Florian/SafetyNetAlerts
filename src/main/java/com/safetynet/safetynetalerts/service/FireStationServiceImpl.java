package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.data.IDataReader;
import com.safetynet.safetynetalerts.model.FireStation;

@Service
public class FireStationServiceImpl implements IFireStationService {

	@Autowired
	IDataReader dataReader;

	@Override
	public List<FireStation> getFireStation() {
		return dataReader.getFirestations();
	}

	@Override
	public List<FireStation> addFirestationService(FireStation firestation) {
		return dataReader.addFireStationToRepository(firestation);
	}

	@Override
	public List<FireStation> updateFirestationNumberService(FireStation firestation) {
		return dataReader.updateFirestationNumberToRepository(firestation);
	}

	@Override
	public List<FireStation> deleteFirestationService(FireStation firestation) {
		return dataReader.deleteFirestationToRepository(firestation);
	}
}
