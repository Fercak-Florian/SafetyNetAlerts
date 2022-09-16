package com.safetynet.safetynetalerts.repository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
	public List<String> getPersonsCoveredByAFirestation(int stationNumber) throws IOException, ParseException {

		/* 1 RECUPERER LE NUMERO DE CASERNE ---> PARAMETRE DE LA METHODE */

		/* 2 FAIRE UNE LISTE DES ADRESSE ASSOCIEES AU NUMERO DE CASERNE */
		List<String> addressesAssociatedWithFirestationsNumber = new ArrayList<>();
		for (FireStation fs : fireStations) {
			if (fs.getStationNumber() == stationNumber)
				addressesAssociatedWithFirestationsNumber.add(fs.getAddress());
		}

		/* 3 COMPARER CETTE LISTE AVEC LA LISTE DES PERSONNES (ADRESS) */
		List<String> personsArray = new ArrayList<>();
		List<String> birthdateArray = new ArrayList<>();
		for (Person person : persons) {
			if (addressesAssociatedWithFirestationsNumber.contains(person.getAddress())) {
				List<String> myArray = new ArrayList<>();
				myArray = Arrays.asList(person.getFirstName(), person.getLastName(), person.getAddress(),
						person.getPhone());
				String myString = myArray.stream().collect(Collectors.joining(", "));
				personsArray.add(myString);

				/* RECUPERATION DE BIRTHDATE */
				String strFirstName = person.getFirstName();
				String strLastName = person.getLastName();

				for (MedicalRecord mr : medicalRecords) {
					String nameString = strFirstName.concat(strLastName).concat(strLastName);
					String mrNameString = mr.getFirstName().concat(mr.getLastName());
					if (nameString.contains(mrNameString)) {
						birthdateArray.add(mr.getBirthDate());
					}
				}
			}
		}

		int numberOfChildren = 0;
		int numberOfAdults = 0;
		/* CALCUL DE L'AGE */
		for (String b : birthdateArray) {
			Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(b);
			Calendar now = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			cal.setTime(dob);

			int age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
			//System.out.println(age);
			if (age <= 18) {
				numberOfChildren = numberOfChildren + 1;
			} else {
				numberOfAdults = numberOfAdults + 1;
			}
			
		}
		String numberOfChildrenToString = String.valueOf(numberOfChildren);
		String numberOfAdultsToString = String.valueOf(numberOfAdults);
		//System.out.println("Le nombre d'enfants est de : " + numberOfChildren);
		//System.out.println("Le nombre d'adultes est de : " + numberOfAdults);
		personsArray.add("Le nombre d'enfants est de : " + numberOfChildrenToString);
		personsArray.add("Le nombre d'adultes est de : " + numberOfAdultsToString);
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
