package com.safetynet.safetynetalerts.workclasses;

import java.util.ArrayList;
import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

import lombok.Data;
@Data
public class Url2 {
	private String firstName;
	private String lastName;
	private int age;
	private List<firstNameLastName> other;
	
	public Url2(String firstName, String lastName, int age, List<Person> otherPersons) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		other = new ArrayList<>();
		for(Person person : otherPersons) {
			other.add(new firstNameLastName(person.getFirstName(), person.getLastName()));
		}
	}
}

@Data
class firstNameLastName{
	private String firstName;
	private String lastName;
	
	public firstNameLastName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}