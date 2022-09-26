package com.safetynet.safetynetalerts.workclasses;

import java.util.List;

import lombok.Data;
@Data
public class Url4 {
	private String firstName;
	private String lastName;
	private int stationNumber;
	private String phone;
	private List <String> medicationsList;
	private List <String> allergiesList;
	private String age;

	public Url4(String lastName, String firstName, int stationNumber, String phone, List <String> medicationsList, List <String> allergiesList, String age) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.stationNumber = stationNumber;
		this.phone = phone;
		this.medicationsList = medicationsList;
		this.allergiesList = allergiesList;
		this.age = age;
	}
}
