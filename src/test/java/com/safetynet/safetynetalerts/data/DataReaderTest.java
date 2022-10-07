package com.safetynet.safetynetalerts.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

@ExtendWith(MockitoExtension.class)
public class DataReaderTest {

	IDataReader dataReader = new DataReader();
	
	@BeforeEach
	public void init() {
		dataReader.clearData();
	}

	@Test
	public void testGetFirestations() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(
				new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicalRecord(
				new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<FireStation> result = dataReader.getFirestations();

		/* THEN --> ASSERT */
		assertThat(result.get(0).getStationNumber()).isEqualTo(3);
	}

	@Test
	public void testGetPersons() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(
				new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicalRecord(
				new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<Person> result = dataReader.getPersons();

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	@Test
	public void testGetMedicalRecords() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(
				new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicalRecord(
				new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.getMedicalRecords();

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	/* TESTS CONCERNANT L'URL_1 */

	@Test
	public void testGetPersonsCoveredByAFirestation() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(
				(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com")));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicalRecord(
				new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<Object> result = dataReader.getPersonsCoveredByAFirestation(3);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(3);
	}

	@Test
	public void testGetPersonsCoveredByAFirestationWithUnknownStationNumber() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(
				new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicalRecord(
				new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<Object> result = dataReader.getPersonsCoveredByAFirestation(1);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	public void testGetPersonsCoveredByAFirestationWhenNoMatchingAddress() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(
				new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("29 15th St", 3));
		dataReader.addMedicalRecord(
				new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<Object> result = dataReader.getPersonsCoveredByAFirestation(3);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	public void testGetPersonsCoveredByAFirestationWithOneChild() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		dataReader.addPerson(new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));
		dataReader.addMedicaRecord(new MedicalRecord("Tenley", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<Object> result = dataReader.getPersonsCoveredByAFirestation(3);
		
		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(4);
	}

	@Test
	public void testGetPersonsCoveredByAFirestationWithNullMedicalRecord() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("", "", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));
		
		/* WHEN --> ACT */
		List<Object> result = dataReader.getPersonsCoveredByAFirestation(3);
		
		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	public void testGetPersonsCoveredByAFirestationWithFirstNameError() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("Jacob", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));
		
		/* WHEN --> ACT */
		List<Object> result = dataReader.getPersonsCoveredByAFirestation(3);
		
		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	public void testGetPersonsCoveredByAFirestationWithLastNameError() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Cadigan", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("John", "Boyd", "21/08/1988", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<Object> result = dataReader.getPersonsCoveredByAFirestation(3);
		
		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(2);
	}

	/* TESTS CONCERNANT L'URL_2 */

	@Test
	public void testGetChildrenLivingAtThisAddress() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-5631", "jboyd@email.com"));
		dataReader.addPerson(new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("Jacob", "Boyd", "02/01/1984", new ArrayList<String>(), new ArrayList<String>()));
		dataReader.addMedicaRecord(new MedicalRecord("Tenley", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
				
		/* WHEN --> ACT */
		List<Url2> result = dataReader.getChildrenLivingAtThisAddress("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void testGetChildrenLivingAtThisAddressWithUnknownPersonAdress() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-5631", "jboyd@email.com"));
		dataReader.addPerson(new Person("Tenley", "Boyd", "951 LoneTree Rd", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("Jacob", "Boyd", "02/01/1984", new ArrayList<String>(), new ArrayList<String>()));
		dataReader.addMedicaRecord(new MedicalRecord("Tenley", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
				
		/* WHEN --> ACT */
		List<Url2> result = dataReader.getChildrenLivingAtThisAddress("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetChildrenLivingAtThisAddressWithPersonWithOtherAdress() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("Eric", "Cadigan", "951", "951 LoneTree Rd", "98774", "841-874-5631", "jboyd@email.com"));
		dataReader.addPerson(new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-5631", "jboyd@email.com"));
		dataReader.addPerson(new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("Tenley", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
		dataReader.addMedicaRecord(new MedicalRecord("Jacob", "Boyd", "02/01/1984", new ArrayList<String>(), new ArrayList<String>()));
				
		/* WHEN --> ACT */
		List<Url2> result = dataReader.getChildrenLivingAtThisAddress("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void testGetChildrenLivingAtThisAddressWithNoMatchingNames() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("Kendrik", "Stelzer", "02/18/2012", new ArrayList<String>(),
				new ArrayList<String>()));
		
		/* WHEN --> ACT */
		List<Url2> result = dataReader.getChildrenLivingAtThisAddress("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetChildrenLivingAtThisAddressWithNoMatchingLastName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("Tenley", "Stelzer", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
				
		/* WHEN --> ACT */
		List<Url2> result = dataReader.getChildrenLivingAtThisAddress("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetChildrenLivingAtThisAddressWithNoMatchingFirstName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "tenz@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("Kendrik", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
				
		/* WHEN --> ACT */
		List<Url2> result = dataReader.getChildrenLivingAtThisAddress("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	/* TESTS CONCERNANT L'URL_3 */

	@Test
	public void testGetPhoneNumbersCoveredByAFirestation() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("Kendrik", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
				
		/* WHEN --> ACT */
		Set<String> result = dataReader.getPhoneNumbersCoveredByAFirestation(3);
		String expected = "841-874-6512";

		/* THEN --> ASSERT */
		assertThat(result.contains(expected)).isTrue();
	}

	@Test
	public void testGetPhoneNumbersCoveredByAFireStationWithUnknownFireStation() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("Kendrik", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
				
		/* WHEN --> ACT */
		Set<String> result = dataReader.getPhoneNumbersCoveredByAFirestation(6);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetPhoneNumbersCoveredByAFirestationWithNoMatchingAddress() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "98774", "841-874-6512", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("Kendrik", "Boyd", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
				
		/* WHEN --> ACT */
		Set<String> result = dataReader.getPhoneNumbersCoveredByAFirestation(3);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	/* TESTS CONCERNANT L'URL_4 */

	@Test
	public void testGetPersonsLivingAtThisAddressWithFirestation() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));
		
		/* WHEN --> ACT */
		List<Url4> result = dataReader.getPersonsLivingAtThisAddressWithFirestation("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void testGetPersonsLivingAtThisAddressWithUnKnownAddress() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));
		
		/* WHEN --> ACT */
		List<Url4> result = dataReader.getPersonsLivingAtThisAddressWithFirestation("951 LoneTree Rd");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetPersonsLivingAtThisAddressWithFirestationWithNoMatchingNames() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("Eric", "Cadigan", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<Url4> result = dataReader.getPersonsLivingAtThisAddressWithFirestation("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void testGetPersonsLivingAtThisAddressWithFirestationWithNoMatchingLastName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Cadigan", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<Url4> result = dataReader.getPersonsLivingAtThisAddressWithFirestation("1509 Culver St");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	/* TESTS CONCERNANT L'URL_5 */

	@Test
	public void testGetHomesCoveredByAListOfFirestation() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("Peter", "Duncan", "644 Gershwin Cir", "Culver", "98774", "841-874-6512",
				"pduncan@email.com"));
		dataReader.addPerson(new Person("Jonathan", "Marrack", "29 15th St", "Culver", "98774", "841-874-6521",
				"jmarrack@email.com"));
		dataReader.addFireStation(new FireStation("644 Gershwin Cir", 1));
		dataReader.addFireStation(new FireStation("29 15th St", 2));
		dataReader.addMedicaRecord(new MedicalRecord("Jonathan", "Marrack", "02/18/1984", new ArrayList<String>(),
				new ArrayList<String>()));
		dataReader.addMedicaRecord(new MedicalRecord("Peter", "Duncan", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));
		List<String> stationNumbers = new ArrayList<>();
		stationNumbers.add("1");
		stationNumbers.add("2");

		/* WHEN --> ACT */
		List<Url5> result = dataReader.getHomesCoveredByAListOfFirestation(stationNumbers);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	public void testGetHomesCoveredByAListOfFirestationWithNoMatchingLastName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("Peter", "Duncan", "644 Gershwin Cir", "Culver", "98774", "841-874-6512",
				"pduncan@email.com"));
		dataReader.addPerson(new Person("Jonathan", "Cadigan", "29 15th St", "Culver", "98774", "841-874-6521",
				"jmarrack@email.com"));
		dataReader.addFireStation(new FireStation("644 Gershwin Cir", 1));
		dataReader.addFireStation(new FireStation("29 15th St", 2));
		dataReader.addMedicaRecord(new MedicalRecord("Jonathan", "Marrack", "02/18/1984", new ArrayList<String>(),
				new ArrayList<String>()));
		dataReader.addMedicaRecord(new MedicalRecord("Peter", "Duncan", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));
		List<String> stationNumbers = new ArrayList<>();
		stationNumbers.add("1");
		stationNumbers.add("2");

		/* WHEN --> ACT */
		List<Url5> result = dataReader.getHomesCoveredByAListOfFirestation(stationNumbers);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	/* TESTS CONCERNANT L'URL_6 */

	@Test
	public void testGetPersonInfo() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<Url6> result = dataReader.getPersonInfo("John", "Boyd");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void testGetPersonInfoWithNoMathingNames() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("Eric", "Cadigan", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<Url6> result = dataReader.getPersonInfo("John", "Boyd");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetPersonInfoWithNoMathingLastName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("John", "Cadigan", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<Url6> result = dataReader.getPersonInfo("John", "Boyd");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetPersonInfoWithNoMatchingNamesWithInMedicalRecords() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));
	
		/* WHEN --> ACT */
		List<Url6> result = dataReader.getPersonInfo("Eric", "Cadigan");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testGetPersonInfoWithNoMatchingLastNameWithInMedicalRecords() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "841-874-6512", "jboyd@email.com"));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addMedicaRecord(new MedicalRecord("John", "Boyd", "02/18/1984", new ArrayList<String>(), new ArrayList<String>()));

		/* WHEN --> ACT */
		List<Url6> result = dataReader.getPersonInfo("John", "Cadigan");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	/* TESTS CONCERNANT L'URL_7 */

	@Test
	public void testGetPersonEmailByCity() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));

		/* WHEN --> ACT */
		Set<String> result = dataReader.getPersonEmailByCity("Culver");

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(1);
	}

	/* TEST CONCERNANT LE CRUD POUR LES FIRESTATIONS */
	/* TEST POUR AJOUT FIRESTATION */

	@Test
	public void testAddFireStationToRepository() {
		/* GIVEN --> ARRANGE */
		FireStation fireStationToAdd = new FireStation("1509 Culver St", 3);

		/* WHEN --> ACT */
		List<FireStation> result = dataReader.addFireStationToRepository(fireStationToAdd);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getStationNumber()).isEqualTo(3);
	}

	@Test
	public void testAddFireStationToRepositoryWithUnproperStationNumber() {
		/* GIVEN --> ARRANGE */
		FireStation fireStationToAdd = new FireStation("1509 Culver St", 0);

		/* WHEN --> ACT */
		List<FireStation> result = dataReader.addFireStationToRepository(fireStationToAdd);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testAddFireStationToRepositoryWithUnproperFireStationAddress() {
		/* GIVEN --> ARRANGE */
		FireStation fireStationToAdd = new FireStation("", 3);

		/* WHEN --> ACT */
		List<FireStation> result = dataReader.addFireStationToRepository(fireStationToAdd);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	/* TEST POUR MODIFICATION FIRESTATION */

	@Test
	public void testUpdateFirestationNumberToRepository() {
		/* GIVEN --> ARRANGE */
		dataReader.addFireStation(new FireStation("29 15th St", 2));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		FireStation fireStationToUpdate = new FireStation("1509 Culver St", 6);

		/* WHEN --> ACT */
		List<FireStation> result = dataReader.updateFirestationNumberToRepository(fireStationToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.contains(fireStationToUpdate)).isTrue();
	}

	@Test
	public void testUpdateFirestationNumberToRepositoryWithNullAddress() {
		/* GIVEN --> ARRANGE */
		dataReader.addFireStation(new FireStation("29 15th St", 2));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		FireStation fireStationToUpdate = new FireStation("", 6);

		/* WHEN --> ACT */
		List<FireStation> result = dataReader.updateFirestationNumberToRepository(fireStationToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.contains(fireStationToUpdate)).isFalse();
	}

	@Test
	public void testUpdateFirestationNumberToRepositoryWhenStationNumberIsZero() {
		/* GIVEN --> ARRANGE */
		dataReader.addFireStation(new FireStation("29 15th St", 2));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		FireStation fireStationToUpdate = new FireStation("1509 Culver St", 0);

		/* WHEN --> ACT */
		List<FireStation> result = dataReader.updateFirestationNumberToRepository(fireStationToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.contains(fireStationToUpdate)).isFalse();
	}

	@Test
	public void testUpdateFirestationNumberToRepositoryWithEmptyFireStationsList() {
		/* GIVEN --> ARRANGE */
		FireStation fireStationToUpdate = new FireStation("1509 Culver St", 6);

		/* WHEN --> ACT */
		List<FireStation> result = dataReader.updateFirestationNumberToRepository(fireStationToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.contains(fireStationToUpdate)).isFalse();
	}

	/* TEST POUR SUPPRESSION FIRESTATION */

	@Test
	public void testDeleteFirestationToRepository() {
		/* GIVEN --> ARRANGE */
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		dataReader.addFireStation(new FireStation("29 15th St", 2));
		FireStation fireStationToDelete = new FireStation("1509 Culver St", 3);

		/* WHEN --> ACT */
		List<FireStation> result = dataReader.deleteFirestationToRepository(fireStationToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getAddress()).isEqualTo("29 15th St");
	}

	@Test
	public void testDeleteFirestationToRepositoryWhenFireStationIsEmpty() {
		/* GIVEN --> ARRANGE */
		dataReader.addFireStation(new FireStation("29 15th St", 2));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		FireStation fireStationToDelete = new FireStation("", 3);

		/* WHEN --> ACT */
		List<FireStation> result = dataReader.deleteFirestationToRepository(fireStationToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(1).getAddress()).isEqualTo("1509 Culver St");
	}

	@Test
	public void testDeleteFirestationToRepositoryWhenStationNumberIsZero() {
		/* GIVEN --> ARRANGE */
		dataReader.addFireStation(new FireStation("29 15th St", 2));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		FireStation fireStationToDelete = new FireStation("1509 Culver St", 0);

		/* WHEN --> ACT */
		List<FireStation> result = dataReader.deleteFirestationToRepository(fireStationToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(1).getAddress()).isEqualTo("1509 Culver St");
	}

	@Test
	public void testDeleteFirestationToRepositoryWithUnknownStationNumber() {
		/* GIVEN --> ARRANGE */
		dataReader.addFireStation(new FireStation("29 15th St", 2));
		dataReader.addFireStation(new FireStation("1509 Culver St", 3));
		FireStation fireStationToDelete = new FireStation("1509 Culver St", 6);

		/* WHEN --> ACT */
		List<FireStation> result = dataReader.deleteFirestationToRepository(fireStationToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(1).getAddress()).isEqualTo("1509 Culver St");
	}

	/* TEST CONCERNANT LE CRUD POUR LES PERSONS */
	/* TEST POUR AJOUT PERSON */

	@Test
	public void testAddPersonToRepository() {
		/* GIVEN --> ARRANGE */
		Person personToAdd = new Person("John", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874",
				"jboyd@email.com");

		/* WHEN --> ACT */
		List<Person> result = dataReader.addPersonToRepository(personToAdd);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	@Test
	public void testAddPersonToRepositoryWithEmptyFirstName() {
		/* GIVEN --> ARRANGE */
		Person personToAdd = new Person("", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874",
				"jboyd@email.com");

		/* WHEN --> ACT */
		List<Person> result = dataReader.addPersonToRepository(personToAdd);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testAddPersonToRepositoryWithEmptyLastName() {
		/* GIVEN --> ARRANGE */
		Person personToAdd = new Person("John", "", "1509 Culver St", "Culver", "98556", "001-596-874",
				"jboyd@email.com");

		/* WHEN --> ACT */
		List<Person> result = dataReader.addPersonToRepository(personToAdd);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	/* TEST POUR MODIFICATION PERSON */

	@Test
	public void testUpdatePersonToRepository() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874", "jboyd@email.com"));
		Person personToUpdate = new Person("John", "Boyd", "2 rue de Paris", "Paris", "75012", "+336 08 09 70 50",
				"john.boyd@email.com");

		/* WHEN --> ACT */
		List<Person> result = dataReader.updatePersonToRepository(personToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getCity()).isEqualTo("Paris");
	}

	@Test
	public void testUpdatePersonToRepositoryWithEmptyFirstName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874", "jboyd@email.com"));
		Person personToUpdate = new Person("", "Boyd", "2 rue de Paris", "Paris", "75012", "+336 08 09 70 50",
				"john.boyd@email.com");

		/* WHEN --> ACT */
		List<Person> result = dataReader.updatePersonToRepository(personToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getCity()).isEqualTo("Culver");
	}

	@Test
	public void testUpdatePersonToRepositoryWithEmptyLastName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874", "jboyd@email.com"));
		Person personToUpdate = new Person("John", "", "2 rue de Paris", "Paris", "75012", "+336 08 09 70 50",
				"john.boyd@email.com");

		/* WHEN --> ACT */
		List<Person> result = dataReader.updatePersonToRepository(personToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getCity()).isEqualTo("Culver");
	}

	@Test
	public void testUpdatePersonToRepositoryWithNoMatchingFirstName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874", "jboyd@email.com"));
		Person personToUpdate = new Person("Eric", "Boyd", "2 rue de Paris", "Paris", "75012", "+336 08 09 70 50",
				"john.boyd@email.com");

		/* WHEN --> ACT */
		List<Person> result = dataReader.updatePersonToRepository(personToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getCity()).isEqualTo("Culver");
	}

	@Test
	public void testUpdatePersonToRepositoryWithNoMatchingLastName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98556", "001-596-874", "jboyd@email.com"));
		Person personToUpdate = new Person("John", "Cadigan", "2 rue de Paris", "Paris", "75012", "+336 08 09 70 50",
				"john.boyd@email.com");

		/* WHEN --> ACT */
		List<Person> result = dataReader.updatePersonToRepository(personToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getCity()).isEqualTo("Culver");
	}

	/* TEST POUR SUPPRESSION PERSON */

	@Test
	public void testDeletePersonToRepository() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		FirstNameAndLastName personToDelete = new FirstNameAndLastName("John", "Boyd");

		/* WHEN --> ACT */
		List<Person> result = dataReader.deletePersonToRepository(personToDelete);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testDeletePersonToRepositoryWithEmptyFisrtName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		FirstNameAndLastName personToDelete = new FirstNameAndLastName("", "Boyd");

		/* WHEN --> ACT */
		List<Person> result = dataReader.deletePersonToRepository(personToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	@Test
	public void testDeletePersonToRepositoryWithEmptyLastName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		FirstNameAndLastName personToDelete = new FirstNameAndLastName("John", "");

		/* WHEN --> ACT */
		List<Person> result = dataReader.deletePersonToRepository(personToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	@Test
	public void testDeletePersonToRepositoryWithNoMatchingFirstName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		FirstNameAndLastName personToDelete = new FirstNameAndLastName("Eric", "Boyd");

		/* WHEN --> ACT */
		List<Person> result = dataReader.deletePersonToRepository(personToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	@Test
	public void testDeletePersonToRepositoryWithNoMatchingLastName() {
		/* GIVEN --> ARRANGE */
		dataReader.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "98774", "069 258 255", "jboyd@email.com"));
		FirstNameAndLastName personToDelete = new FirstNameAndLastName("John", "Cadigan");

		/* WHEN --> ACT */
		List<Person> result = dataReader.deletePersonToRepository(personToDelete);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	/* TEST CONCERNANT LE CRUD POUR LES MEDICALRECORDS */
	/* TEST POUR AJOUT MEDICALRECORDS */

	@Test
	public void testAddMedicalRecord() {
		/* GIVEN --> ARRANGE */
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");
		MedicalRecord medicalRecordToAdd = new MedicalRecord("Eric", "Cadigan", "01/02/1984", medications, allergies);
		

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.addMedicalRecord(medicalRecordToAdd);

		/* THEN --> ASSERT */
		assertThat(result.contains(medicalRecordToAdd));
	}

	@Test
	public void testAddMedicalRecordWithEmptyFirstName() {
		/* GIVEN --> ARRANGE */
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");
		MedicalRecord medicalRecordToAdd = new MedicalRecord("", "Cadigan", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.addMedicalRecord(medicalRecordToAdd);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testAddMedicalRecordWithEmptyLastName() {
		/* GIVEN --> ARRANGE */
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");
		MedicalRecord medicalRecordToAdd = new MedicalRecord("Eric", "", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.addMedicalRecord(medicalRecordToAdd);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	/* TEST POUR MODIFICATION MEDICALRECORDS */

	@Test
	public void testUpdateMedicalRecord() {
		/* GIVEN --> ARRANGE */
		dataReader.addMedicalRecord(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");
		MedicalRecord medicalRecordToUpdate = new MedicalRecord("John", "Boyd", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.updateMedicalRecord(medicalRecordToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getAllergiesList().get(0)).isEqualTo("fruits de mer");
	}

	@Test
	public void testUpdateMedicalRecordWithEmptyFirstName() {
		/* GIVEN --> ARRANGE */
		dataReader.addMedicalRecord(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");
		MedicalRecord medicalRecordToUpdate = new MedicalRecord("", "Boyd", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.updateMedicalRecord(medicalRecordToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getAllergiesList()).isEmpty();
	}

	@Test
	public void testUpdateMedicalRecordWithEmptyLastName() {
		/* GIVEN --> ARRANGE */
		dataReader.addMedicalRecord(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");
		MedicalRecord medicalRecordToUpdate = new MedicalRecord("John", "", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.updateMedicalRecord(medicalRecordToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getAllergiesList()).isEmpty();
	}

	@Test
	public void testUpdateMedicalRecordWithNoMatchingFirstName() {
		/* GIVEN --> ARRANGE */
		dataReader.addMedicalRecord(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");
		MedicalRecord medicalRecordToUpdate = new MedicalRecord("Eric", "Boyd", "01/02/1984", medications, allergies);

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.updateMedicalRecord(medicalRecordToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getAllergiesList()).isEmpty();
	}

	@Test
	public void testUpdateMedicalRecordWithNoMatchingLastName() {
		/* GIVEN --> ARRANGE */
		dataReader.addMedicalRecord(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("Aspirine");
		medications.add("Doliprane");
		allergies.add("fruits de mer");
		MedicalRecord medicalRecordToUpdate = new MedicalRecord("John", "Cadigan", "01/02/1984", medications,
				allergies);

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.updateMedicalRecord(medicalRecordToUpdate);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getAllergiesList()).isEmpty();
	}

	/* TEST POUR SUPPRESSION MEDICALRECORDS */

	@Test
	public void testDeleteMedicalRecord() {
		/* GIVEN --> ARRANGE */
		dataReader.addMedicalRecord(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));
		FirstNameAndLastName combination = new FirstNameAndLastName("John", "Boyd");
		
		/* THEN --> ASSERT */
		List<MedicalRecord> result = dataReader.deleteMedicalRecord(combination);

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void testDeleteMedicalRecordWithEmptyFirstName() {
		/* GIVEN --> ARRANGE */
		dataReader.addMedicalRecord(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));
		FirstNameAndLastName combination = new FirstNameAndLastName("", "Boyd");

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.deleteMedicalRecord(combination);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	@Test
	public void testDeleteMedicalRecordWithEmptyLastName() {
		/* GIVEN --> ARRANGE */
		dataReader.addMedicalRecord(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));
		FirstNameAndLastName combination = new FirstNameAndLastName("John", "");

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.deleteMedicalRecord(combination);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	@Test
	public void testDeleteMedicalRecordWithNoMatchingFirstName() {
		/* GIVEN --> ARRANGE */
		dataReader.addMedicalRecord(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));
		FirstNameAndLastName combination = new FirstNameAndLastName("Eric", "Boyd");

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.deleteMedicalRecord(combination);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}

	@Test
	public void testDeleteMedicalRecordWithNoMatchingLastName() {
		/* GIVEN --> ARRANGE */
		dataReader.addMedicalRecord(new MedicalRecord("John", "Boyd", "01/02/1984", new ArrayList<>(), new ArrayList<>()));
		FirstNameAndLastName combination = new FirstNameAndLastName("John", "Cadigan");

		/* WHEN --> ACT */
		List<MedicalRecord> result = dataReader.deleteMedicalRecord(combination);

		/* THEN --> ASSERT */
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
	}
}