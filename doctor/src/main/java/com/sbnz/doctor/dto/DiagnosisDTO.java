package com.sbnz.doctor.dto;

import java.util.Date;

/**
 * @author Nikola
 *
 */
public class DiagnosisDTO {

	private long diagnosisId;
	private Date diagnosisDate;
	private long diseaseId;
	private long patientId;
	private long userId;
	private boolean diagnosisActive;
	private String diseaseName;
	private String diseaseCode;

	public DiagnosisDTO(long diagnosisId, Date diagnosisDate, long diseaseId, long patientId, long userId) {
		this.diagnosisId = diagnosisId;
		this.diagnosisDate = diagnosisDate;
		this.diseaseId = diseaseId;
		this.patientId = patientId;
		this.userId = userId;
	}

	public DiagnosisDTO() {
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
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

	public boolean isDiagnosisActive() {
		return diagnosisActive;
	}

	public void setDiagnosisActive(boolean diagnosisActive) {
		this.diagnosisActive = diagnosisActive;
	}
}
