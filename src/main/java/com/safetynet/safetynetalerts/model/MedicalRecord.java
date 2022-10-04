package com.safetynet.safetynetalerts.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
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

	@JsonIgnore
	public int getAge() {
		Date dob = new Date();
		try {
			dob = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Calendar now = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dob);

		int age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);

		return age;
	}
	@JsonIgnore
	public boolean isAdult() {
		return (getAge() > 18);
	}
	@JsonIgnore
	public boolean isChild() {
		return (!isAdult());
	}
}
