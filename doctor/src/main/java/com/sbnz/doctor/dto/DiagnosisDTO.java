package com.sbnz.doctor.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola
 *
 */
public class DiagnosisDTO {

	private long diagnosisId;
	@NotNull
	private Date diagnosisDate;
	private long diseaseId;
	private long patientId;

	public long getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(long diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public Date getDiagnosisDate() {
		return diagnosisDate;
	}

	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	public long getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(long diseaseId) {
		this.diseaseId = diseaseId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
}
