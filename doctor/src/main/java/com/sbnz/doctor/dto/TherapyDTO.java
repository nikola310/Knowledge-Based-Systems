package com.sbnz.doctor.dto;

import java.util.Date;

/**
 * @author Nikola
 *
 */
public class TherapyDTO {

	private long therapyId;
	private long medicineId;
	private long patientId;
	private long diseaseId;
	private String diseaseCode;
	private long userId;
	private String diseaseName;
	private String patientName;
	private String medicineName;
	private Date therapyDate;

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public long getTherapyId() {
		return therapyId;
	}

	public void setTherapyId(long therapyId) {
		this.therapyId = therapyId;
	}

	public long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(long medicineId) {
		this.medicineId = medicineId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public Date getTherapyDate() {
		return therapyDate;
	}

	public void setTherapyDate(Date therapyDate) {
		this.therapyDate = therapyDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public TherapyDTO(long therapyId, long medicineId, long patientId, long diseaseId, String diseaseCode, long userId,
			Date therapyDate) {
		super();
		this.therapyId = therapyId;
		this.medicineId = medicineId;
		this.patientId = patientId;
		this.diseaseId = diseaseId;
		this.diseaseCode = diseaseCode;
		this.userId = userId;
		this.therapyDate = therapyDate;
	}

	public TherapyDTO(long therapyId, long medicineId, long patientId, long userId, Date therapyDate) {
		this.therapyId = therapyId;
		this.medicineId = medicineId;
		this.patientId = patientId;
		this.userId = userId;
		this.therapyDate = therapyDate;
	}

	public TherapyDTO() {
	}

}
