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

@Component
public class FireStationRepositoryImpl implements IFireStationRepository {
	/* SERT A COMMUNIQUER AVEC LA SOURCE DE DONNEES */

	private List<FireStation> fireStationsArray;

	private String jsonFilePath = "src/main/resources/data.json";
	//@Value("${test.filePath}")
	//private String jsonFilePath;
	
	/*
	 * public String jsonFilePath() {
	 * 
	 * @Value("${test.filePath}") String path; return path; }
	 */

	/* CONSTRUCTEUR AVEC ARGUMENT */
	@Autowired
	public FireStationRepositoryImpl() {
		getFireStationsFromJson();
	}

	@Override
	public List<FireStation> getFireStationList() {
		return fireStationsArray;
	}

	public void getFireStationsFromJson() {
		String stringFile = "";
		try {
			stringFile = Files.readString(new File(jsonFilePath).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonIterator iter = JsonIterator.parse(stringFile);
		Any any = null;
		try {
			any = iter.readAny();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Any fireStationAny = any.get("firestations");
		fireStationsArray = new ArrayList<>();

		for (Any fs : fireStationAny) {
			fireStationsArray.add(new FireStation(fs.get("address").toString(), fs.get("station").toInt()));
		}
	}
}