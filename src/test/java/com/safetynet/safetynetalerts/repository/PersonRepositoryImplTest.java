package com.safetynet.safetynetalerts.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonRepositoryImplTest {

	@Autowired
	IPersonRepository personRepository;

	@Test
	public void testGetPersonFromJson() throws Exception {
		/* GIVEN --> ARRANGE */

		/* WHEN --> ACT */
		String result = personRepository.getPersonList().get(0).getFirstName();
		String expected = "John";

		/* THEN --> ASSERT */
		assertTrue(expected.equals(result));
	}
}
