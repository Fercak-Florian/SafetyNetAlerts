package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.model.MedicalRecord;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MedicalRecordRepositoryImpl implements IMedicalRecordRepository {

	List<MedicalRecord> medicalRecordsArray;

	private String jsonFilePath;

	public MedicalRecordRepositoryImpl(@Value("${com.safetynet.safetynetalerts.filePath}") String jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
	}

	@Override
	public List<MedicalRecord> getMedicalRecords() {
		if (medicalRecordsArray == null) {
			getMedicalRecordsFromJson();
		}
		return medicalRecordsArray;
	}

	public void getMedicalRecordsFromJson() {
		medicalRecordsArray = new ArrayList<>();
		String stringFile = null;
		try {
			stringFile = Files.readString(new File(jsonFilePath).toPath(), StandardCharsets.UTF_8);
			JsonIterator iter = JsonIterator.parse(stringFile);

			Any any = iter.readAny();
			Any medicalRecordsAny = any.get("medicalrecords");
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
		} catch (IOException e) {
			log.error("Impossible de lire le fichier");
			e.printStackTrace();
		}
	}
}
