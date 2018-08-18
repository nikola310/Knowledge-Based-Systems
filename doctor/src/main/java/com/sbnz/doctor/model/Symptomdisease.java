package com.sbnz.doctor.model;
// Generated Aug 18, 2018 11:10:28 AM by Hibernate Tools 5.0.6.Final

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
 * Symptomdisease generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "symptomdisease", catalog = "sbnz")
public class Symptomdisease implements java.io.Serializable {

	private Long sdId;
	private Disease disease;
	private Symptom symptom;
	private boolean sdSpecific;

	public Symptomdisease() {
	}

	public Symptomdisease(Disease disease, Symptom symptom, boolean sdSpecific) {
		this.disease = disease;
		this.symptom = symptom;
		this.sdSpecific = sdSpecific;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "SD_ID", unique = true, nullable = false)
	public Long getSdId() {
		return this.sdId;
	}

	public void setSdId(Long sdId) {
		this.sdId = sdId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISEASE_ID", nullable = false)
	public Disease getDisease() {
		return this.disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYM_ID", nullable = false)
	public Symptom getSymptom() {
		return this.symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	@Column(name = "SD_SPECIFIC", nullable = false)
	public boolean isSdSpecific() {
		return this.sdSpecific;
	}

	public void setSdSpecific(boolean sdSpecific) {
		this.sdSpecific = sdSpecific;
	}

}
