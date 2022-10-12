package com.safetynet.safetynetalerts.service;

import java.util.List;


import com.safetynet.safetynetalerts.model.FireStation;

public interface IFireStationService {
	List<FireStation> getFireStations();
	List<FireStation> addFireStation(FireStation fireStation);
	List<FireStation> deleteFireStation(FireStation fireStation);
	List<FireStation> updateFireStationNumber(FireStation fireStation);
}
