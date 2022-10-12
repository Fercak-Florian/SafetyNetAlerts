package com.safetynet.safetynetalerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.service.FireStationServiceImpl;


@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private FireStationServiceImpl fireStationService;

	@Test
	public void testGetFireStationFromService() throws Exception {
		mockMvc.perform(get("/firestations")).andExpect(status().isOk());
	}

	@Test
	public void testGetFireStationFromServiceWithBadRequest() throws Exception {
		mockMvc.perform(get("/fire")).andExpect(status().isNotFound());
	}
}