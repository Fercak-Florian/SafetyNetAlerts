package com.safetynet.safetynetalerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetynetalerts.data.IDataReader;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.workclasses.FirstNameAndLastName;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceImplTest {

	private IMedicalRecordService medicalRecordService;

	@Mock
	private IDataReader dataReader;

	@BeforeEach
	public void init() {
		medicalRecordService = new MedicalRecordServiceImpl(dataReader);
	}

	@Test
	public void testGetMedicalRecords() {
		when(dataReader.getMedicalRecords()).thenReturn(
				Arrays.asList(new MedicalRecord("John", "Boyd", "03/06/1984", new ArrayList<>(), new ArrayList<>())));
		List<MedicalRecord> result = medicalRecordService.getMedicalRecords();
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
		verify(dataReader).getMedicalRecords();
	}

	@Test
	public void testAddMedicalRecord() {
		when(dataReader.addMedicalRecord(
				new MedicalRecord("Eric", "Cadigan", "03/06/1984", new ArrayList<>(), new ArrayList<>())))
				.thenReturn(Arrays
						.asList(new MedicalRecord("John", "Boyd", "03/06/1984", new ArrayList<>(), new ArrayList<>())));
		List<MedicalRecord> result = medicalRecordService.addMedicalRecord(
				new MedicalRecord("Eric", "Cadigan", "03/06/1984", new ArrayList<>(), new ArrayList<>()));
		assertThat(result.get(0).getFirstName()).isEqualTo("John");
		verify(dataReader).addMedicalRecord(
				new MedicalRecord("Eric", "Cadigan", "03/06/1984", new ArrayList<>(), new ArrayList<>()));
	}

	@Test
	public void testUpdateMedicalRecord() {
		when(dataReader.updateMedicalRecord(
				new MedicalRecord("John", "Boyd", "03/06/1984", new ArrayList<>(), new ArrayList<>())))
				.thenReturn(Arrays
						.asList(new MedicalRecord("John", "Boyd", "01/01/1980", new ArrayList<>(), new ArrayList<>())));
		List<MedicalRecord> result = medicalRecordService.updateMedicalRecord(
				new MedicalRecord("John", "Boyd", "03/06/1984", new ArrayList<>(), new ArrayList<>()));
		assertThat(result.get(0).getBirthDate()).isEqualTo("01/01/1980");
		verify(dataReader).updateMedicalRecord(
				new MedicalRecord("John", "Boyd", "03/06/1984", new ArrayList<>(), new ArrayList<>()));
	}

	@Test
	public void testDeleteMedicalRecord() {
		when(dataReader.deleteMedicalRecord(new FirstNameAndLastName("John", "Boyd"))).thenReturn(
				Arrays.asList(new MedicalRecord("Jacob", "Boyd", "03/06/1989", new ArrayList<>(), new ArrayList<>())));
		List<MedicalRecord> result = medicalRecordService.deleteMedicalRecord(new FirstNameAndLastName("John", "Boyd"));
		assertThat(result.get(0).getFirstName()).isEqualTo("Jacob");
		verify(dataReader).deleteMedicalRecord(new FirstNameAndLastName("John", "Boyd"));
	}
}
