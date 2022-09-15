package com.safetynet.safetynetalerts.repository;

import java.io.IOException;
import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

public interface IPersonRepository {
	List<Person> getPersonList();
	List<String> getPersonEmailFromJson(String city) throws IOException;
}
