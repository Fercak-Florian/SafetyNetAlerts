package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.model.MedicalRecord;

@Component
public class MedicalRecordRepositoryImpl implements IMedicalRecordRepository {
	/* SERT A COMMUNIQUER AVEC LA SOURCE DE DONNEES */
	/* UTILISER JSONITER */

	List<MedicalRecord> medicalRecordsArray;

	private String jsonFilePath = "src/main/resources/data.json";

	/* CONSTRUCTEUR */
	public MedicalRecordRepositoryImpl() {
		getMedicalRecordsFromJson();
	}

	@Override
	public List<MedicalRecord> getMedicalRecordList() {
		return medicalRecordsArray;
	}

	public void getMedicalRecordsFromJson() {
		String stringFile = null;
		try {
			stringFile = Files.readString(new File(jsonFilePath).toPath(), StandardCharsets.UTF_8);
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
		Any medicalRecordsAny = any.get("medicalrecords");

		medicalRecordsArray = new ArrayList<>();

		for (Any mr : medicalRecordsAny) {
			List<String> myMedications = new ArrayList<>();
			List<String> myAllergies = new ArrayList<>();
			List<Any> medicationsAny = mr.get("medications").asList();
			List<Any> allergiesAny = mr.get("allergies").asList();
			for (Any m : medicationsAny) {
				myMedications.add(m.toString());
			}

			for (Any a : allergiesAny) {
				myAllergies.add(a.toString());
			}

			medicalRecordsArray.add(new MedicalRecord(mr.get("firstName").toString(), mr.get("lastName").toString(),
					mr.get("birthdate").toString(), myMedications, myAllergies));
		}
	}
}
