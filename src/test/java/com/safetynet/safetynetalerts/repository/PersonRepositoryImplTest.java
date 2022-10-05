package com.safetynet.safetynetalerts.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryImplTest {

	FilePaths filePaths = new FilePaths();

	@Test
	public void testGetPersonFromJson() throws Exception {
		/* GIVEN --> ARRANGE */
		IPersonRepository personRepository = new PersonRepositoryImpl();
		/* WHEN --> ACT */
		String result = personRepository.getPersonList().get(0).getFirstName();
		String expected = "John";

		/* THEN --> ASSERT */
		assertTrue(expected.equals(result));
	}
}
