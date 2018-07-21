package com.sbnz.model;
// Generated Jul 21, 2018 12:49:21 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Diagnosis generated by hbm2java
 */
@Entity
@Table(name = "diagnosis", catalog = "sbnz")
public class Diagnosis implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long diagnosisId;
	private Date diagnosisDate;
	private Set<Disease> diseases = new HashSet<Disease>(0);

	public Diagnosis() {
	}

	public Diagnosis(long diagnosisId, Date diagnosisDate) {
		this.diagnosisId = diagnosisId;
		this.diagnosisDate = diagnosisDate;
	}

	public Diagnosis(long diagnosisId, Date diagnosisDate, Set<Disease> diseases) {
		this.diagnosisId = diagnosisId;
		this.diagnosisDate = diagnosisDate;
		this.diseases = diseases;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DIAGNOSIS_ID", unique = true, nullable = false)
	public long getDiagnosisId() {
		return this.diagnosisId;
	}

	public void setDiagnosisId(long diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DIAGNOSIS_DATE", nullable = false, length = 10)
	public Date getDiagnosisDate() {
		return this.diagnosisDate;
	}

	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "diagnosis")
	public Set<Disease> getDiseases() {
		return this.diseases;
	}

	public void setDiseases(Set<Disease> diseases) {
		this.diseases = diseases;
	}

}