package com.safetynet.safetynetalerts.repository;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class FilePaths {
	private String productionFilePath = "src/main/resources/data.json";
	private String emptyFilePath = "src/main/resources/emptyfile.json";
}
