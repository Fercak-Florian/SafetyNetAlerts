package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;
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

	private List<MedicalRecord> medicalRecordsArray;

	/* CONSTRUCTEUR */
	public MedicalRecordRepositoryImpl() throws IOException {
		getMedicalRecordsFromJson();
	}
	
	@Override
	public List<MedicalRecord> getMedicalRecordList() {
		return medicalRecordsArray;
	}

	public void getMedicalRecordsFromJson() throws IOException {
		String filePath = "src/main/resources/data.json";
		String stringFile = Files.readString(new File(filePath).toPath());

		JsonIterator iter = JsonIterator.parse(stringFile);
		Any any = iter.readAny();
		Any medicalRecordsAny = any.get("medicalrecords");
		
		medicalRecordsArray = new ArrayList<>();

		for (Any mr : medicalRecordsAny) {
			List<String> myMedications = new ArrayList<>();
			List<String> myAllergies = new ArrayList<>();
			List<Any> medicationsAny = mr.get("medications").asList();
			List<Any> allergiesAny = mr.get("allergies").asList();
			for (Any m : medicationsAny){
				myMedications.add(m.toString());
			}
			
			for(Any a : allergiesAny) {
				myAllergies.add(a.toString());
			}
			
			medicalRecordsArray.add(new MedicalRecord(mr.get("firstName").toString(), mr.get("lastName").toString(),
					mr.get("birthdate").toString(), myMedications, myAllergies));
		}
	}
}
