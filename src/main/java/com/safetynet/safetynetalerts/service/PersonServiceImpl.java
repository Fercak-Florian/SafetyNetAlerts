package com.safetynet.safetynetalerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.SafetyNetAlertsApplication;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.GlobalRepository;
import com.safetynet.safetynetalerts.repository.IGlobalRepository;
import com.safetynet.safetynetalerts.repository.IPersonRepository;

@Service
public class PersonServiceImpl implements IPersonService {
	@Autowired
	IPersonRepository personRepository;
	//@Autowired
	IGlobalRepository globalRepository = SafetyNetAlertsApplication.getGlobalRepository();

	@Override /* METHODE TEST */
	public List<Person> getPerson() throws IOException {
		return personRepository.getPersonList();
	}

	@Override
	public List<Person> getPersonsCoveredByStationNumberFromRepository(int stationNumber) throws IOException {
		return globalRepository.getPersonsCoveredByAFirestation(stationNumber);
	}
}