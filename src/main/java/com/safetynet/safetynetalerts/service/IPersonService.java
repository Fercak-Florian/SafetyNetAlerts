package com.safetynet.safetynetalerts.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

public interface IPersonService {
	List<Person> getPerson() throws IOException;
	List<String> getPersonsCoveredByStationNumberFromRepository(int stationNumber) throws IOException, ParseException;
	List<String> getPhoneNumbersCoveredByStationNumberFromRepository(int stationNumber);
	List<Url4> getPersonsLivingAtThisAddressWithFirestationFromRepository(String address) throws ParseException;
	List<Url2> getChildrenLivingAtThisAddressFromRepository(String address);
	List<Url5> getHomesCoveredByAListOfFirestationFromRepository(List<String> stations);
	List<Url6> getPersonInfoFromRepository(String firstName, String lastName);
}
