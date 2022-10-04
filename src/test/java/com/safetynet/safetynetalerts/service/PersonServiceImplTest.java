package com.safetynet.safetynetalerts.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
public class PersonServiceImplTest {

	@Autowired
	PersonServiceImpl personServiceImpl;

	@Test
	public void testGetPerson() throws Exception {
		List<Person> result = personServiceImpl.getPerson();
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetPersonsCoveredByStationNumberFromRepository() {
		List<Object> result = personServiceImpl.getPersonsCoveredByStationNumberFromRepository(3);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetPhoneNumbersCoveredByStationNumberFromRepository() {
		List<String> result = personServiceImpl.getPhoneNumbersCoveredByStationNumberFromRepository(3);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetPersonsLivingAtThisAddressWithFirestationFromRepository() throws ParseException {
		List<Url4> result = personServiceImpl.getPersonsLivingAtThisAddressWithFirestationFromRepository("1509 Culver St");
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetChildrenLivingAtThisAddressFromRepository() {
		List<Url2> result = personServiceImpl.getChildrenLivingAtThisAddressFromRepository("1509 Culver St");
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetHomesCoveredByAListOfFirestationFromRepository() {
		List<Url5> result = personServiceImpl.getHomesCoveredByAListOfFirestationFromRepository(new ArrayList<>());
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetPersonInfoFromRepository() {
		List<Url6> result = personServiceImpl.getPersonInfoFromRepository(null, null);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetPersonEmailFromRepository() {
		List<String> result = personServiceImpl.getPersonEmailFromRepository(null);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testAddPersonService() {
		Person person = new Person("Han", "Solo", "01/02/1985", "1509 Culver St","98562", "962-632-412", "hsolo@email.com");
		List<Person> result = personServiceImpl.addPersonService(person);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testUpdatePersonService() {
		Person person = new Person("Han", "Solo", "01/02/1985", "1509 Culver St","98562", "962-632-412", "hsolo@email.com");
		List<Person> result = personServiceImpl.updatePersonService(person);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testDeletePersonService() {
		FirstNameAndLastName combination = new FirstNameAndLastName("Han", "Solo");
		List<Person> result = personServiceImpl.deletePersonService(combination);
		assertThat(result).isNotNull();
	}
}
