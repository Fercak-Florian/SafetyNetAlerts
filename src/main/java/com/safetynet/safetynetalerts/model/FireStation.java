package com.safetynet.safetynetalerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireStation {
	private String address;
	private int stationNumber;

//	public FireStation(String address, int stationNumber) {
//		this.address = address;
//		this.stationNumber = stationNumber;
//	}
	
	
	
//	public FireStation() {
//	}
}
