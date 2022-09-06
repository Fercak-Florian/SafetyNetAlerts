package com.safetynet.safetynetalerts.model;

import java.util.List;

import com.jsoniter.annotation.JsonProperty;
import com.jsoniter.fuzzy.MaybeEmptyArrayDecoder;

import lombok.Data;

@Data
public class MedicalRecord {
	private String firstName;
	private String lastName;
	private String birthDate; // Type Calendar or Date
	@JsonProperty(decoder = MaybeEmptyArrayDecoder.class) // PAS OBLIGE
	private List<String>  medications; // Type list of String
	@JsonProperty(decoder = MaybeEmptyArrayDecoder.class) // PAS OBLIGE
	private List<String> allergies; // Type list of String 
}
