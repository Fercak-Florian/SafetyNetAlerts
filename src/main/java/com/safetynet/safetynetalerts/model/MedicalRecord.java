package com.safetynet.safetynetalerts.model;

import java.util.ArrayList;
import java.util.List;

import com.jsoniter.any.Any;

import lombok.Data;

@Data
public class MedicalRecord {
	private String firstName;
	private String lastName;
	private String birthDate;
	private List<String> medicationsList;
	private List<String> allergiesList;

	/* CONSTRUCTEUR */
	public MedicalRecord(String firstName, String lastName, String birthDate, List<String> medications,
			List<String> allergies) {

		medicationsList = new ArrayList<>();
		allergiesList = new ArrayList<>();

		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;

		this.medicationsList = medications;
		this.allergiesList = allergies;
	}
}
