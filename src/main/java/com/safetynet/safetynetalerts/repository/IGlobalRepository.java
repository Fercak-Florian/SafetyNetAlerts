package com.safetynet.safetynetalerts.repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

public interface IGlobalRepository {

	List<FireStation> getFirestations();

	List<Person> getPersons();

	List<String> getPersonsCoveredByAFirestation(int stationNumber) throws IOException, ParseException;

	/* VERIFIER CETTE SIGNATURE */
	IPersonRepository getPersonRepository();

	List<String> getPhoneNumbersCoveredByAFirestation(int stationNumber);

	List<Url4> getPersonsLivingAtThisAddressWithFirestation(String address) throws ParseException;

	List<Url2> getChildrenLivingAtThisAddress(String address);

	List<Url5> getHomesCoveredByAListOfFirestation(List<String> stations);

	List<Url6> getPersonInfo(String firstName, String lastName);

	FireStation addFireStationToRepository(FireStation firestation);

	FireStation deleteFirestationToRepository(FireStation firestation);

	FireStation updateFirestationNumberToRepository(FireStation firestation);
	
	Person addPersonToRepository(Person person);
	
	Person updatePersonToRepository(Person person);
}
