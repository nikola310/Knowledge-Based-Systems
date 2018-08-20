package com.sbnz.doctor.model;
// Generated Aug 20, 2018 8:56:25 PM by Hibernate Tools 5.0.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Disease generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "disease", catalog = "sbnz")
public class Disease implements java.io.Serializable {

	private Long diseaseId;
	private Medicine medicine;
	private String diseaseName;
	private String diseaseCode;
	private Set<Symptomdisease> symptomdiseases = new HashSet<Symptomdisease>(0);
	private Set<Diagnosis> diagnosises = new HashSet<Diagnosis>(0);

	public Disease() {
	}

	public Disease(String diseaseName, String diseaseCode) {
		this.diseaseName = diseaseName;
		this.diseaseCode = diseaseCode;
	}

	public Disease(Medicine medicine, String diseaseName, String diseaseCode, Set<Symptomdisease> symptomdiseases,
			Set<Diagnosis> diagnosises) {
		this.medicine = medicine;
		this.diseaseName = diseaseName;
		this.diseaseCode = diseaseCode;
		this.symptomdiseases = symptomdiseases;
		this.diagnosises = diagnosises;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "DISEASE_ID", unique = true, nullable = false)
	public Long getDiseaseId() {
		return this.diseaseId;
	}

	public void setDiseaseId(Long diseaseId) {
		this.diseaseId = diseaseId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEDICINE_ID")
	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	@Column(name = "DISEASE_NAME", nullable = false, length = 100)
	public String getDiseaseName() {
		return this.diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	@Column(name = "DISEASE_CODE", nullable = false, length = 5, columnDefinition = "char")
	public String getDiseaseCode() {
		return this.diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "disease")
	public Set<Symptomdisease> getSymptomdiseases() {
		return this.symptomdiseases;
	}

	public void setSymptomdiseases(Set<Symptomdisease> symptomdiseases) {
		this.symptomdiseases = symptomdiseases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "disease")
	public Set<Diagnosis> getDiagnosises() {
		return this.diagnosises;
	}

	public void setDiagnosises(Set<Diagnosis> diagnosises) {
		this.diagnosises = diagnosises;
	}

}
