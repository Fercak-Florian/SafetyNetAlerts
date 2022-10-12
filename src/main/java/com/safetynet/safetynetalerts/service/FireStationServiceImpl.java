package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.data.IDataReader;
import com.safetynet.safetynetalerts.model.FireStation;

/**
 * This class calls methods from the repository class
 */
@Service
public class FireStationServiceImpl implements IFireStationService {

	private IDataReader dataReader;

	public FireStationServiceImpl(IDataReader dataReader) {
		this.dataReader = dataReader;
	}

	/**
	 * This method gets data from the memory
	 * @return a list of FireStation
	 */
	@Override
	public List<FireStation> getFireStations() {
		return dataReader.getFireStations();
	}

	/**
	 * This method adds data to the memory
	 * @param a FireStation object
	 * @return a list of FireStation
	 */
	@Override
	public List<FireStation> addFireStation(FireStation fireStation) {
		return dataReader.addFireStationToRepository(fireStation);
	}

	@Override
	public List<FireStation> updateFireStationNumber(FireStation fireStation) {
		return dataReader.updateFirestationNumberToRepository(fireStation);
	}

	@Override
	public List<FireStation> deleteFireStation(FireStation fireStation) {
		return dataReader.deleteFirestationToRepository(fireStation);
	}
}
