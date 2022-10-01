package com.safetynet.safetynetalerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
@Disabled
@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetMedicalRecordFromService() throws Exception {
		mockMvc.perform(get("/medicalRecord")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].birthDate", is("03/06/1984")));
	}

	@Test
	public void testGetMedicalRecordFromServiceWithBadURI() throws Exception {
		mockMvc.perform(get("/medicalRecords")).andExpect(status().isNotFound());
	}

	@Test
	public void testPostMedicalRecord() throws Exception {
		List<String> medications = new ArrayList<>();
		medications.add("Bacta");
		medications.add("Aspirine");

		List<String> allergies = new ArrayList<>();
		allergies.add("Ewok");
		allergies.add("Jawas");

		MedicalRecord mr = new MedicalRecord("Han", "Solo", "21/07/1987", medications, allergies);
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(post("/medicalRecord").contentType("application/json").content(om.writeValueAsString(mr)))
				.andExpect(status().isCreated());
	}

	@Test
	public void testPostMedicalRecordWithUnproperFirstName() throws Exception {
		List<String> medications = new ArrayList<>();
		medications.add("Bacta");
		medications.add("Aspirine");

		List<String> allergies = new ArrayList<>();
		allergies.add("Ewok");
		allergies.add("Jawas");

		MedicalRecord mr = new MedicalRecord("", "Solo", "21/07/1987", medications, allergies);
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(post("/medicalRecord").contentType("application/json").content(om.writeValueAsString(mr)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPostMedicalRecordWithUnproperLastName() throws Exception {
		List<String> medications = new ArrayList<>();
		medications.add("Bacta");
		medications.add("Aspirine");

		List<String> allergies = new ArrayList<>();
		allergies.add("Ewok");
		allergies.add("Jawas");

		MedicalRecord mr = new MedicalRecord("Han", "", "21/07/1987", medications, allergies);
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(post("/medicalRecord").contentType("application/json").content(om.writeValueAsString(mr)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPostMedicalRecordWithEmptyFirstNameAndLastName() throws Exception {
		List<String> medications = new ArrayList<>();
		medications.add("Bacta");
		medications.add("Aspirine");

		List<String> allergies = new ArrayList<>();
		allergies.add("Ewok");
		allergies.add("Jawas");

		MedicalRecord mr = new MedicalRecord("", "", "21/07/1987", medications, allergies);
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(post("/medicalRecord").contentType("application/json").content(om.writeValueAsString(mr)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testPutMedicalRecord() throws Exception {
		List<String> medications = new ArrayList<>();
		medications.add("Bacta");
		medications.add("Aspirine");

		List<String> allergies = new ArrayList<>();
		allergies.add("Ewok");
		allergies.add("Jawas");

		MedicalRecord mr = new MedicalRecord("John", "Boyd", "03/06/1984", medications, allergies);
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(put("/medicalRecord").contentType("application/json").content(om.writeValueAsString(mr)))
				.andExpect(status().isCreated());
	}

	@Test
	public void testPutMedicalRecordWithUnproperFirstName() throws Exception {
		List<String> medications = new ArrayList<>();
		medications.add("Bacta");
		medications.add("Aspirine");

		List<String> allergies = new ArrayList<>();
		allergies.add("Ewok");
		allergies.add("Jawas");

		MedicalRecord mr = new MedicalRecord("", "Boyd", "03/06/1984", medications, allergies);
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(put("/medicalRecord").contentType("application/json").content(om.writeValueAsString(mr)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPutMedicalRecordWithUnproperLastName() throws Exception {
		List<String> medications = new ArrayList<>();
		medications.add("Bacta");
		medications.add("Aspirine");

		List<String> allergies = new ArrayList<>();
		allergies.add("Ewok");
		allergies.add("Jawas");

		MedicalRecord mr = new MedicalRecord("John", "", "03/06/1984", medications, allergies);
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(put("/medicalRecord").contentType("application/json").content(om.writeValueAsString(mr)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPutMedicalRecordWithEmptyFirstNameAndLastName() throws Exception {
		List<String> medications = new ArrayList<>();
		medications.add("Bacta");
		medications.add("Aspirine");

		List<String> allergies = new ArrayList<>();
		allergies.add("Ewok");
		allergies.add("Jawas");

		MedicalRecord mr = new MedicalRecord("", "", "03/06/1984", medications, allergies);
		ObjectMapper om = new ObjectMapper();

		mockMvc.perform(put("/medicalRecord").contentType("application/json").content(om.writeValueAsString(mr)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testDeleteMedicalRecord() throws Exception {
		FirstNameAndLastName fl = new FirstNameAndLastName("John", "Boyd");
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(delete("/medicalRecord").contentType("application/json").content(om.writeValueAsString(fl)))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteMedicalRecordWithUnproperFirstName() throws Exception {
		FirstNameAndLastName fl = new FirstNameAndLastName("", "Boyd");
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(delete("/medicalRecord").contentType("application/json").content(om.writeValueAsString(fl)))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteMedicalRecordWithUnproperLastName() throws Exception {
		FirstNameAndLastName fl = new FirstNameAndLastName("John", "");
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(delete("/medicalRecord").contentType("application/json").content(om.writeValueAsString(fl)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteMedicalRecordWithEmptyFirstnameAndlastName() throws Exception {
		FirstNameAndLastName fl = new FirstNameAndLastName("", "");
		ObjectMapper om = new ObjectMapper();
		mockMvc.perform(delete("/medicalRecord").contentType("application/json").content(om.writeValueAsString(fl)))
				.andExpect(status().isBadRequest());
	}
}
