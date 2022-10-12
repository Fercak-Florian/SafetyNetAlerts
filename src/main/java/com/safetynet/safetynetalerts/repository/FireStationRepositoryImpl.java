package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.model.FireStation;

import lombok.extern.slf4j.Slf4j;

/**
 * This class allows to access to a JSON file and loads data from the
 * FireStation part. This class implements the IFireStationRepository interface.
 */
@Slf4j
@Component
public class FireStationRepositoryImpl implements IFireStationRepository {

	private List<FireStation> fireStationsArray;

	
	private String jsonFilePath;

	@Autowired
	public FireStationRepositoryImpl(@Value("${com.safetynet.safetynetalerts.filePath}") String jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
	}

	@Override
	public List<FireStation> getFireStations() {
		if (fireStationsArray == null) {
			getFireStationsFromJson();
		}
		return fireStationsArray;
	}

	/**
	 * This method accesses to a JSON file and load data from it into a list
	 */
	public void getFireStationsFromJson() {
		fireStationsArray = new ArrayList<>();
		String stringFile = "";
		try {
			stringFile = Files.readString(new File(jsonFilePath).toPath());
			JsonIterator iter = JsonIterator.parse(stringFile);
			Any any = iter.readAny();
			Any fireStationAny = any.get("firestations");
			for (Any fs : fireStationAny) {
				fireStationsArray.add(new FireStation(fs.get("address").toString(), fs.get("station").toInt()));
			}
		} catch (IOException e) {
			log.error("Impossible de lire le fichier");
			e.printStackTrace();
		}
	}
}