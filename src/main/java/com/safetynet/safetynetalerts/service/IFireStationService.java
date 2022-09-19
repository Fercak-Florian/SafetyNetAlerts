package com.safetynet.safetynetalerts.service;

import java.util.List;


import com.safetynet.safetynetalerts.model.FireStation;

public interface IFireStationService {
	List<FireStation> getFireStation();
	FireStation addFirestationService(FireStation firestation);
	FireStation deleteFirestationService(FireStation firestation);
	FireStation updateFirestationNumberService(FireStation firestation);
}
