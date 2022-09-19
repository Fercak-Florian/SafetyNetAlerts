package com.safetynet.safetynetalerts.workclasses;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Url6 {
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private int age;
	private List<String> medications;
	private List<String> allergies;
	
	public Url6(String firstName,String lastName, String address, String email, int age, List<String> medicationsList, List<String> allergiesList) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.age = age;
		this.medications = new ArrayList<>();
		for(String m : medicationsList) {
			medications.add(m);
		}
		this.allergies = new ArrayList<>();
		for(String a : allergiesList) {
			allergies.add(a);
		}
	}
}
