package com.safetynet.safetynetalerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.controller.FireStationController;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationRepository;
import com.safetynet.safetynetalerts.repository.IFireStationRepository;

@Service
public class FireStationServiceImpl implements IFireStationService{
	
	@Autowired
	IFireStationRepository fireStationRepository;
	
	@Override
	public List<FireStation> getFireStation() throws IOException {
		return fireStationRepository.getFireStationFromJson();
	}
	
	//public FireStation addFireStation() {
		//return fireStationController.postController(null)
	//}
}
