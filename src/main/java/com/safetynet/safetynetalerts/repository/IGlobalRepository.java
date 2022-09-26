package com.safetynet.safetynetalerts.repository;

import java.text.ParseException;
import java.util.List;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

public interface IGlobalRepository {

	List<FireStation> getFirestations();

	List<Person> getPersons();

	List<MedicalRecord> getMedicalRecords();

	List<Object> getPersonsCoveredByAFirestation(int stationNumber);

	/* VERIFIER CETTE SIGNATURE */
	IPersonRepository getPersonRepository();

	List<String> getPhoneNumbersCoveredByAFirestation(int stationNumber);

	List<Url4> getPersonsLivingAtThisAddressWithFirestation(String address) throws ParseException;

	List<Url2> getChildrenLivingAtThisAddress(String address);

	List<Url5> getHomesCoveredByAListOfFirestation(List<String> stations);

	List<Url6> getPersonInfo(String firstName, String lastName);

	List<String> getPersonEmailByCity(String city);

	List<FireStation> addFireStationToRepository(FireStation firestation);

	List<FireStation> deleteFirestationToRepository(FireStation firestation);

	List<FireStation> updateFirestationNumberToRepository(FireStation firestation);

	Person addPersonToRepository(Person person);

	Person updatePersonToRepository(Person person);

	Person deletePersonToRepository(FirstNameAndLastName combination);

	List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord);

	List<MedicalRecord> updateMedicalRecord(MedicalRecord medicalRecord);

	List<MedicalRecord> deleteMedicalRecord(FirstNameAndLastName combination);
}
