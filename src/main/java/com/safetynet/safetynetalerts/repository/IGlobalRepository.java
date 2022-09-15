package com.safetynet.safetynetalerts.repository;

import java.io.IOException;
import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

public interface IGlobalRepository {
	List<Person> getPersonsCoveredByAFirestation(int stationNumber) throws IOException;
	/* VERIFIER CETTE SIGNATURE */
	IPersonRepository getPersonRepository();
	List<String> getPhoneNumbersCoveredByAFirestation(int stationNumber);
}
