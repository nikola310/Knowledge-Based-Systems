package com.sbnz.doctor.dto;

import java.util.ArrayList;

/**
 * @author Nikola
 *
 */
public class PatientSymptomsDTO {

	private ArrayList<SymptomDTO> symptoms;
	private long patientId;

	public ArrayList<SymptomDTO> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(ArrayList<SymptomDTO> symptoms) {
		this.symptoms = symptoms;
	}

	public long getPatientId() {
		return patientId;
	}

	public PatientSymptomsDTO() {
		super();
		this.symptoms = new ArrayList<>();
	}

	public PatientSymptomsDTO(ArrayList<SymptomDTO> symptoms, long patientId) {
		this.symptoms = symptoms;
		this.patientId = patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

}
