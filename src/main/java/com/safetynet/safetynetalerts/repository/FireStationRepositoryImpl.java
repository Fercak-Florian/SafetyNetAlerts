package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.model.FireStation;

@Component
public class FireStationRepositoryImpl implements IFireStationRepository {
	/* SERT A COMMUNIQUER AVEC LA SOURCE DE DONNEES */
	/* UTILISER JSONITER */

	List<FireStation> fireStationsArray;

	/* CONSTRUCTEUR */
	public FireStationRepositoryImpl() throws IOException {
		getFireStationsFromJson();
	}

	@Override
	public List<FireStation> getFireStationList() throws IOException {
		return fireStationsArray;
	}

	public void getFireStationsFromJson() throws IOException {

		String filePath = "src/main/resources/data.json";
		String stringFile = Files.readString(new File(filePath).toPath());

		JsonIterator iter = JsonIterator.parse(stringFile);
		Any any = iter.readAny();
		Any fireStationAny = any.get("firestations");
		fireStationsArray = new ArrayList<>();

		for (Any fs : fireStationAny) {
			fireStationsArray.add(new FireStation(fs.get("address").toString(), fs.get("station").toInt()));
		}
	}

	/* TEST D'ECRITURE DANS LE FICHIER TEST.JSON */
	public void addFireStation() throws StreamWriteException, DatabindException, IOException {
		String filePath = "src/main/resources/test.json";
		FireStation newFireStation = new FireStation("2 rue de Paris", 3);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File(filePath), newFireStation);
	}
}