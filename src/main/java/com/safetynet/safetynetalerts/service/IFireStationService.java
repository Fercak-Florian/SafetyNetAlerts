package com.safetynet.safetynetalerts.service;

import java.io.IOException;
import java.util.List;

import com.safetynet.safetynetalerts.model.FireStation;

public interface IFireStationService {
	List<FireStation> getFireStation() throws IOException;
}
