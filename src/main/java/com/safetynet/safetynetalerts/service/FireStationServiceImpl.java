package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.data.DataInMemory;
import com.safetynet.safetynetalerts.data.IDataReader;
import com.safetynet.safetynetalerts.model.FireStation;

@Service
public class FireStationServiceImpl implements IFireStationService {

	
	IDataReader globalRepository = DataInMemory.getGlobalRepository();

	@Override
	public List<FireStation> getFireStation() {
		return globalRepository.getFirestations();
	}

	@Override
	public List<FireStation> addFirestationService(FireStation firestation) {
		return globalRepository.addFireStationToRepository(firestation);
	}

	@Override
	public List<FireStation> deleteFirestationService(FireStation firestation) {
		return globalRepository.deleteFirestationToRepository(firestation);
	}

	@Override
	public List<FireStation> updateFirestationNumberService(FireStation firestation) {
		return globalRepository.updateFirestationNumberToRepository(firestation);
	}
}
