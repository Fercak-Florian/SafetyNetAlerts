package com.safetynet.safetynetalerts.repository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

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

	@Override
	public List<FireStation> getFirestations() {
		return fireStations;
	}

	@Override
	public List<Person> getPersons() {
		return persons;
	}

	@Override
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
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
			// System.out.println(age);
			if (age <= 18) {
				numberOfChildren = numberOfChildren + 1;
			} else {
				numberOfAdults = numberOfAdults + 1;
			}

		}
		String numberOfChildrenToString = String.valueOf(numberOfChildren);
		String numberOfAdultsToString = String.valueOf(numberOfAdults);
		// System.out.println("Le nombre d'enfants est de : " + numberOfChildren);
		// System.out.println("Le nombre d'adultes est de : " + numberOfAdults);
		personsArray.add("Le nombre d'enfants est de : " + numberOfChildrenToString);
		personsArray.add("Le nombre d'adultes est de : " + numberOfAdultsToString);
		return personsArray;
	}

	/* URL_2 LISTE DES ENFANTS VIVANTS A UNE ADRESSE */
	@Override
	public List<Url2> getChildrenLivingAtThisAddress(String address) {
		List<Url2> result = new ArrayList<>();
		for (MedicalRecord mr : medicalRecords) {
			int age = ageCalculate(mr);
			if (age <= 18) {
				for (Person person : persons) {
					if (person.getFirstName().equalsIgnoreCase(mr.getFirstName())
							&& person.getLastName().equalsIgnoreCase(mr.getLastName())) {
						if (person.getAddress().equalsIgnoreCase(address)) {
							List<Person> inhabitants = new ArrayList<>();
							for (Person p : persons) {
								if (p.getAddress().equalsIgnoreCase(address)) {
									if (p != person) {
										inhabitants.add(p);
									}
								}
							}
							result.add(new Url2(person.getFirstName(), person.getLastName(), age, inhabitants));
						}
					}
				}
			}
		}
		return result;
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

	/*
	 * URL_4 LISTE DES PERSONS VIVANT A UNE ADRESSE AISNI QUE LA FIRESTATION LES
	 * COUVRANT
	 */
	@Override
	public List<Url4> getPersonsLivingAtThisAddressWithFirestation(String address) throws ParseException {
		List<Url4> myList = new ArrayList<>();
		int stationNumber = 0;
		for (FireStation fs : fireStations) {
			if (fs.getAddress().equalsIgnoreCase(address)) {
				stationNumber = fs.getStationNumber();
			}
		}
		for (Person person : persons) {
			if (person.getAddress().equalsIgnoreCase(address)) {
				boolean medicalRecordFound = false;
				for (MedicalRecord mr : medicalRecords) {
					if (person.getFirstName().equalsIgnoreCase(mr.getFirstName())
							&& person.getLastName().equalsIgnoreCase(mr.getLastName())) {
						medicalRecordFound = true;
						myList.add(new Url4(person.getLastName(), person.getFirstName(), stationNumber,
								person.getPhone(), mr.getMedicationsList(), mr.getAllergiesList(),
								Integer.toString(ageCalculate(mr))));
					}
				}
				if (!medicalRecordFound) {
					myList.add(new Url4(person.getLastName(), person.getFirstName(), stationNumber, person.getPhone(),
							new ArrayList<String>(), new ArrayList<String>(), " "));
				}
			}
		}
		return myList;
	}

	/*
	 * URL_5 LISTE DES FOYERS COUVERTS PAR UNE LISTE DE FIRESTATION
	 */
	@Override
	public List<Url5> getHomesCoveredByAListOfFirestation(List<String> stations) {
		List<String> listOfStationAddress = new ArrayList<>();
		List<Url5> result = new ArrayList<>();
		for (String s : stations) {
			for (FireStation f : fireStations) {
				if (s.equalsIgnoreCase(String.valueOf(f.getStationNumber()))) {
					for (Person person : persons) {
						if (person.getAddress().equalsIgnoreCase(f.getAddress())) {
							for (MedicalRecord mr : medicalRecords) {
								if ((person.getFirstName().equalsIgnoreCase(mr.getFirstName())
										&& (person.getLastName().equalsIgnoreCase(mr.getLastName())))) {
									result.add(new Url5(person.getFirstName(), person.getLastName(),
											person.getAddress(), person.getPhone(), ageCalculate(mr),
											mr.getMedicationsList(), mr.getAllergiesList()));
								}
							}
						}
					}
					listOfStationAddress.add(f.getAddress());
					System.out.println(f.getAddress());
				}
			}

		}
		return result;
	}

	/*
	 * URL_6 LISTE DES INFORMATIONS EN RAPPORT AVEC UNE PERSONNE
	 */
	@Override
	public List<Url6> getPersonInfo(String firstName, String lastName) {
		List<Url6> result = new ArrayList<>();
		for (Person person : persons) {
			if ((person.getFirstName().equalsIgnoreCase(firstName))
					&& (person.getLastName().equalsIgnoreCase(lastName))) {
				for (MedicalRecord mr : medicalRecords) {
					if ((mr.getFirstName().equalsIgnoreCase(firstName))
							&& (mr.getLastName().equalsIgnoreCase(lastName))) {
						result.add(new Url6(person.getFirstName(), person.getLastName(), person.getAddress(),
								person.getEmail(), ageCalculate(mr), mr.getMedicationsList(), mr.getAllergiesList()));
					}
				}
			}
		}
		return result;
	}

	public int ageCalculate(MedicalRecord medicalRecord) {
		Date dob = new Date();
		try {
			dob = new SimpleDateFormat("dd/MM/yyyy").parse(medicalRecord.getBirthDate());
		} catch (Exception e) {
			// TODO: handle exception
		}
		Calendar now = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dob);

		int age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);

		return age;
	}

	/* CRUD POUR LES FIRESTATIONS */

	/* AJOUT D'UNE FIRESTATION AVEC POST */
	public FireStation addFireStationToRepository(FireStation firestation) {
		fireStations.add(firestation);
		return firestation;
	}

	/* MIS A JOUR DU NUMERO DE LA FIRESTATION */
	@Override
	public FireStation updateFirestationNumberToRepository(FireStation firestation) {
		FireStation result = new FireStation();
		for (FireStation fs : fireStations) {
			if (fs.getAddress().equalsIgnoreCase(firestation.getAddress())) {
				fs.setStationNumber(firestation.getStationNumber());
				result.setAddress(firestation.getAddress());
				result.setStationNumber(firestation.getStationNumber());
			}
		}
		return result;
	}

	/* SUPPRESSION D'UNE FIRESTATION AVEC DELETE */
	@Override
	public FireStation deleteFirestationToRepository(FireStation firestation) {
		fireStations.remove(firestation);
		return null;
	}

	/* CRUD POUR LES PERSONS */

	/* AJOUT D'UNE PERSON AVEC POST */
	public Person addPersonToRepository(Person person) {
		persons.add(person);
		return person;
	}

	/* MISE A JOUR D'UNE PERSON AVEC PUT */
	@Override
	public Person updatePersonToRepository(Person person) {
		for (Person p : persons) {
			if ((p.getFirstName().equalsIgnoreCase(person.getFirstName())
					&& p.getLastName().equalsIgnoreCase(person.getLastName()))) {
				p.setAddress(person.getAddress());
				p.setCity(person.getCity());
				p.setEmail(person.getEmail());
				p.setPhone(person.getPhone());
				p.setZip(person.getZip());
			}
		}
		return person;
	}

	/* SUPPRESSION D'UNE PERSON AVEC DELETE */
	@Override
	public Person deletePersonToRepository(FirstNameAndLastName combination) {
		List<Person> personsToDelete = new ArrayList<>();
		for (Person p : persons) {
			if ((p.getFirstName().equalsIgnoreCase(combination.getFirstName())
					&& p.getLastName().equalsIgnoreCase(combination.getLastName()))) {
				personsToDelete.add(p);
			}
		}

		for (Person p : personsToDelete) {
			persons.remove(p);
		}
		return null;
	}

	/* CRUD POUR LES MEDICALRECORD */
	
	/* AJOUT D'UN MEDICALRECORD AVEC POST */
	@Override
	public List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecords.add(medicalRecord);
		return medicalRecords;
	}

}
