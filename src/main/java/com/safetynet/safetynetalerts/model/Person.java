package com.safetynet.safetynetalerts.model;

import lombok.Data;

@Data
public class Person {
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;

	// CONSTRUCTEUR SANS ARGUMENTS
	public Person() {
	}

	// CONSTRUCTEUR AVEC TOUS LES ARGUMENTS
	public Person(String firstName, String lastName, String address, String city, String zip, String phone,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	// UNE CLASSE PERSON BUILDER
	@Data
	public static class PersonBuilder {
		private String firstName;
		private String lastName;
		private String address;
		private String city;
		private String zip;
		private String phone;
		private String email;

		// CONSTRUCTEUR SANS ARGUMENTS
		public PersonBuilder() {
		}

		// METHODE email
		public PersonBuilder email(String email) {
			this.email = email;
			return this;
		}

		public Person build() {
			return new Person(firstName, lastName, phone, zip, address, city, email);
		}
	}
}
