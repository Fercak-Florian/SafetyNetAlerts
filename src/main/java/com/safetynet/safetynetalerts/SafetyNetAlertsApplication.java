package com.safetynet.safetynetalerts;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.safetynet.safetynetalerts.data.CustomProperties;

import lombok.CustomLog;

@EnableConfigurationProperties(CustomProperties.class)
@SpringBootApplication
@ConfigurationPropertiesScan("com.safetynet.safetynetalerts.data")
public class SafetyNetAlertsApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}
}
