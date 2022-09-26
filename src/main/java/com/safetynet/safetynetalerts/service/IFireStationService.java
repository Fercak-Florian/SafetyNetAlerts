package com.safetynet.safetynetalerts.service;

import java.util.List;


import com.safetynet.safetynetalerts.model.FireStation;

public interface IFireStationService {
	List<FireStation> getFireStation();
	List<FireStation> addFirestationService(FireStation firestation);
	List<FireStation> deleteFirestationService(FireStation firestation);
	List<FireStation> updateFirestationNumberService(FireStation firestation);
}
