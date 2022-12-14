package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.model.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PersonRepositoryImpl implements IPersonRepository {

	List<Person> personsArray;

	private String jsonFilePath;

	public PersonRepositoryImpl(@Value("${com.safetynet.safetynetalerts.filePath}") String jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
	}

	@Override
	public List<Person> getPersons() {
		if (personsArray == null) {
			getPersonFromJson();
		}
		return personsArray;
	}

	public void getPersonFromJson() {
		personsArray = new ArrayList<>();
		String stringFile = null;
		try {
			stringFile = Files.readString(new File(jsonFilePath).toPath());
			JsonIterator iter = JsonIterator.parse(stringFile);
			Any any = iter.readAny();
			Any personAny = any.get("persons");

			for (Any person : personAny) {
				personsArray.add(new Person(person.get("firstName").toString(), person.get("lastName").toString(),
						person.get("address").toString(), person.get("city").toString(), person.get("zip").toString(),
						person.get("phone").toString(), person.get("email").toString()));
			}
		} catch (IOException e) {
			log.error("Impossible de lire le fichier");
			e.printStackTrace();
		}
	}
}
