package com.sbnz.doctor.dto;

import java.util.Date;

import com.sbnz.doctor.model.Therapy;

/**
 * @author Nikola
 *
 */
public class TherapyReport {
	private long therapyId;
	private long medicineId;
	private long patientId;
	private long userId;
	private Date therapyDate;
	private char medicineType;

	public TherapyReport(long therapyId, long medicineId, long patientId, long userId, Date therapyDate,
			char medicineType) {
		this.therapyId = therapyId;
		this.medicineId = medicineId;
		this.patientId = patientId;
		this.userId = userId;
		this.therapyDate = therapyDate;
		this.medicineType = medicineType;
	}
	
	public TherapyReport(Therapy therapy) {
		this.therapyId = therapy.getTherapyId();
		this.medicineId = therapy.getMedicine().getMedicineId();
		this.medicineType = therapy.getMedicine().getMedicineType();
		this.patientId = therapy.getPatient().getPatientId();
		this.userId = therapy.getUser().getUserId();
		this.therapyDate = therapy.getTherapyDate();
	}

	public TherapyReport() {
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getTherapyDate() {
		return therapyDate;
	}

	public void setTherapyDate(Date therapyDate) {
		this.therapyDate = therapyDate;
	}

	public char getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(char medicineType) {
		this.medicineType = medicineType;
	}
}
