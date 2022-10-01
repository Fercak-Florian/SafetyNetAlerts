package com.safetynet.safetynetalerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.FireStation;
@Disabled
@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetFireStationFromService() throws Exception {
		mockMvc.perform(get("/firestations")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].address", is("1509 Culver St")));
	}

	@Test
	public void testPostFirestation() throws Exception {
		FireStation fs = new FireStation("2 rue de Paris", 6);
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(post("/firestation").contentType("application/json").content(om.writeValueAsString(fs)))
				.andExpect(status().isCreated());
	}

	@Test
	public void testPostFirestationWithUnproperStationNumber() throws Exception {
		FireStation fs = new FireStation("2 rue de Paris", 0);
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(post("/firestation").contentType("application/json").content(om.writeValueAsString(fs)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPostFirestationWithUnproperAddress() throws Exception {
		FireStation fs = new FireStation("", 6);
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(post("/firestation").contentType("application/json").content(om.writeValueAsString(fs)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPostFirestationWithNothing() throws Exception {
		FireStation fs = new FireStation("", 0);
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(post("/firestation").contentType("application/json").content(om.writeValueAsString(fs)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testPutFirestation() throws Exception {
		FireStation fs = new FireStation("112 Steppes Pl", 1);
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(put("/firestation").contentType("application/json").content(om.writeValueAsString(fs)))
				.andExpect(status().isCreated());
	}

	@Test
	public void testPutFirestationWithUnproperStationNumber() throws Exception {
		FireStation fs = new FireStation("112 Steppes Pl", 0);
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(put("/firestation").contentType("application/json").content(om.writeValueAsString(fs)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPutFirestationWithUnproperAddress() throws Exception {
		FireStation fs = new FireStation("", 1);
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(put("/firestation").contentType("application/json").content(om.writeValueAsString(fs)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPutFirestationWithEmptyAddressAndZeroStationNumber() throws Exception {
		FireStation fs = new FireStation("", 0);
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(put("/firestation").contentType("application/json").content(om.writeValueAsString(fs)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testDeleteFirestation() throws Exception {
		FireStation fs = new FireStation("748 Townings Dr", 3);
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(delete("/firestation").contentType("application/json").content(om.writeValueAsString(fs)))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteFirestationWithUnproperStationNumber() throws Exception {
		FireStation fs = new FireStation("748 Townings Dr", 0);
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(delete("/firestation").contentType("application/json").content(om.writeValueAsString(fs)))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteFirestationWithUnproperAddress() throws Exception {
		FireStation fs = new FireStation("", 3);
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(delete("/firestation").contentType("application/json").content(om.writeValueAsString(fs)))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteFirestationWithEmpyAddressAndZeroStationNumber() throws Exception {
		FireStation fs = new FireStation("", 0);
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(delete("/firestation").contentType("application/json").content(om.writeValueAsString(fs)))
				.andExpect(status().isBadRequest());
	}
}
