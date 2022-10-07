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

	@Value("${com.safetynet.safetynetalerts.filePath}")
	private String jsonFilePath;

	/* CONSTRUCTEUR SANS ARGUMENT */
	public MedicalRecordRepositoryImpl() {
	}

	@Override
	public void setFilePath(String filePath) {
		this.jsonFilePath = filePath;
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
		boolean resume = true;
		try {
			stringFile = Files.readString(new File(jsonFilePath).toPath(), StandardCharsets.UTF_8);
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
		}
	}
}
