/**
 * 
 */
package com.sbnz.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Nikola
 *
 */
public class PatientDTO {

	private long patientId;
	@NotNull
	private long userId;
	@NotNull
	@Size(min = 50, max = 100)
	private String patientName;
	@NotNull
	@Size(min = 50, max = 100)
	private String patientSurname;
	@NotNull
	private int patientBloodPressure;
	@NotNull
	private float patientTemperature;

	public PatientDTO() {
	}

	public PatientDTO(long patientId, long userId, String patientName, String patientSurname, int patientBloodPressure,
			float patientTemperature) {
		this.patientId = patientId;
		this.userId = userId;
		this.patientName = patientName;
		this.patientSurname = patientSurname;
		this.patientBloodPressure = patientBloodPressure;
		this.patientTemperature = patientTemperature;
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

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientSurname() {
		return patientSurname;
	}

	public void setPatientSurname(String patientSurname) {
		this.patientSurname = patientSurname;
	}

	public int getPatientBloodPressure() {
		return patientBloodPressure;
	}

	public void setPatientBloodPressure(int patientBloodPressure) {
		this.patientBloodPressure = patientBloodPressure;
	}

	public float getPatientTemperature() {
		return patientTemperature;
	}

	public void setPatientTemperature(float patientTemperature) {
		this.patientTemperature = patientTemperature;
	}
}
