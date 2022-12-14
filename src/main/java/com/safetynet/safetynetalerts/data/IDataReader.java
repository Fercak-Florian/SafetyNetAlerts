package com.safetynet.safetynetalerts.data;

import java.util.List;
import java.util.Set;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

public interface IDataReader {

	void clearData();

	void addPerson(Person person);

	void addFireStation(FireStation fireStation);
	
	void addMedicaRecord(MedicalRecord medicalRecord);

	List<FireStation> getFireStations();

	List<Person> getPersons();

	List<MedicalRecord> getMedicalRecords();

	List<Object> getPersonsCoveredByAFirestation(int stationNumber);

	Set<String> getPhoneNumbersCoveredByAFirestation(int stationNumber);

	List<Url4> getPersonsLivingAtThisAddressWithFirestation(String address);

	List<Url2> getChildrenLivingAtThisAddress(String address);

	List<Url5> getHomesCoveredByAListOfFirestation(List<String> stations);

	List<Url6> getPersonInfo(String firstName, String lastName);

	Set<String> getPersonEmailByCity(String city);

	List<FireStation> addFireStationToRepository(FireStation firestation);

	List<FireStation> deleteFirestationToRepository(FireStation firestation);

	List<FireStation> updateFirestationNumberToRepository(FireStation firestation);

	List<Person> addPersonToRepository(Person person);

	List<Person> updatePersonToRepository(Person person);

	List<Person> deletePersonToRepository(FirstNameAndLastName combination);

	List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord);

	List<MedicalRecord> updateMedicalRecord(MedicalRecord medicalRecord);

	List<MedicalRecord> deleteMedicalRecord(FirstNameAndLastName combination);
}
