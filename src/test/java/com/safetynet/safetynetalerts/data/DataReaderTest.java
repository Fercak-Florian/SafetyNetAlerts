package com.safetynet.safetynetalerts.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.FilePaths;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

@SpringBootTest
public class DataReaderTest {

	@Autowired
	FilePaths filePaths;
	
	
	
	
	@Test
	public void testGetFirestations() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		/* WHEN --> ACT */

		List<FireStation> result = dataReader.getFirestations();
		/* THEN --> ASSERT */
		assertThat(result.get(0).getStationNumber()).isEqualTo(3);
	}
	
	@Test
	public void testGetPersons() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		/* WHEN --> ACT */

		List<Person> result = dataReader.getPersons();
		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}
	
	@Test
	public void testGetMedicalRecords() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		/* WHEN --> ACT */

		List<MedicalRecord> result = dataReader.getMedicalRecords();
		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	/* TESTS CONCERNANT L'URL_1 */

	@Test
	public void testGetPersonsCoveredByAFirestation() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		/* WHEN --> ACT */

		List<Object> result = dataReader.getPersonsCoveredByAFirestation(3);
		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(3);
	}

	@Test
	public void testGetPersonsCoveredByAFirestationWithUnknownStationNumber() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		/* WHEN --> ACT */

		List<Object> result = dataReader.getPersonsCoveredByAFirestation(1);
		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	public void testGetPersonsCoveredByAFirestationWhenNoMatchingAddress() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("29 15th St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		/* WHEN --> ACT */

		List<Object> result = dataReader.getPersonsCoveredByAFirestation(3);
		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	public void testGetPersonsCoveredByAFirestationWithOneChild() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		persons.add(
				new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "tenz@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));
		medicalRecords.add(
				new MedicalRecord("Tenley", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		/* WHEN --> ACT */

		List<Object> result = dataReader.getPersonsCoveredByAFirestation(3);
		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(4);
	}

	@Test
	public void testGetPersonsCoveredByAFirestationWithNullMedicalRecord() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		/* WHEN --> ACT */

		List<Object> result = dataReader.getPersonsCoveredByAFirestation(3);
		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	public void testGetPersonsCoveredByAFirestationWithFirstNameError() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("Jacob", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		/* WHEN --> ACT */

		List<Object> result = dataReader.getPersonsCoveredByAFirestation(3);
		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	public void testGetPersonsCoveredByAFirestationWithLastNameError() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(
				new Person("John", "Cadigan", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		/* WHEN --> ACT */

		List<Object> result = dataReader.getPersonsCoveredByAFirestation(3);
		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(2);
	}

	/* TESTS CONCERNANT L'URL_2 */

	@Test
	public void testGetChildrenLivingAtThisAddress() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("Tenley", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
		medicalRecords.add(
				new MedicalRecord("Jacob", "Boyd", "02/01/1984", new ArrayList<String>(), new ArrayList<String>()));

		List<Person> persons = new ArrayList<>();
		persons.add(
				new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		persons.add(
				new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-5631", "jboyd@email.com"));
		/* WHEN --> ACT */

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url2> result = dataReader.getChildrenLivingAtThisAddress("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void testGetChildrenLivingAtThisAddressWithUnknownPersonAdress() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("Tenley", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
		medicalRecords.add(
				new MedicalRecord("Jacob", "Boyd", "02/01/1984", new ArrayList<String>(), new ArrayList<String>()));

		List<Person> persons = new ArrayList<>();
		persons.add(
				new Person("Tenley", "Boyd", "951 LoneTree Rd", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		persons.add(
				new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-5631", "jboyd@email.com"));
		/* WHEN --> ACT */

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url2> result = dataReader.getChildrenLivingAtThisAddress("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetChildrenLivingAtThisAddressWithPersonWithOtherAdress() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("Tenley", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
		medicalRecords.add(
				new MedicalRecord("Jacob", "Boyd", "02/01/1984", new ArrayList<String>(), new ArrayList<String>()));

		List<Person> persons = new ArrayList<>();
		persons.add(
				new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		persons.add(
				new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-5631", "jboyd@email.com"));
		persons.add(
				new Person("Eric", "Cadigan", "951", "951 LoneTree Rd", "98774", "841-874-5631", "jboyd@email.com"));
		/* WHEN --> ACT */

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url2> result = dataReader.getChildrenLivingAtThisAddress("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void testGetChildrenLivingAtThisAddressWithNoMatchingNames() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(new MedicalRecord("Kendrik", "Stelzer", "02/18/2012", new ArrayList<String>(),
				new ArrayList<String>()));

		List<Person> persons = new ArrayList<>();
		persons.add(
				new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		/* WHEN --> ACT */

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url2> result = dataReader.getChildrenLivingAtThisAddress("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetChildrenLivingAtThisAddressWithNoMatchingLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("Tenley", "Stelzer", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));

		List<Person> persons = new ArrayList<>();
		persons.add(
				new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		/* WHEN --> ACT */

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url2> result = dataReader.getChildrenLivingAtThisAddress("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetChildrenLivingAtThisAddressWithNoMatchingFirstName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("Kendrik", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));

		List<Person> persons = new ArrayList<>();
		persons.add(
				new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		/* WHEN --> ACT */

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url2> result = dataReader.getChildrenLivingAtThisAddress("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	/* TESTS CONCERNANT L'URL_3 */

	@Test
	public void testGetPhoneNumbersCoveredByAFirestation() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(
				new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("Kendrik", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<String> result = dataReader.getPhoneNumbersCoveredByAFirestation(3);
		String expected = "841-874-6512";

		/* THEN --> ASSERT */
		assertThat(StringUtils.equalsIgnoreCase(result.get(0), expected)).isTrue();
	}

	@Test
	public void testGetPhoneNumbersCoveredByAFireStationWithUnknownFireStation() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(
				new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("Kendrik", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<String> result = dataReader.getPhoneNumbersCoveredByAFirestation(6);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetPhoneNumbersCoveredByAFirestationWithNoMatchingAddress() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(
				new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "98774", "841-874-6512", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("Kendrik", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<String> result = dataReader.getPhoneNumbersCoveredByAFirestation(3);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	/* TESTS CONCERNANT L'URL_4 */

	@Test
	public void testGetPersonsLivingAtThisAddressWithFirestation() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));
		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url4> result = dataReader.getPersonsLivingAtThisAddressWithFirestation("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void testGetPersonsLivingAtThisAddressWithUnKnownAddress() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));
		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url4> result = dataReader.getPersonsLivingAtThisAddressWithFirestation("951 LoneTree Rd");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetPersonsLivingAtThisAddressWithFirestationWithNoMatchingNames() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(
				new Person("Eric", "Cadigan", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url4> result = dataReader.getPersonsLivingAtThisAddressWithFirestation("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void testGetPersonsLivingAtThisAddressWithFirestationWithNoMatchingLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(
				new Person("John", "Cadigan", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url4> result = dataReader.getPersonsLivingAtThisAddressWithFirestation("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	/* TESTS CONCERNANT L'URL_5 */

	@Test
	public void testGetHomesCoveredByAListOfFirestation() throws Exception {
		/* GIVEN --> ARRANGE */
		List<String> stationNumbers = new ArrayList<>();
		stationNumbers.add("1");
		stationNumbers.add("2");

		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("644 Gershwin Cir", 1));
		fireStations.add(new FireStation("29 15th St", 2));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Peter", "Duncan", "644 Gershwin Cir", "Culver", "98774", "841-874-6512",
				"pduncan@email.com"));
		persons.add(new Person("Jonathan", "Marrack", "29 15th St", "Culver", "98774", "841-874-6521",
				"jmarrack@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("Peter", "Duncan", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));
		medicalRecords.add(new MedicalRecord("Jonathan", "Marrack", "02/18/1984", new ArrayList<String>(),
				new ArrayList<String>()));
		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url5> result = dataReader.getHomesCoveredByAListOfFirestation(stationNumbers);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	public void testGetHomesCoveredByAListOfFirestationWithNoMatchingLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<String> stationNumbers = new ArrayList<>();
		stationNumbers.add("1");
		stationNumbers.add("2");

		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("644 Gershwin Cir", 1));
		fireStations.add(new FireStation("29 15th St", 2));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Peter", "Duncan", "644 Gershwin Cir", "Culver", "98774", "841-874-6512",
				"pduncan@email.com"));
		persons.add(new Person("Jonathan", "Cadigan", "29 15th St", "Culver", "98774", "841-874-6521",
				"jmarrack@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("Peter", "Duncan", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));
		medicalRecords.add(new MedicalRecord("Jonathan", "Marrack", "02/18/1984", new ArrayList<String>(),
				new ArrayList<String>()));
		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url5> result = dataReader.getHomesCoveredByAListOfFirestation(stationNumbers);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	/* TESTS CONCERNANT L'URL_6 */

	@Test
	public void testGetPersonInfo() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url6> result = dataReader.getPersonInfo("John", "Boyd");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void testGetPersonInfoWithNoMathingNames() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("Eric", "Cadigan", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url6> result = dataReader.getPersonInfo("John", "Boyd");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetPersonInfoWithNoMathingLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(
				new MedicalRecord("John", "Cadigan", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url6> result = dataReader.getPersonInfo("John", "Boyd");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetPersonInfoWithNoMatchingNamesWithInMedicalRecords() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url6> result = dataReader.getPersonInfo("Eric", "Cadigan");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetPersonInfoWithNoMatchingLastNameWithInMedicalRecords() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Url6> result = dataReader.getPersonInfo("John", "Cadigan");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	/* TESTS CONCERNANT L'URL_7 */

	@Test
	public void testGetPersonEmailByCity() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("1509 Culver St", 3));

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<String> result = dataReader.getPersonEmailByCity("Culver");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	/* TEST CONCERNANT LE CRUD POUR LES FIRESTATIONS */
	/* TEST POUR AJOUT FIRESTATION */

	@Test
	public void testAddFireStationToRepository() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		FireStation fireStationToAdd = new FireStation("1509 Culver St", 3);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<FireStation> result = dataReader.addFireStationToRepository(fireStationToAdd);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getStationNumber()).isEqualTo(3);
	}

	@Test
	public void testAddFireStationToRepositoryWithUnproperStationNumber() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		FireStation fireStationToAdd = new FireStation("1509 Culver St", 0);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<FireStation> result = dataReader.addFireStationToRepository(fireStationToAdd);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testAddFireStationToRepositoryWithUnproperFireStationAddress() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		FireStation fireStationToAdd = new FireStation("", 3);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<FireStation> result = dataReader.addFireStationToRepository(fireStationToAdd);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	/* TEST POUR MODIFICATION FIRESTATION */

	@Test
	public void testUpdateFirestationNumberToRepository() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("29 15th St", 2));
		fireStations.add(new FireStation("1509 Culver St", 3));
		FireStation fireStationToUpdate = new FireStation("1509 Culver St", 6);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<FireStation> result = dataReader.updateFirestationNumberToRepository(fireStationToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.contains(fireStationToUpdate)).isTrue();
	}

	@Test
	public void testUpdateFirestationNumberToRepositoryWithNullAddress() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("29 15th St", 2));
		fireStations.add(new FireStation("1509 Culver St", 3));
		FireStation fireStationToUpdate = new FireStation("", 6);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<FireStation> result = dataReader.updateFirestationNumberToRepository(fireStationToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.contains(fireStationToUpdate)).isFalse();
	}

	@Test
	public void testUpdateFirestationNumberToRepositoryWhenStationNumberIsZero() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("29 15th St", 2));
		fireStations.add(new FireStation("1509 Culver St", 3));
		FireStation fireStationToUpdate = new FireStation("1509 Culver St", 0);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<FireStation> result = dataReader.updateFirestationNumberToRepository(fireStationToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.contains(fireStationToUpdate)).isFalse();
	}

	@Test
	public void testUpdateFirestationNumberToRepositoryWithEmptyFireStationsList() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		FireStation fireStationToUpdate = new FireStation("1509 Culver St", 6);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<FireStation> result = dataReader.updateFirestationNumberToRepository(fireStationToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.contains(fireStationToUpdate)).isFalse();
	}

	/* TEST POUR SUPPRESSION FIRESTATION */

	@Test
	public void testDeleteFirestationToRepository() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("29 15th St", 2));
		fireStations.add(new FireStation("1509 Culver St", 3));
		FireStation fireStationToDelete = new FireStation("1509 Culver St", 3);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<FireStation> result = dataReader.deleteFirestationToRepository(fireStationToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getAddress()).isEqualTo("29 15th St");
	}

	@Test
	public void testDeleteFirestationToRepositoryWhenFireStationIsEmpty() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("29 15th St", 2));
		fireStations.add(new FireStation("1509 Culver St", 3));
		FireStation fireStationToDelete = new FireStation("", 3);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<FireStation> result = dataReader.deleteFirestationToRepository(fireStationToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(1).getAddress()).isEqualTo("1509 Culver St");
	}

	@Test
	public void testDeleteFirestationToRepositoryWhenStationNumberIsZero() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("29 15th St", 2));
		fireStations.add(new FireStation("1509 Culver St", 3));
		FireStation fireStationToDelete = new FireStation("1509 Culver St", 0);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<FireStation> result = dataReader.deleteFirestationToRepository(fireStationToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(1).getAddress()).isEqualTo("1509 Culver St");
	}

	@Test
	public void testDeleteFirestationToRepositoryWithUnknownStationNumber() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(new FireStation("29 15th St", 2));
		fireStations.add(new FireStation("1509 Culver St", 3));
		FireStation fireStationToDelete = new FireStation("1509 Culver St", 6);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords
				.add(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<FireStation> result = dataReader.deleteFirestationToRepository(fireStationToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(1).getAddress()).isEqualTo("1509 Culver St");
	}

	/* TEST CONCERNANT LE CRUD POUR LES PERSONS */
	/* TEST POUR AJOUT PERSON */

	@Test
	public void testAddPersonToRepository() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		Person personToAdd = new Person("John", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874",
				"jboyd@email.com");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.addPersonToRepository(personToAdd);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	@Test
	public void testAddPersonToRepositoryWithEmptyFirstName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		Person personToAdd = new Person("", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874",
				"jboyd@email.com");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.addPersonToRepository(personToAdd);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testAddPersonToRepositoryWithEmptyLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		Person personToAdd = new Person("John", "", "1509 Culver St", "Culver", "98556", "001-596-874",
				"jboyd@email.com");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.addPersonToRepository(personToAdd);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	/* TEST POUR MODIFICATION PERSON */

	@Test
	public void testUpdatePersonToRepository() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		Person personToUpdate = new Person("John", "Boyd", "2 rue de Paris", "Paris", "75012", "+336 08 09 70 50",
				"john.boyd@email.com");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.updatePersonToRepository(personToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getCity()).isEqualTo("Paris");
	}

	@Test
	public void testUpdatePersonToRepositoryWithEmptyFirstName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		Person personToUpdate = new Person("", "Boyd", "2 rue de Paris", "Paris", "75012", "+336 08 09 70 50",
				"john.boyd@email.com");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.updatePersonToRepository(personToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getCity()).isEqualTo("Culver");
	}

	@Test
	public void testUpdatePersonToRepositoryWithEmptyLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		Person personToUpdate = new Person("John", "", "2 rue de Paris", "Paris", "75012", "+336 08 09 70 50",
				"john.boyd@email.com");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.updatePersonToRepository(personToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getCity()).isEqualTo("Culver");
	}

	@Test
	public void testUpdatePersonToRepositoryWithNoMatchingFirstName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		Person personToUpdate = new Person("Eric", "Boyd", "2 rue de Paris", "Paris", "75012", "+336 08 09 70 50",
				"john.boyd@email.com");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.updatePersonToRepository(personToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getCity()).isEqualTo("Culver");
	}

	@Test
	public void testUpdatePersonToRepositoryWithNoMatchingLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		Person personToUpdate = new Person("John", "Cadigan", "2 rue de Paris", "Paris", "75012", "+336 08 09 70 50",
				"john.boyd@email.com");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.updatePersonToRepository(personToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getCity()).isEqualTo("Culver");
	}

	/* TEST POUR SUPPRESSION PERSON */

	@Test
	public void testDeletePersonToRepository() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		FirstNameAndLastName personToDelete = new FirstNameAndLastName("John", "Boyd");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.deletePersonToRepository(personToDelete);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testDeletePersonToRepositoryWithEmptyFisrtName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		FirstNameAndLastName personToDelete = new FirstNameAndLastName("", "Boyd");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.deletePersonToRepository(personToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	@Test
	public void testDeletePersonToRepositoryWithEmptyLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		FirstNameAndLastName personToDelete = new FirstNameAndLastName("John", "");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.deletePersonToRepository(personToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	@Test
	public void testDeletePersonToRepositoryWithNoMatchingFirstName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		FirstNameAndLastName personToDelete = new FirstNameAndLastName("Eric", "Boyd");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.deletePersonToRepository(personToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	@Test
	public void testDeletePersonToRepositoryWithNoMatchingLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		FirstNameAndLastName personToDelete = new FirstNameAndLastName("John", "Cadigan");

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		List<MedicalRecord> medicalRecords = new ArrayList<>();

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<Person> result = dataReader.deletePersonToRepository(personToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	/* TEST CONCERNANT LE CRUD POUR LES MEDICALRECORDS */
	/* TEST POUR AJOUT MEDICALRECORDS */

	@Test
	public void testAddMedicalRecord() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		MedicalRecord medicalRecordToAdd = new MedicalRecord("Eric", "Cadigan", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.addMedicalRecord(medicalRecordToAdd);

		/* THEN --> ASSERT */
		assertThat(result.contains(medicalRecordToAdd));
	}

	@Test
	public void testAddMedicalRecordWithEmptyFirstName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		MedicalRecord medicalRecordToAdd = new MedicalRecord("", "Cadigan", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.addMedicalRecord(medicalRecordToAdd);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void testAddMedicalRecordWithEmptyLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		MedicalRecord medicalRecordToAdd = new MedicalRecord("Eric", "", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.addMedicalRecord(medicalRecordToAdd);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	/* TEST POUR MODIFICATION MEDICALRECORDS */

	@Test
	public void testUpdateMedicalRecord() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		MedicalRecord medicalRecordToUpdate = new MedicalRecord("John", "Boyd", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.updateMedicalRecord(medicalRecordToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getAllergiesList().get(0)).isEqualTo("fruits de mer");
	}

	@Test
	public void testUpdateMedicalRecordWithEmptyFirstName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		MedicalRecord medicalRecordToUpdate = new MedicalRecord("", "Boyd", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.updateMedicalRecord(medicalRecordToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getAllergiesList()).isEmpty();
	}

	@Test
	public void testUpdateMedicalRecordWithEmptyLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		MedicalRecord medicalRecordToUpdate = new MedicalRecord("John", "", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.updateMedicalRecord(medicalRecordToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getAllergiesList()).isEmpty();
	}

	@Test
	public void testUpdateMedicalRecordWithNoMatchingFirstName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		MedicalRecord medicalRecordToUpdate = new MedicalRecord("Eric", "Boyd", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.updateMedicalRecord(medicalRecordToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getAllergiesList()).isEmpty();
	}

	@Test
	public void testUpdateMedicalRecordWithNoMatchingLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		MedicalRecord medicalRecordToUpdate = new MedicalRecord("John", "Cadigan", "01/02/1984", medications,
				allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.updateMedicalRecord(medicalRecordToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getAllergiesList()).isEmpty();
	}

	/* TEST POUR SUPPRESSION MEDICALRECORDS */

	@Test
	public void testDeleteMedicalRecord() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		FirstNameAndLastName combination = new FirstNameAndLastName("John", "Boyd");
		
		MedicalRecord medicalRecordToDelete = new MedicalRecord("John", "Boyd", "01/02/1984", medications,
				allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecordToDelete);

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.deleteMedicalRecord(combination);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}
	
	@Test
	public void testDeleteMedicalRecordWithEmptyFirstName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		FirstNameAndLastName combination = new FirstNameAndLastName("", "Boyd");
		
		MedicalRecord medicalRecordToDelete = new MedicalRecord("John", "Boyd", "01/02/1984", medications,
				allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecordToDelete);

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.deleteMedicalRecord(combination);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}
	
	@Test
	public void testDeleteMedicalRecordWithEmptyLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		FirstNameAndLastName combination = new FirstNameAndLastName("John", "");
		
		MedicalRecord medicalRecordToDelete = new MedicalRecord("John", "Boyd", "01/02/1984", medications,
				allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecordToDelete);

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.deleteMedicalRecord(combination);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}
	
	@Test
	public void testDeleteMedicalRecordWithNoMatchingFirstName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		FirstNameAndLastName combination = new FirstNameAndLastName("Eric", "Boyd");
		
		MedicalRecord medicalRecordToDelete = new MedicalRecord("John", "Boyd", "01/02/1984", medications,
				allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecordToDelete);

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.deleteMedicalRecord(combination);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}
	
	@Test
	public void testDeleteMedicalRecordWithNoMatchingLastName() throws Exception {
		/* GIVEN --> ARRANGE */
		List<FireStation> fireStations = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");

		FirstNameAndLastName combination = new FirstNameAndLastName("John", "Cadigan");
		
		MedicalRecord medicalRecordToDelete = new MedicalRecord("John", "Boyd", "01/02/1984", medications,
				allergies);

		/* WHEN --> ACT */
		List<Person> persons = new ArrayList<>();

		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecordToDelete);

		IDataReader dataReader = new DataReader(persons, fireStations, medicalRecords);
		List<MedicalRecord> result = dataReader.deleteMedicalRecord(combination);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}
}