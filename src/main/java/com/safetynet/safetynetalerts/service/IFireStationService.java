package com.safetynet.safetynetalerts.service;

import java.util.List;


import com.safetynet.safetynetalerts.model.FireStation;

public interface IFireStationService {
	List<FireStation> getFireStation();
	public FireStation addFirestationService(FireStation firestation);
}
