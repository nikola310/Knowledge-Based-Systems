package com.sbnz.doctor.dto;

/**
 * @author Nikola
 *
 */
public class MedicineallergyDTO {
	private long medId;
	private long medicineId;
	private long patientId;
	private String medicine;
	private String patient;

	public long getMedId() {
		return medId;
	}

	public void setMedId(long medId) {
		this.medId = medId;
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

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public MedicineallergyDTO(long medId, long medicineId, long patientId, String medicine, String patient) {
		super();
		this.medId = medId;
		this.medicineId = medicineId;
		this.patientId = patientId;
		this.medicine = medicine;
		this.patient = patient;
	}

	public MedicineallergyDTO() {
		super();
	}
}
