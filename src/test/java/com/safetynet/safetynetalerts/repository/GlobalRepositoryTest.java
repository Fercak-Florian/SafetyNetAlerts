package com.safetynet.safetynetalerts.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.workclasses.Url2;

@SpringBootTest
public class GlobalRepositoryTest {
	
	@Autowired
	IGlobalRepository globalRepository;
	
	@Mock
	Person person;

	@Disabled
	@Test
	public void testGetPersonsCoveredByAFirestation() {
		/* GIVEN --> ARRANGE */
		
		/* WHEN --> ACT */
		List<Object> result = globalRepository.getPersonsCoveredByAFirestation(4);
		String expected = "Lily";
		Object t = result.get(4);
		/* THEN --> ASSERT */
		
	}
	
	@Test
	public void testGetChildrenLivingAtThisAddress() {
		/* GIVEN --> ARRANGE */
		
		/* WHEN --> ACT */
		List<Url2> result = globalRepository.getChildrenLivingAtThisAddress("1509 Culver St");
		String expected = "Tenley";
		String value = result.get(0).getFirstName();
		/* THEN --> ASSERT */
		assertEquals(expected, value);
	}
	
	@Test
	public void testGetChildrenLivingAtThisAddressWithNullPerson() {
		/* GIVEN --> ARRANGE */
		
		/* WHEN --> ACT */
		Mockito.when(person.getFirstName()).thenReturn("Han");
		List<Url2> result = globalRepository.getChildrenLivingAtThisAddress("1509 Culver St");
		String expected = "Tenley";
		String value = result.get(0).getFirstName();
		System.out.println(value);
		/* THEN --> ASSERT */
		assertEquals(expected, value);
	}
}