package com.safetynet.safetynetalerts.data;

import java.io.IOException;


import com.safetynet.safetynetalerts.repository.FilePaths;
import com.safetynet.safetynetalerts.repository.FireStationRepositoryImpl;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepositoryImpl;
import com.safetynet.safetynetalerts.repository.PersonRepositoryImpl;


public class DataInMemory {

	private static IDataReader globalRepository = null;

	private static String jsonFilePath = new FilePaths().getProductionFilePath();
	

	public static IDataReader getGlobalRepository() {
		
		CustomProperties prop = new CustomProperties();
		
		System.out.println(prop.getFilePath());
		
		if (globalRepository == null) {
			try {
				
				globalRepository = new DataReader(new PersonRepositoryImpl(jsonFilePath).getPersonList(),
						new FireStationRepositoryImpl(jsonFilePath).getFireStationList(), new MedicalRecordRepositoryImpl(jsonFilePath).getMedicalRecordList());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return globalRepository;
	}
}