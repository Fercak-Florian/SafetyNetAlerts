package com.safetynet.safetynetalerts.repository;

import java.io.IOException;
import java.util.List;

import com.safetynet.safetynetalerts.model.FireStation;

public interface IFireStationRepository {
	List<FireStation> getFireStationList() throws IOException;
}
