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

	public MedicalRecordRepositoryImpl() {
	}

	/* CONSTRUCTEUR */
	public MedicalRecordRepositoryImpl(String jsonFilePath) throws IOException {
		getMedicalRecordsFromJson(jsonFilePath);
	}

	@Override
	public List<MedicalRecord> getMedicalRecordList() {
		return medicalRecordsArray;
	}

	public void getMedicalRecordsFromJson(String filePath) throws IOException {
		String stringFile = Files.readString(new File(filePath).toPath(), StandardCharsets.UTF_8);

		JsonIterator iter = JsonIterator.parse(stringFile);
		Any any = iter.readAny();
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
