package com.safetynet.safetynetalerts.repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
import com.safetynet.safetynetalerts.workclasses.Url1;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
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
	public List<Object> getPersonsCoveredByAFirestation(int stationNumber) {
		List<Object> result = new ArrayList<>();
		int numberOfChildren = 0;
		int numberOfAdults = 0;
		for (FireStation fs : fireStations) {
			if (fs.getStationNumber() == stationNumber) {
				for (Person p : persons) {
					if (p.getAddress().equalsIgnoreCase(fs.getAddress())) {
						for (MedicalRecord mr : medicalRecords) {
							if ((mr.getFirstName().equalsIgnoreCase(p.getFirstName())
									&& mr.getLastName().equalsIgnoreCase(p.getLastName()))) {
								result.add(new Url1(p.getFirstName(), p.getLastName(), p.getAddress(), p.getPhone()));
								if (mr.isChild()) {
									numberOfChildren += 1;
								} else {
									numberOfAdults += 1;
								}
							}
						}
					}
				}
			}
		}
		result.add("Le nombre d'enfants est de : " + String.valueOf(numberOfChildren));
		result.add("Le nombre d'adultes est de : " + String.valueOf(numberOfAdults));
		return result;
	}

	/* URL_2 LISTE DES ENFANTS VIVANTS A UNE ADRESSE */
	@Override
	public List<Url2> getChildrenLivingAtThisAddress(String address) {
		List<Url2> result = new ArrayList<>();
		for (MedicalRecord mr : medicalRecords) {
			int age = mr.getAge();
			if (mr.isChild()) {
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
		List<String> result = new ArrayList<>();
		for (FireStation fs : fireStations) {
			if (fs.getStationNumber() == stationNumber) {
				for (Person p : persons) {
					if (fs.getAddress().equalsIgnoreCase(p.getAddress())) {
						result.add(p.getPhone());
					}
				}
			}
		}
		return result;
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
						myList.add(
								new Url4(person.getLastName(), person.getFirstName(), stationNumber, person.getPhone(),
										mr.getMedicationsList(), mr.getAllergiesList(), Integer.toString(mr.getAge())));
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
											person.getAddress(), person.getPhone(), mr.getAge(),
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
								person.getEmail(), mr.getAge(), mr.getMedicationsList(), mr.getAllergiesList()));
					}
				}
			}
		}
		return result;
	}

	/*
	 * URL_7 LISTE DES ADRESSES MAILS DE TOUS LES HABITANTS DE LA VILLE
	 */
	@Override /* VERIFIER L'ADEQUATION DU NOM DE LA METHODE */
	public List<String> getPersonEmailByCity(String city) {
		List<String> result = new ArrayList<>();
		for (Person p : persons)
			if (p.getCity().equalsIgnoreCase(city)) {
				result.add(p.getEmail());
			}
		return result;
	}

	/* CRUD POUR LES FIRESTATIONS */

	/* AJOUT D'UNE FIRESTATION AVEC POST */
	public List<FireStation> addFireStationToRepository(FireStation firestation) {
		fireStations.add(firestation);
		return fireStations;
	}

	/* MIS A JOUR DU NUMERO DE LA FIRESTATION */
	@Override
	public List<FireStation> updateFirestationNumberToRepository(FireStation firestation) {
		FireStation result = new FireStation();
		for (FireStation fs : fireStations) {
			if (fs.getAddress().equalsIgnoreCase(firestation.getAddress())) {
				fs.setStationNumber(firestation.getStationNumber());
				result.setAddress(firestation.getAddress());
				result.setStationNumber(firestation.getStationNumber());
				return fireStations;
			}
		}
		return fireStations;
	}

	/* SUPPRESSION D'UNE FIRESTATION AVEC DELETE */
	@Override
	public List<FireStation> deleteFirestationToRepository(FireStation firestation) {
		List<FireStation> firestationToDelete = new ArrayList<>();
		for (FireStation fs : fireStations) {
			if (fs.getAddress().equalsIgnoreCase(firestation.getAddress())
					&& fs.getStationNumber() == firestation.getStationNumber()) {
				firestationToDelete.add(firestation);
			}
		}
		for (FireStation fs : firestationToDelete) {
			fireStations.remove(fs);
		}
		return fireStations;
	}

	/* CRUD POUR LES PERSONS */

	/* AJOUT D'UNE PERSON AVEC POST */
	public List<Person> addPersonToRepository(Person person) {
		persons.add(person);
		return persons;
	}

	/* MISE A JOUR D'UNE PERSON AVEC PUT */
	@Override
	public List<Person> updatePersonToRepository(Person person) {
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
		return persons;
	}

	/* SUPPRESSION D'UNE PERSON AVEC DELETE */
	@Override
	public List<Person> deletePersonToRepository(FirstNameAndLastName combination) {
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
		return persons;
	}

	/* CRUD POUR LES MEDICALRECORD */

	/* AJOUT D'UN MEDICALRECORD AVEC POST */
	@Override
	public List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord) {
		if (Objects.isNull(medicalRecord)) {
			log.info("Erreur dans la requête");
		}
		medicalRecords.add(medicalRecord);
		return medicalRecords;
	}

	/* MISE A JOUR D'UN MEDICALRECORD AVEC PUT */
	@Override
	public List<MedicalRecord> updateMedicalRecord(MedicalRecord medicalRecord) {
		for (MedicalRecord mr : medicalRecords) {
			if ((mr.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName())
					&& mr.getLastName().equalsIgnoreCase(medicalRecord.getLastName()))) {
				mr.setBirthDate(medicalRecord.getBirthDate());
				mr.setMedicationsList(medicalRecord.getMedicationsList());
				mr.setAllergiesList(medicalRecord.getAllergiesList());
			}
		}
		return medicalRecords;
	}

	/* SUPPRESSION D'UN MEDICALRECORD AVEC DELETE */
	@Override
	public List<MedicalRecord> deleteMedicalRecord(FirstNameAndLastName combination) {
		List<MedicalRecord> medicalRecordsToDelete = new ArrayList<>();
		for (MedicalRecord mr : medicalRecords) {
			if ((mr.getFirstName().equalsIgnoreCase(combination.getFirstName())
					&& mr.getLastName().equalsIgnoreCase(combination.getLastName()))) {
				medicalRecordsToDelete.add(mr);
			}
		}

		for (MedicalRecord mr : medicalRecordsToDelete) {
			medicalRecords.remove(mr);
		}
		return medicalRecords;
	}
}
