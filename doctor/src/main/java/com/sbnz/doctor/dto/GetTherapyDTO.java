package com.sbnz.doctor.dto;

import java.util.ArrayList;

/**
 * @author Nikola
 *
 */
public class GetTherapyDTO {
	private ArrayList<Long> meds;
	private long patient;

	public ArrayList<Long> getMeds() {
		return meds;
	}

	public void setMeds(ArrayList<Long> meds) {
		this.meds = meds;
	}

	public long getPatient() {
		return patient;
	}

	public void setPatient(long patient) {
		this.patient = patient;
	}

	public GetTherapyDTO(ArrayList<Long> meds, long patient) {
		this.meds = meds;
		this.patient = patient;
	}

	public GetTherapyDTO() {
	}
}
