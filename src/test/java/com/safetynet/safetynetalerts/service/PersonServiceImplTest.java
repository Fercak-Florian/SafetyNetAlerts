package com.safetynet.safetynetalerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.data.IDataReader;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {

	private IPersonService personService;

	@Mock
	private IDataReader dataReader;

	@BeforeEach
	public void init() {
		personService = new PersonServiceImpl(dataReader);
	}

	@Test
	public void testGetPersons() {
		when(dataReader.getPersons()).thenReturn(Arrays.asList(
				new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jboyd@email.com")));
		List<Person> result = personService.getPersons();
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
		verify(dataReader).getPersons();
	}

	@Test
	public void testAddPerson() {
		when(dataReader.addPersonToRepository(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451",
				"841-874-7458", "ecadigan@email.com")))
				.thenReturn(Arrays.asList(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
						"841-874-6512", "jboyd@email.com")));
		List<Person> result = personService.addPerson(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
				"97451", "841-874-7458", "ecadigan@email.com"));
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
		verify(dataReader).addPersonToRepository(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451",
				"841-874-7458", "ecadigan@email.com"));
	}

	@Test
	public void testUpdatePerson() {
		when(dataReader.updatePersonToRepository(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451",
				"841-874-7458", "ecadigan@email.com")))
				.thenReturn(Arrays.asList(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451",
						"841-874-6512", "jboyd@email.com")));
		List<Person> result = personService.updatePerson(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver",
				"97451", "841-874-7458", "ecadigan@email.com"));
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
		verify(dataReader).updatePersonToRepository(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451",
				"841-874-7458", "ecadigan@email.com"));
	}

	@Test
	public void testDeletePerson() {
		when(dataReader.deletePersonToRepository(new FirstNameAndLastName("Eric", "Cadigan"))).thenReturn(Arrays.asList(
				new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jboyd@email.com")));
		List<Person> result = personService.deletePerson(new FirstNameAndLastName("Eric", "Cadigan"));
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
		verify(dataReader).deletePersonToRepository(new FirstNameAndLastName("Eric", "Cadigan"));
	}

	@Test
	public void testGetPersonsCoveredByStationNumber() {
		when(dataReader.getPersonsCoveredByAFirestation(1)).thenReturn(Arrays.asList(
				new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jboyd@email.com")));
		List<Object> result = personService.getPersonsCoveredByStationNumber(1);
		assertThat(result.contains("John"));
		verify(dataReader).getPersonsCoveredByAFirestation(1);
	}

	@Test
	public void testGetPhoneNumbersCoveredByAStationNumber() {
		Set<String> hashSet = new HashSet<>();
		hashSet.add("841-874-6512");
		when(dataReader.getPhoneNumbersCoveredByAFirestation(1)).thenReturn(hashSet);
		Set<String> result = personService.getPhoneNumbersCoveredByAStationNumber(1);
		assertThat(result.contains("841-874-6512"));
		verify(dataReader).getPhoneNumbersCoveredByAFirestation(1);
	}

	@Test
	public void testGetPersonsLivingAtThisAddressWithFireStation() {
		when(dataReader.getPersonsLivingAtThisAddressWithFirestation("1509 Culver St")).thenReturn(
				Arrays.asList(new Url4("John", "Boyd", 1, "841-874-6512", new ArrayList<>(), new ArrayList<>(), "40")));
		List<Url4> result = personService.getPersonsLivingAtThisAddressWithFireStation("1509 Culver St");
		assertThat(result.get(0).getAge()).isEqualTo("40");
		verify(dataReader).getPersonsLivingAtThisAddressWithFirestation("1509 Culver St");
	}

	@Test
	public void testGetChildrenLivingAtThisAddress() {
		when(dataReader.getChildrenLivingAtThisAddress("1509 Culver St"))
				.thenReturn(Arrays.asList(new Url2("John", "Boyd", 40, new ArrayList<>())));
		List<Url2> result = personService.getChildrenLivingAtThisAddress("1509 Culver St");
		assertThat(result.get(0).getAge()).isEqualTo(40);
		verify(dataReader).getChildrenLivingAtThisAddress("1509 Culver St");
	}

	@Test
	public void testGetHomesCoveredByAListOfFireStation() {
		List<String> stations = new ArrayList<>();
		stations.add("1");
		stations.add("3");
		when(dataReader.getHomesCoveredByAListOfFirestation(stations)).thenReturn(Arrays.asList(
				new Url5("John", "Boyd", "1509 Culver St", "841-874-6512", 40, new ArrayList<>(), new ArrayList<>())));
		List<Url5> result = personService.getHomesCoveredByAListOfFireStation(stations);
		assertThat(result.get(0).getAge()).isEqualTo(40);
		verify(dataReader).getHomesCoveredByAListOfFirestation(stations);
	}

	@Test
	public void testGetPersonInfo() {
		when(dataReader.getPersonInfo("Eric", "Cadigan")).thenReturn(Arrays.asList(new Url6("John", "Boyd",
				"1509 Culver St", "jboyd@email.com", 40, new ArrayList<>(), new ArrayList<>())));
		List<Url6> result = personService.getPersonInfo("Eric", "Cadigan");
		assertThat(result.get(0).getAge()).isEqualTo(40);
		verify(dataReader).getPersonInfo("Eric", "Cadigan");
	}

	@Test
	public void testGetPersonEmail() {
		Set<String> hashSet = new HashSet<>();
		hashSet.add("jboyd@email.com");
		when(dataReader.getPersonEmailByCity("Culver")).thenReturn(hashSet);
		Set<String> result = personService.getPersonEmail("Culver");
		assertThat(result.contains("jboyd@email.com"));
		verify(dataReader).getPersonEmailByCity("Culver");
	}
}