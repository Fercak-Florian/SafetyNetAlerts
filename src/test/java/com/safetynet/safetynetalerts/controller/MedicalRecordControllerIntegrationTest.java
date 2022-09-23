package com.safetynet.safetynetalerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testgetMedicalRecordFromService() throws Exception {
		mockMvc.perform(get("/medicalRecord")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].birthDate", is("03/06/1984")));
	}
}
