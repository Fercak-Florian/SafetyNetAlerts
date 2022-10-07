package com.safetynet.safetynetalerts.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

@SpringBootTest
public class PersonServiceImplIntegrationTest {

	@Autowired
	PersonServiceImpl personServiceImpl;

	@Test
	public void testGetPerson() throws Exception {
		List<Person> result = personServiceImpl.getPersons();
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetPersonsCoveredByStationNumberFromRepository() {
		List<Object> result = personServiceImpl.getPersonsCoveredByStationNumber(3);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetPhoneNumbersCoveredByStationNumberFromRepository() {
		Set<String> result = personServiceImpl.getPhoneNumbersCoveredByAStationNumber(3);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetPersonsLivingAtThisAddressWithFirestationFromRepository() throws ParseException {
		List<Url4> result = personServiceImpl.getPersonsLivingAtThisAddressWithFireStation("1509 Culver St");
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetChildrenLivingAtThisAddressFromRepository() {
		List<Url2> result = personServiceImpl.getChildrenLivingAtThisAddress("1509 Culver St");
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetHomesCoveredByAListOfFirestationFromRepository() {
		List<Url5> result = personServiceImpl.getHomesCoveredByAListOfFireStation(new ArrayList<>());
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetPersonInfoFromRepository() {
		List<Url6> result = personServiceImpl.getPersonInfo(null, null);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetPersonEmailFromRepository() {
		Set<String> result = personServiceImpl.getPersonEmail(null);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testAddPersonService() {
		Person person = new Person("Han", "Solo", "01/02/1985", "1509 Culver St","98562", "962-632-412", "hsolo@email.com");
		List<Person> result = personServiceImpl.addPerson(person);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testUpdatePersonService() {
		Person person = new Person("Han", "Solo", "01/02/1985", "1509 Culver St","98562", "962-632-412", "hsolo@email.com");
		List<Person> result = personServiceImpl.updatePerson(person);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testDeletePersonService() {
		FirstNameAndLastName combination = new FirstNameAndLastName("Han", "Solo");
		List<Person> result = personServiceImpl.deletePerson(combination);
		assertThat(result).isNotNull();
	}
}
