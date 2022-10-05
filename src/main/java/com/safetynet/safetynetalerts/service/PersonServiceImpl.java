package com.safetynet.safetynetalerts.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.data.IDataReader;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

@Service
public class PersonServiceImpl implements IPersonService {
	
	
	@Autowired
	IDataReader dataReader;

	@Override
	public List<Person> getPerson() throws IOException {
		return dataReader.getPersons();
	}

	@Override
	public List<Object> getPersonsCoveredByStationNumberFromRepository(int stationNumber) {
		return dataReader.getPersonsCoveredByAFirestation(stationNumber);
	}

	@Override
	public List<String> getPhoneNumbersCoveredByStationNumberFromRepository(int stationNumber) {
		return dataReader.getPhoneNumbersCoveredByAFirestation(stationNumber);
	}

	@Override
	public List<Url4> getPersonsLivingAtThisAddressWithFirestationFromRepository(String address) throws ParseException {
		return dataReader.getPersonsLivingAtThisAddressWithFirestation(address);
	}

	@Override
	public List<Url2> getChildrenLivingAtThisAddressFromRepository(String address) {
		return dataReader.getChildrenLivingAtThisAddress(address);
	}

	@Override
	public List<Url5> getHomesCoveredByAListOfFirestationFromRepository(List<String> stations) {
		return dataReader.getHomesCoveredByAListOfFirestation(stations);
	}

	@Override
	public List<Url6> getPersonInfoFromRepository(String firstName, String lastName) {
		return dataReader.getPersonInfo(firstName, lastName);
	}

	@Override
	public List<String> getPersonEmailFromRepository(String city) {
		return dataReader.getPersonEmailByCity(city);
	}

	@Override
	public List<Person> addPersonService(Person person) {
		return dataReader.addPersonToRepository(person);
	}

	@Override
	public List<Person> updatePersonService(Person person) {
		return dataReader.updatePersonToRepository(person);
	}

	@Override
	public List<Person> deletePersonService(FirstNameAndLastName combination) {
		return dataReader.deletePersonToRepository(combination);
	}
}