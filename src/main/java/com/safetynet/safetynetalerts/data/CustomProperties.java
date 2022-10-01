package com.safetynet.safetynetalerts.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "test")
public class CustomProperties {
	
	@Value("${test.filePath}")
	private String filePath;
}
