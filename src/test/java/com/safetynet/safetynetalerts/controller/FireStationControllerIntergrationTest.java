package com.safetynet.safetynetalerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.model.FireStation;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerIntergrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetFireStationFromService() throws Exception {
		mockMvc.perform(get("/firestations")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].address", is("1509 Culver St")));
	}

	@Disabled
	@Test
	public void testPostFirestation() throws Exception {
		FireStation fsBody = new FireStation("2 rue de paris", 6);
		mockMvc.perform(post("/firestation")
				.content("2 rue de paris"+ "6"));
				//.andExpect(status().isOk()));
	}

	@Disabled
	@Test
	public void testPostFirestationWithBadRequest() throws Exception {
		mockMvc.perform(post("/firestation")).andExpect(status().isNoContent());
	}

	@Disabled
	@Test
	public void testDeleteFirestation() throws Exception {
		mockMvc.perform(delete("/firestation")).andExpect(status().isOk());
	}
}
