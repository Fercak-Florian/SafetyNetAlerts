package com.safetynet.safetynetalerts.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.SafetyNetAlertsApplication;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.IGlobalRepository;
import com.safetynet.safetynetalerts.repository.IPersonRepository;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;

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
	public List<String> getPersonsCoveredByStationNumberFromRepository(int stationNumber) throws IOException, ParseException {
		return globalRepository.getPersonsCoveredByAFirestation(stationNumber);
	}

	@Override
	public List<String> getPhoneNumbersCoveredByStationNumberFromRepository(int stationNumber) {
		return globalRepository.getPhoneNumbersCoveredByAFirestation(stationNumber);
	}

	@Override
	public List<Url4> getPersonsLivingAtThisAddressWithFirestationFromRepository(String address) throws ParseException {
		return globalRepository.getPersonsLivingAtThisAddressWithFirestation(address);
	}

	@Override
	public List<Url2> getChildrenLivingAtThisAddressFromRepository(String address) {
		return globalRepository.getChildrenLivingAtThisAddress(address);
	}
}