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

@Slf4j
@Component
public class FireStationRepositoryImpl implements IFireStationRepository {

	private List<FireStation> fireStationsArray;

	@Value("${com.safetynet.safetynetalerts.filePath}")
	private String jsonFilePath;

	/* CONSTRUCTEUR SANS ARGUMENT */
	@Autowired
	public FireStationRepositoryImpl() {
	}

	@Override
	public void setFilePath(String filePath) {
		this.jsonFilePath = filePath;
	}

	@Override
	public List<FireStation> getFireStations() {
		if (fireStationsArray == null) {
			getFireStationsFromJson();
		}
		return fireStationsArray;
	}

	public void getFireStationsFromJson() {
		fireStationsArray = new ArrayList<>();
		String stringFile = "";
		boolean resume = true;
		try {
			stringFile = Files.readString(new File(jsonFilePath).toPath());
		} catch (IOException e) {
			log.error("Impossible de lire le fichier");
			e.printStackTrace();
			resume = false;
		}

		if (resume) {
			JsonIterator iter = JsonIterator.parse(stringFile);
			Any any = null;
			try {
				any = iter.readAny();
			} catch (IOException e) {
				log.error("Impossible d'analyser le contenu du fichier");
				e.printStackTrace();
			}
			Any fireStationAny = any.get("firestations");
			for (Any fs : fireStationAny) {
				fireStationsArray.add(new FireStation(fs.get("address").toString(), fs.get("station").toInt()));
			}
		}
	}
}