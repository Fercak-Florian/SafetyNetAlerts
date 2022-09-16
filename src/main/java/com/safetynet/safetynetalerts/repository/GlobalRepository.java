package com.safetynet.safetynetalerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

import lombok.Data;

@Data
@Component
public class GlobalRepository implements IGlobalRepository {
	private List<Person> persons;
	private List<FireStation> fireStations;
	private List<MedicalRecord> medicalRecords;

	IPersonRepository personRepository = new PersonRepositoryImpl();
	IFireStationRepository fireStationRepository = new FireStationRepositoryImpl();
	IMedicalRecordRepository medicalRecordsRepository = new MedicalRecordRepositoryImpl();

	/* CONSTRUCTEUR */
	public GlobalRepository() throws IOException {
		this.persons = personRepository.getPersonList();
		this.fireStations = fireStationRepository.getFireStationList();
		this.medicalRecords = medicalRecordsRepository.getMedicalRecordList();
	}

	/* URL_1 LISTE DES FIRESTATIONS CROISEES AVEC LISTE DES PERSONS */
	public List<String> getPersonsCoveredByAFirestation(int stationNumber) throws IOException {

		/* 1 RECUPERER LE NUMERO DE CASERNE ---> PARAMETRE DE LA METHODE */

		/* 2 FAIRE UNE LISTE DES ADRESSE ASSOCIEES AU NUMERO DE CASERNE */
		List<String> addressesAssociatedWithFirestationsNumber = new ArrayList<>();
		for (FireStation fs : fireStations) {
			if (fs.getStationNumber() == stationNumber)
				addressesAssociatedWithFirestationsNumber.add(fs.getAddress());
		}

		/* 3 COMPARER CETTE LISTE AVEC LA LISTE DES PERSONNES (ADRESS) */
		List<String> personsArray = new ArrayList<>();
		
		for (Person person : persons) {
			if (addressesAssociatedWithFirestationsNumber.contains(person.getAddress())) {
				List<String> myArray = new ArrayList<>();
				myArray = Arrays.asList(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone());
				String myString = myArray.stream().collect(Collectors.joining(", "));
				personsArray.add(myString);
			}
		}
		return personsArray;
	}

	/* URL_3 LISTE DES PHONE NUMBERS COUVERTS PAR UNE FIRESTATION */
	@Override
	public List<String> getPhoneNumbersCoveredByAFirestation(int stationNumber) {
		
		/* 1 RECUPERER LE NUMERO DE CASERNE ---> PARAMETRE DE LA METHODE */

		/* 2 FAIRE UNE LISTE DES ADRESSE ASSOCIEES AU NUMERO DE CASERNE */
		List<String> addressesAssociatedWithFirestationsNumber = new ArrayList<>();
		for (FireStation fs : fireStations) {
			if (fs.getStationNumber() == stationNumber)
				addressesAssociatedWithFirestationsNumber.add(fs.getAddress());
		}

		/* 3 COMPARER CETTE LISTE AVEC LA LISTE DES PERSONNES (ADRESS) */
		List<String> personPhoneNumbers = new ArrayList<>();
		for (Person person : persons) {
			if (addressesAssociatedWithFirestationsNumber.contains(person.getAddress())) {
				personPhoneNumbers.add(person.getPhone());
			}
		}
		return personPhoneNumbers;
	}
}
