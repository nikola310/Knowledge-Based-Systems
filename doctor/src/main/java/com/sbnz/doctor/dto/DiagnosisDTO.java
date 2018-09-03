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
	private long userId;

	public DiagnosisDTO(long diagnosisId, @NotNull Date diagnosisDate, long diseaseId, long patientId, long userId) {
		this.diagnosisId = diagnosisId;
		this.diagnosisDate = diagnosisDate;
		this.diseaseId = diseaseId;
		this.patientId = patientId;
		this.userId = userId;
	}

	public DiagnosisDTO() {
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

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
