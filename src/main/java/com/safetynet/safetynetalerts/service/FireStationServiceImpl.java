package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.IFireStationRepository;
import com.safetynet.safetynetalerts.repository.IGlobalRepository;

@Service
public class FireStationServiceImpl implements IFireStationService {

	@Autowired
	IFireStationRepository fireStationRepository;
	@Autowired
	IGlobalRepository globalRepository;

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
