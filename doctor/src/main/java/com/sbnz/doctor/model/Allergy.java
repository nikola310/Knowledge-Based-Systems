package com.sbnz.doctor.model;
// Generated Aug 4, 2018 9:09:23 PM by Hibernate Tools 5.0.6.Final

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
 * Allergy generated by hbm2java
 */
@Entity
@Table(name = "allergy", catalog = "sbnz")
public class Allergy implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1796114425164577662L;
	private Long allergyId;
	private Ingredient ingredient;
	private Patient patient;

	public Allergy() {
	}

	public Allergy(Ingredient ingredient, Patient patient) {
		this.ingredient = ingredient;
		this.patient = patient;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ALLERGY_ID", unique = true, nullable = false)
	public Long getAllergyId() {
		return this.allergyId;
	}

	public void setAllergyId(Long allergyId) {
		this.allergyId = allergyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INGREDIENT_ID", nullable = false)
	public Ingredient getIngredient() {
		return this.ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
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
