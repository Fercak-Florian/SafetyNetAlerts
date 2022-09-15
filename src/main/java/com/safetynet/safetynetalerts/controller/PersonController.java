package com.safetynet.safetynetalerts.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonRepositoryImpl;
import com.safetynet.safetynetalerts.service.IPersonService;

@RestController
public class PersonController {
	/* APPELER DES METHODES DE LA CLASSE SERVICE */

	@Autowired
	PersonRepositoryImpl personRepository;
	@Autowired
	IPersonService personService;

	@GetMapping("/persons")
	public List<Person> getPersonFromService() throws IOException {
		return personService.getPerson();
	}

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

	/* URL DEMANDEE --> http://localhost:8080/communityEmail?city=<city> */
	@GetMapping("/communityEmail")
	public List<String> getPersonEmailFromService(@RequestParam String city) throws IOException {
		return personRepository.getPersonEmailFromJson(city);
	}

	/*
	 * URL DEMANDEE -->
	 * http://localhost:8080/firestation?stationNumber=<station_number>
	 */
	@GetMapping("/firestation")
	public List<Person> getPersonsCoveredByFireStationAdressFromService(@RequestParam int stationNumber)
			throws IOException {
		return personService.getPersonsCoveredByStationNumberFromRepository(stationNumber);
	}

	/*
	 * URL DEMANDEE -->
	 * http://localhost:8080/phoneAlert?firestation=<firestation_number>
	 */
	@GetMapping("/phoneAlert")
	public List<String> getPhoneNumbersCoveredByStationNumberFromService(@RequestParam(value = "firestation") int stationNumber) {
		return personService.getPhoneNumbersCoveredByStationNumberFromRepository(stationNumber);
	}
}