package com.safetynet.safetynetalerts.repository;

import java.io.IOException;

public class RepositoryInstance {

	private static IGlobalRepository globalRepository = null;

	public static IGlobalRepository getGlobalRepository() {
		if (globalRepository == null) {
			try {
				globalRepository = new GlobalRepository();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return globalRepository;
	}
}
