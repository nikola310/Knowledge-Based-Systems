package com.sbnz.doctor.dto;

/**
 * @author Nikola
 *
 */
public class TherapyDTO {

	private long therapyId;
	private long medicineId;
	private long patientId;

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

	public TherapyDTO(long therapyId, long medicineId, long patientId) {
		this.therapyId = therapyId;
		this.medicineId = medicineId;
		this.patientId = patientId;
	}

	public TherapyDTO() {
	}

}
