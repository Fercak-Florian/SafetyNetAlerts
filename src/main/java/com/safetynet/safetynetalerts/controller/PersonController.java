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

@RestController
public class PersonController {
	/* APPELER DES METHODES DE LA CLASSE SERVICE */

	@Autowired
	PersonRepositoryImpl personRepository;
	@Autowired
	IPersonService personService;

	// FORMAT URL : http://localhost:8080/persons/Paris/4
	@GetMapping(value = "/ville/{city}/{jojo}")
	@ResponseBody
	public String getPersonEmailFromServiceTesMethod(@PathVariable String city,
			@PathVariable(value = "jojo") int number) throws IOException {
		return (city + ":" + number);
	}

	// FORMAT URL :
	// http://localhost:8080/communityEmail/ChangeVar?city=<nom_de_la_ville>
	@RequestMapping("/communityEmail/ChangeVar")
	@ResponseBody
	public String test(@RequestParam(value = "city") String cities) throws IOException {
		return cities;
	}

	// TEST DE GETMAPPING AVEC ?
	@GetMapping("/test")
	@ResponseBody
	public String maMethodeDeTest(@RequestParam String id) {
		return "La valeur de id est : " + id;
	}

	/*
	 * URL_1 : http://localhost:8080/firestation?stationNumber=<station_number>
	 */
	@GetMapping("/firestation")
	public List<String> getPersonsCoveredByFireStationAdressFromService(@RequestParam int stationNumber)
			throws IOException, ParseException {
		return personService.getPersonsCoveredByStationNumberFromRepository(stationNumber);
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
		return personService.getPerson();
	}

	@PostMapping("/person")
	public ResponseEntity<Person> postPerson(@RequestBody Person person) {
		Person p = personService.addPersonService(person);
		if (Objects.isNull(p)) {
			return ResponseEntity.noContent().build();
		} else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/")
					.buildAndExpand(person.getAddress()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@PutMapping("/person")
	public ResponseEntity<Person> putPerson(@RequestBody Person person) {
		Person p = personService.updatePersonService(person);
		if (Objects.isNull(p)) {
			return ResponseEntity.noContent().build();
		} else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/")
					.buildAndExpand(person.getAddress()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@DeleteMapping("/person")
	public ResponseEntity<FireStation> deletePerson(@RequestBody FirstNameAndLastName combination) {
		personService.deletePersonService(combination);
		return null;
	}
}