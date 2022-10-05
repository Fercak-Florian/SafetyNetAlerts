package com.safetynet.safetynetalerts.data;

import java.io.IOException;
import java.util.List;

import com.safetynet.safetynetalerts.repository.FireStationRepositoryImpl;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepositoryImpl;
import com.safetynet.safetynetalerts.repository.PersonRepositoryImpl;

public class DataInMemory {

	private static IDataReader globalRepository = null;

	public static IDataReader getGlobalRepository() {

		CustomProperties prop = new CustomProperties();

		System.out.println(prop.getFilePath());

		if (globalRepository == null) {
			try {

				globalRepository = new DataReader(new PersonRepositoryImpl(), new FireStationRepositoryImpl(),
						new MedicalRecordRepositoryImpl());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return globalRepository;
	}
}
