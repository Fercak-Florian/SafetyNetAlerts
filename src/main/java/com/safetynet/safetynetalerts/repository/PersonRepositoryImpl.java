package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.model.Person;

@Component
public class PersonRepositoryImpl implements IPersonRepository {

	  List<Person> personsArray;

	/* CONSTRUCTEUR */
	public PersonRepositoryImpl() throws IOException {
		getPersonFromJson();
	}

	@Override
	public List<Person> getPersonList() {
		return personsArray;
	}

	public void getPersonFromJson() throws IOException {

		if (personsArray == null) {
			personsArray = new ArrayList<>();
			String filePath = "src/main/resources/data.json";
			String stringFile = Files.readString(new File(filePath).toPath());

			JsonIterator iter = JsonIterator.parse(stringFile);
			Any any = iter.readAny();
			Any personAny = any.get("persons");

			for (Any person : personAny) {
				personsArray.add(new Person(person.get("firstName").toString(), person.get("lastName").toString(),
						person.get("address").toString(), person.get("city").toString(), person.get("zip").toString(),
						person.get("phone").toString(), person.get("email").toString()));
			}
		}
	}
}