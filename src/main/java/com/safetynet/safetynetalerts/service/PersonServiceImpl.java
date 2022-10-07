package com.safetynet.safetynetalerts.service;

import java.util.List;
import java.util.Set;

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
	public List<Person> getPersons() {
		return dataReader.getPersons();
	}

	@Override
	public List<Object> getPersonsCoveredByStationNumber(int stationNumber) {
		return dataReader.getPersonsCoveredByAFirestation(stationNumber);
	}

	@Override
	public Set<String> getPhoneNumbersCoveredByAStationNumber(int stationNumber) {
		return dataReader.getPhoneNumbersCoveredByAFirestation(stationNumber);
	}

	@Override
	public List<Url4> getPersonsLivingAtThisAddressWithFireStation(String address) {
		return dataReader.getPersonsLivingAtThisAddressWithFirestation(address);
	}

	@Override
	public List<Url2> getChildrenLivingAtThisAddress(String address) {
		return dataReader.getChildrenLivingAtThisAddress(address);
	}

	@Override
	public List<Url5> getHomesCoveredByAListOfFireStation(List<String> stations) {
		return dataReader.getHomesCoveredByAListOfFirestation(stations);
	}

	@Override
	public List<Url6> getPersonInfo(String firstName, String lastName) {
		return dataReader.getPersonInfo(firstName, lastName);
	}

	@Override
	public Set<String> getPersonEmail(String city) {
		return dataReader.getPersonEmailByCity(city);
	}

	@Override
	public List<Person> addPerson(Person person) {
		return dataReader.addPersonToRepository(person);
	}

	@Override
	public List<Person> updatePerson(Person person) {
		return dataReader.updatePersonToRepository(person);
	}

	@Override
	public List<Person> deletePerson(FirstNameAndLastName combination) {
		return dataReader.deletePersonToRepository(combination);
	}
}