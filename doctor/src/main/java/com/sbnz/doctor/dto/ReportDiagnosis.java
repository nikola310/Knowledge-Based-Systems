package com.sbnz.doctor.dto;

import java.util.Date;

import com.sbnz.doctor.model.Diagnosis;

/**
 * @author Nikola
 *
 */
public class ReportDiagnosis {

	private long diagnosisId;
	private Date diagnosisDate;
	private long diseaseId;
	private String diseaseCode;
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

	public String getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public ReportDiagnosis() {
	}

	public ReportDiagnosis(long diagnosisId, Date diagnosisDate, long diseaseId, String diseaseCode, long patientId) {
		this.diagnosisId = diagnosisId;
		this.diagnosisDate = diagnosisDate;
		this.diseaseId = diseaseId;
		this.diseaseCode = diseaseCode;
		this.patientId = patientId;
	}

	public ReportDiagnosis(Diagnosis diag) {
		this.diagnosisId = diag.getDiagnosisId();
		this.diagnosisDate = diag.getDiagnosisDate();
		this.patientId = diag.getPatient().getPatientId();
		this.diseaseId = diag.getDisease().getDiseaseId();
		this.diseaseCode = diag.getDisease().getDiseaseCode();
	}
}
