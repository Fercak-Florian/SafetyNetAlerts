package com.safetynet.safetynetalerts.model;

import lombok.Data;

@Data
public class FireStation {
	private String address;
	private int stationNumber;

	public FireStation(String address, int stationNumber) {
		this.address = address;
		this.stationNumber = stationNumber;
	}
}
