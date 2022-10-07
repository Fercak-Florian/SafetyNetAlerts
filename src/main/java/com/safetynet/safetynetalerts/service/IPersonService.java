package com.safetynet.safetynetalerts.service;

import java.util.List;
import java.util.Set;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

public interface IPersonService {

	List<Person> getPersons();

	List<Object> getPersonsCoveredByStationNumber(int stationNumber);

	Set<String> getPhoneNumbersCoveredByAStationNumber(int stationNumber);

	List<Url4> getPersonsLivingAtThisAddressWithFireStation(String address);

	List<Url2> getChildrenLivingAtThisAddress(String address);

	List<Url5> getHomesCoveredByAListOfFireStation(List<String> stations);

	List<Url6> getPersonInfo(String firstName, String lastName);

	Set<String> getPersonEmail(String city);

	List<Person> addPerson(Person person);

	List<Person> updatePerson(Person person);

	List<Person> deletePerson(FirstNameAndLastName combination);
}
