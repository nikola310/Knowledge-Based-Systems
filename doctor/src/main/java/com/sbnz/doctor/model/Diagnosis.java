package com.sbnz.doctor.model;
// Generated Aug 4, 2018 9:09:23 PM by Hibernate Tools 5.0.6.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Diagnosis generated by hbm2java
 */
@Entity
@Table(name = "diagnosis", catalog = "sbnz")
public class Diagnosis implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1389697713709800495L;
	private Long diagnosisId;
	private Disease disease;
	private Patient patient;
	private User user;
	private Date diagnosisDate;

	public Diagnosis() {
	}

	public Diagnosis(Disease disease, Patient patient, User user, Date diagnosisDate) {
		this.disease = disease;
		this.patient = patient;
		this.user = user;
		this.diagnosisDate = diagnosisDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "DIAGNOSIS_ID", unique = true, nullable = false)
	public Long getDiagnosisId() {
		return this.diagnosisId;
	}

	public void setDiagnosisId(Long diagnosisId) {
		this.diagnosisId = diagnosisId;
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
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DIAGNOSIS_DATE", nullable = false, length = 10)
	public Date getDiagnosisDate() {
		return this.diagnosisDate;
	}

	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

}
