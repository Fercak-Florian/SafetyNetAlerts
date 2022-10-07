package com.safetynet.safetynetalerts.repository;

import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

public interface IPersonRepository {
	void setFilePath(String filePath);
	List<Person> getPersons();
}
