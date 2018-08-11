package com.sbnz.doctor.model;
// Generated Aug 11, 2018 4:31:23 PM by Hibernate Tools 5.0.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Therapy generated by hbm2java
 */
@Entity
@Table(name = "therapy", catalog = "sbnz")
public class Therapy implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5016702219054184522L;
	private Long therapyId;
	private Medicine medicine;
	private Patient patient;

	public Therapy() {
	}

	public Therapy(Medicine medicine, Patient patient) {
		this.medicine = medicine;
		this.patient = patient;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "THERAPY_ID", unique = true, nullable = false)
	public Long getTherapyId() {
		return this.therapyId;
	}

	public void setTherapyId(Long therapyId) {
		this.therapyId = therapyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEDICINE_ID", nullable = false)
	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
