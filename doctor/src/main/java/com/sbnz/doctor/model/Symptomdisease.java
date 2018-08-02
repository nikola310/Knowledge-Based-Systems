package com.sbnz.doctor.model;
// Generated Aug 2, 2018 12:56:36 PM by Hibernate Tools 5.0.6.Final

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
@Entity
@Table(name = "symptomdisease", catalog = "sbnz")
public class Symptomdisease implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long sdId;
	private Disease disease;
	private Symptom symptom;

	public Symptomdisease() {
	}

	public Symptomdisease(Disease disease, Symptom symptom) {
		this.disease = disease;
		this.symptom = symptom;
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

}
