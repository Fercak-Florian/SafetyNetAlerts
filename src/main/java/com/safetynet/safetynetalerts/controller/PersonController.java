package com.safetynet.safetynetalerts.controller;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.IPersonService;
import com.safetynet.safetynetalerts.workclasses.EmptyJsonResponse;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;
import com.safetynet.safetynetalerts.workclasses.Url2;
import com.safetynet.safetynetalerts.workclasses.Url4;
import com.safetynet.safetynetalerts.workclasses.Url5;
import com.safetynet.safetynetalerts.workclasses.Url6;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PersonController {

	@Autowired
	IPersonService personService;

	/*
	 * URL_1 : http://localhost:8080/firestation?stationNumber=<station_number>
	 */
	@GetMapping("/firestation")
	public List<Object> getPersonsCoveredByFireStationAddress(@RequestParam int stationNumber) {
		List<Object> result = personService.getPersonsCoveredByStationNumber(stationNumber);
		log.info("La requete à réussie");
		return result;
	}

	/*
	 * URL_2 : http://localhost:8080/childAlert?address=<address>
	 */
	@GetMapping("/childAlert")
	public List<Url2> getChildrenLivingAtThisAddress(@RequestParam String address) {
		log.info("La requete à réussie");
		return personService.getChildrenLivingAtThisAddress(address);
	}

	/*
	 * URL_3 : http://localhost:8080/phoneAlert?firestation=<firestation_number>
	 */
	@GetMapping("/phoneAlert")
	public Set<String> getPhoneNumbersCoveredByAStationNumber(@RequestParam(value = "firestation") int stationNumber) {
		log.info("La requete à réussie");
		return personService.getPhoneNumbersCoveredByAStationNumber(stationNumber);
	}

	/*
	 * URL_4 : http://localhost:8080/fire?address=<address>
	 */
	@GetMapping("/fire")
	public List<Url4> getPersonsLivingAtThisAddressWithFireStation(@RequestParam String address) {
		log.info("La requete à réussie");
		return personService.getPersonsLivingAtThisAddressWithFireStation(address);
	}

	/*
	 * URL_5 : http://localhost:8080/flood/stations?stations=<a list of
	 * station_numbers>
	 */
	@GetMapping("/flood/stations")
	public List<Url5> getHomesCoveredByAListOfFireStation(@RequestParam List<String> stations) {
		log.info("La requete à réussie");
		return personService.getHomesCoveredByAListOfFireStation(stations);
	}

	/*
	 * URL_6 :
	 * http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	 */
	@GetMapping("/personInfo")
	public List<Url6> getPersonInfo(@RequestParam String firstName, String lastName) {
		log.info("La requete à réussie");
		return personService.getPersonInfo(firstName, lastName);

	}

	/*
	 * URL_7 : http://localhost:8080/communityEmail?city=<city>
	 */
	@GetMapping("/communityEmail")
	public Set<String> getPersonEmail(@RequestParam String city) {
		log.info("La requete à réussie");
		return personService.getPersonEmail(city);
	}

	/* CRUD POUR PERSONS */

	@GetMapping("/persons")
	public List<Person> getPersons() {
		log.info("Récuperation de toutes les personnes");
		return personService.getPersons();
	}

	@PostMapping("/person")
	public ResponseEntity<Person> postPerson(@RequestBody(required = true) Person person) {
		if (StringUtils.isEmpty(person.getFirstName()) && StringUtils.isEmpty(person.getLastName())) {
			log.error("Impossible de créer cette personne");
			return ResponseEntity.badRequest().build();
		} else {
			List<Person> persons = personService.addPerson(person);
			if (persons.contains(person)) {
				log.info("La personne suivante à été créée : {}", person);
				return ResponseEntity.status(HttpStatus.CREATED).body(person);
			}
			log.error("Erreur lors de la création d'une personne");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(person);
		}
	}

	@PutMapping("/person")
	public ResponseEntity<Person> putPerson(@RequestBody(required = true) Person person) {
		if (StringUtils.isEmpty(person.getFirstName()) && StringUtils.isEmpty(person.getLastName())) {
			log.error("Impossible de modifier cette personne");
			return ResponseEntity.badRequest().build();
		} else {
			List<Person> persons = personService.updatePerson(person);
			if (persons.contains(person)) {
				log.info("La personne suivante à été mise à jour : {}", person);
				return ResponseEntity.status(HttpStatus.CREATED).body(person);
			}
			log.error("Erreur lors de la modification de la personne");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(person);
		}
	}

	@DeleteMapping("/person")
	public ResponseEntity<FirstNameAndLastName> deletePerson(
			@RequestBody(required = true) FirstNameAndLastName combination) {
		if (StringUtils.isEmpty(combination.getFirstName()) && StringUtils.isEmpty(combination.getLastName())) {
			log.error("Impossible de supprimer cette personne");
			return ResponseEntity.badRequest().build();
		} else {
			List<Person> persons = personService.deletePerson(combination);
			if (persons.contains(combination)) {
				log.error("Erreur lors de la suppression de la personne");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(combination);
			}
			log.info("Cette personne à été supprimée : {}", combination);
			return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
		}
	}
}
