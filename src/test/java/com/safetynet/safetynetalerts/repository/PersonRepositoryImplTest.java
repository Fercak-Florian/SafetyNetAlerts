package com.safetynet.safetynetalerts.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryImplTest {

	@Test
	public void testGetPersonFromJson() throws Exception {
		/* GIVEN --> ARRANGE */
		IPersonRepository personRepository = new PersonRepositoryImpl();
		personRepository.setFilePath("src/main/resources/data.json");
		
		/* WHEN --> ACT */
		String result = personRepository.getPersons().get(0).getFirstName();
		String expected = "John";

		/* THEN --> ASSERT */
		assertTrue(expected.equals(result));
	}

	@Test
	public void testGetPersonWithUnproperFilepath() throws Exception {
		/* GIVEN --> ARRANGE */
		IPersonRepository personRepository = new PersonRepositoryImpl();
		personRepository.setFilePath("unproper/file/path");
		
		/* WHEN --> ACT */
		List<Person> result = personRepository.getPersons();

		/* THEN --> ASSERT */
		assertThat(result.size()).isEqualTo(0);
	}
}
