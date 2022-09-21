package com.safetynet.safetynetalerts.controller;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonRepositoryImpl;
import com.safetynet.safetynetalerts.service.IPersonService;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class PersonController {
	/* APPELER DES METHODES DE LA CLASSE SERVICE */

	@Autowired
	PersonRepositoryImpl personRepository;
	@Autowired
	IPersonService personService;

	/*
	 * URL_1 : http://localhost:8080/firestation?stationNumber=<station_number>
	 */
	@GetMapping("/firestation")
	public List<String> getPersonsCoveredByFireStationAdressFromService(@RequestParam int stationNumber)
			throws IOException, ParseException {
		List<String> result = personService.getPersonsCoveredByStationNumberFromRepository(stationNumber);
		return result;
	}

	/*
	 * URL_2 : http://localhost:8080/childAlert?address=<address>
	 */
	@GetMapping("/childAlert")
	public List<Url2> getChildrenLivingAtThisAddress(@RequestParam String address) {
		return personService.getChildrenLivingAtThisAddressFromRepository(address);
	}

	/*
	 * URL_3 : http://localhost:8080/phoneAlert?firestation=<firestation_number>
	 */
	@GetMapping("/phoneAlert")
	public List<String> getPhoneNumbersCoveredByStationNumberFromService(
			@RequestParam(value = "firestation") int stationNumber) {
		return personService.getPhoneNumbersCoveredByStationNumberFromRepository(stationNumber);
	}

	/*
	 * URL_4 : http://localhost:8080/fire?address=<address>
	 */
	@GetMapping("/fire")
	public List<Url4> getPersonsLivingAtThisAdressWithFireStation(@RequestParam String address) throws ParseException {
		return personService.getPersonsLivingAtThisAddressWithFirestationFromRepository(address);
	}

	/*
	 * URL_5 : http://localhost:8080/flood/stations?stations=<a list of
	 * station_numbers>
	 */
	@GetMapping("/flood/stations")
	public List<Url5> getHomesCoveredByAListOfFirestationFromService(@RequestParam List<String> stations) {
		return personService.getHomesCoveredByAListOfFirestationFromRepository(stations);
	}

	/*
	 * URL_6 :
	 * http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	 */
	@GetMapping("/personInfo")
	public List<Url6> getPersonInfoFromService(@RequestParam String firstName, String lastName) {
		return personService.getPersonInfoFromRepository(firstName, lastName);

	}

	/*
	 * URL_7 : http://localhost:8080/communityEmail?city=<city>
	 */
	@GetMapping("/communityEmail")
	public List<String> getPersonEmailFromService(@RequestParam String city) throws IOException {
		return personRepository.getPersonEmailFromJson(city);
	}

	/* CRUD POUR PERSONS */

	@GetMapping("/person")
	public List<Person> getPersonFromService() throws IOException {
		log.info("Récuperation de toutes les personnes");
		return personService.getPerson();
	}

	@PostMapping("/person")
	public ResponseEntity<Person> postPerson(@RequestBody Person person) {
		Person p = personService.addPersonService(person);
		if (Objects.isNull(p)) {
			log.info("Erreur lors de la creation de la personne");
			return ResponseEntity.noContent().build();
		} else {
			log.info("La personne suivante à été créée : {}", person);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/")
					.buildAndExpand(person.getAddress()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@PutMapping("/person")
	public ResponseEntity<Person> putPerson(@RequestBody Person person) {
		Person p = personService.updatePersonService(person);
		if (Objects.isNull(p)) {
			log.info("Erreur lors de la mise à jour de la personne");
			return ResponseEntity.noContent().build();
		} else {
			log.info("La personne suivante à été mise à jour : {}", person);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/")
					.buildAndExpand(person.getAddress()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@DeleteMapping("/person")
	public ResponseEntity<FireStation> deletePerson(@RequestBody FirstNameAndLastName combination) {
		log.info("La personne suivante à été supprimée : {}", combination);
		personService.deletePersonService(combination);
		return null;
	}
}