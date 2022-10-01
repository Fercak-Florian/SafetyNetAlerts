package com.safetynet.safetynetalerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Person;
@Disabled
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	/* TESTS CONCERNANT LES URLS DEMANDEES */

	@Test
	public void testUrl1() throws Exception {
		mockMvc.perform(get("/firestation").param("stationNumber", "1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$[0].firstName").value("Peter"));
	}

	@Test
	public void testUrl2() throws Exception {
		mockMvc.perform(get("/childAlert").param("address", "1509 Culver St")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$[0].firstName").value("Tenley"));
	}

	@Test
	public void testUrl3() throws Exception {
		mockMvc.perform(get("/phoneAlert").param("firestation", "1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")).andExpect(jsonPath("$[0]").value("841-874-6512"));
	}

	@Test
	public void testUrl4() throws Exception {
		mockMvc.perform(get("/fire").param("address", "112 Steppes Pl")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$[0].firstName").value("Tony"));
	}

	@Test
	public void testUrl5() throws Exception {
		mockMvc.perform(get("/flood/stations").param("stations", "2,4")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$[0].firstName").value("Jonanathan"));
	}

	@Test
	public void testUrl6() throws Exception {
		mockMvc.perform(get("/personInfo").param("firstName", "Tessa").param("lastName", "Carman"))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$[0].address").value("834 Binoc Ave"));
	}

	@Test
	public void testUrl7() throws Exception {
		mockMvc.perform(get("/communityEmail").param("city", "Culver")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$[1]").value("drk@email.com"));
	}

	/* TESTS CONCERNANT LE CRUD DES PERSONS */

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
		Person person = new Person("", "Andor", "Fest", "Bordure Exterieure", "97554", "887-862-6458",
				"candor@email.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(post("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPostPersonWithUnproperLastName() throws Exception {
		Person person = new Person("Cassian", "", "Fest", "Bordure Exterieure", "97554", "887-862-6458",
				"candor@email.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(post("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPostPersonWithEmptyFirstNameAndLastName() throws Exception {
		Person person = new Person("", "", "Fest", "Bordure Exterieure", "97554", "887-862-6458", "candor@email.com");
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
		Person person = new Person("", "Boyd", "Fest", "Bordure Exterieure", "97554", "887-862-6458",
				"candor@email.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(put("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPutPersonWithUnproperLastName() throws Exception {
		Person person = new Person("John", "", "Fest", "Bordure Exterieure", "97554", "887-862-6458",
				"candor@email.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(put("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPutPersonWithEmptyFirstNameAndlastName() throws Exception {
		Person person = new Person("", "", "Fest", "Bordure Exterieure", "97554", "887-862-6458", "candor@email.com");
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
		Person person = new Person("", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "gramps@email.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(delete("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeletePersonWithUnproperLastName() throws Exception {
		Person person = new Person("John", "", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"aboyd@emapeekil.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(delete("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeletePersonWithEmptyFirstNameAndLastName() throws Exception {
		Person person = new Person("", "", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"aboyd@emapeekil.com");
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(delete("/person").contentType("application/json").content(om.writeValueAsString(person)))
				.andExpect(status().isBadRequest());
	}
}
