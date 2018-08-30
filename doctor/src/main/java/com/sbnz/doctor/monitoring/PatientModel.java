package com.sbnz.doctor.monitoring;

import com.sbnz.doctor.dto.PatientDTO;

/**
 * @author Nikola
 *
 */
public class PatientModel {

	private long patientId;
	private String name;
	private String surname;
	private double oxygenLevel; // mmHg
	private int heartBeat; // otkucaja u minuti
	private double urinationAmount; // ml
	private String disease;

	public PatientModel(PatientDTO dto) {
		this.patientId = dto.getPatientId();
		this.name = dto.getPatientName();
		this.surname = dto.getPatientSurname();
		this.oxygenLevel = 100.0;
		this.heartBeat = 80;
		this.urinationAmount = 0.0;
		this.disease = "HRBUB";
	}

	public PatientModel(long patientId, String name, String surname, double oxygenLevel, int heartBeat,
			double urinationAmount) {
		this.patientId = patientId;
		this.name = name;
		this.surname = surname;
		this.oxygenLevel = oxygenLevel;
		this.heartBeat = heartBeat;
		this.urinationAmount = urinationAmount;
	}

	public PatientModel() {
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public double getOxygenLevel() {
		return oxygenLevel;
	}

	public void setOxygenLevel(double oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
	}

	public int getHeartBeat() {
		return heartBeat;
	}

	public void setHeartBeat(int heartBeat) {
		this.heartBeat = heartBeat;
	}

	public double getUrinationAmount() {
		return urinationAmount;
	}

	public void setUrinationAmount(double urinationAmount) {
		this.urinationAmount = urinationAmount;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}
}
