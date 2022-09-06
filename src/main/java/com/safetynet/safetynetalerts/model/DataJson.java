package com.safetynet.safetynetalerts.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class DataJson {
	private List<Person> persons;
	private List<FireStation> firestations;
	private List<MedicalRecord> medicalrecords;
}
