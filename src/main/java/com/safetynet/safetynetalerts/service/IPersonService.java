package com.safetynet.safetynetalerts.service;

import java.io.IOException;
import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

public interface IPersonService {
	List<Person> getPerson() throws IOException;
	List<Person> getPersonsCoveredByStationNumberFromRepository(int stationNumber) throws IOException;
	List<String> getPhoneNumbersCoveredByStationNumberFromRepository(int stationNumber);
}
