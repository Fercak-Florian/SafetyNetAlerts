package com.safetynet.safetynetalerts.workclasses;

import lombok.Data;

@Data
public class Url1 {
	private String firstName;
	private String lastName;
	private String address;
	private String phone;

	public Url1(String firstName, String lastName, String address, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}
}
