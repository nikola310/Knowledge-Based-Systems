package com.sbnz.doctor.dto;

import java.util.Date;

/**
 * @author Nikola
 *
 */
public class MyDiagnosisDTO {

	private long diagnosisId;
	private Date date;
	private String disease;
	private String patient;

	public long getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(long diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}
}
