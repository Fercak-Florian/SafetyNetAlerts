package com.safetynet.safetynetalerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Person;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetPersonFromService() throws Exception {
		mockMvc.perform(get("/person")).andExpect(status().isOk()).andExpect(jsonPath("$[0].firstName", is("John")));
	}

	@Test
	public void testGetPersonFromServiceWithBadURI() throws Exception {
		mockMvc.perform(get("/personnes")).andExpect(status().isNotFound());
	}

	@Test
	public void testPostPerson() throws Exception {
		Person person = new Person("Cassian", "Andor", "Fest", "Bordure Exterieure", "97554", "887-862-6458",
				"candor@email.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(post("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isCreated());
	}

	@Test
	public void testPostPersonWithUnproperFirstName() throws Exception {
		Person person = new Person(null, "Andor", "Fest", "Bordure Exterieure", "97554", "887-862-6458",
				"candor@email.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(post("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testPostPersonWithUnproperLastName() throws Exception {
		Person person = new Person("Cassian", null, "Fest", "Bordure Exterieure", "97554", "887-862-6458",
				"candor@email.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(post("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testPutPerson() throws Exception {
		Person person = new Person("John", "Boyd", "Fest", "Bordure Exterieure", "97554", "887-862-6458",
				"candor@email.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(put("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isCreated());
	}

	@Test
	public void testPutPersonWithUnproperFirstName() throws Exception {
		Person person = new Person(null, "Boyd", "Fest", "Bordure Exterieure", "97554", "887-862-6458",
				"candor@email.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(put("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testPutPersonWithUnproperLastName() throws Exception {
		Person person = new Person("John", null, "Fest", "Bordure Exterieure", "97554", "887-862-6458",
				"candor@email.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(put("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testDeletePerson() throws Exception {
		Person person = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451", "841-874-7458",
				"aboyd@emapeekil.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(delete("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeletePersonWithUnproperFirstName() throws Exception {
		Person person = new Person(null, "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"gramps@email.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(delete("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testDeletePersonWithUnproperLastName() throws Exception {
		Person person = new Person("John", null, "1509 Culver St", "Culver", "97451", "841-874-6512",
				"aboyd@emapeekil.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(delete("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isBadRequest());
	}
}
