package com.safetynet.safetynetalerts.repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

public interface IGlobalRepository {
	List<String> getPersonsCoveredByAFirestation(int stationNumber) throws IOException, ParseException;
	/* VERIFIER CETTE SIGNATURE */
	IPersonRepository getPersonRepository();
	List<String> getPhoneNumbersCoveredByAFirestation(int stationNumber);
}
