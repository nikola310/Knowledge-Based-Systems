package com.sbnz.doctor.dto;

import java.util.Date;

/**
 * @author Nikola
 *
 */
public class MyDiagnosisDTO {

	private long diagnosisId;
	private boolean success;
	private Date date;
	private String disease;
	private String patient;
	private long diseaseId;
	private long patientId;
	private String diseaseCode;

	public MyDiagnosisDTO() {
	}

	public MyDiagnosisDTO(long diagnosisId, boolean success, Date date, String disease, String patient, long diseaseId,
			long patientId, String diseaseCode) {
		this.diagnosisId = diagnosisId;
		this.success = success;
		this.date = date;
		this.disease = disease;
		this.patient = patient;
		this.diseaseId = diseaseId;
		this.patientId = patientId;
		this.diseaseCode = diseaseCode;
	}

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

	public String getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
