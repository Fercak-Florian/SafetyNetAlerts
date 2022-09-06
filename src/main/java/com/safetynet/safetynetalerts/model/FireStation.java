package com.safetynet.safetynetalerts.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class FireStation {
	private String address;
	private int id;

	public FireStation (String address, int id) {
		this.address = address;
		this.id = id;
	}
}
