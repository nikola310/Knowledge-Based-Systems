package com.sbnz.doctor.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Nikola
 *
 */
public class PatientDTO {

	private long patientId;
	private long userId;
	@NotNull
	@Size(max = 100)
	private String patientName;
	@NotNull
	@Size(max = 250)
	private String patientSurname;

	public PatientDTO() {
	}

	public PatientDTO(long patientId, long userId, String patientName, String patientSurname) {
		this.patientId = patientId;
		this.userId = userId;
		this.patientName = patientName;
		this.patientSurname = patientSurname;
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

}
