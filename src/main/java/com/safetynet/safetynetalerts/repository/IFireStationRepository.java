package com.safetynet.safetynetalerts.repository;

import java.util.List;

import com.safetynet.safetynetalerts.model.FireStation;

public interface IFireStationRepository {
	void setFilePath(String filePath);
	List<FireStation> getFireStations();
}
