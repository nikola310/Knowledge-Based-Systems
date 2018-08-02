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
 * Ingredientmedicine generated by hbm2java
 */
@Entity
@Table(name = "ingredientmedicine", catalog = "sbnz")
public class Ingredientmedicine implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long imId;
	private Ingredient ingredient;
	private Medicine medicine;

	public Ingredientmedicine() {
	}

	public Ingredientmedicine(Ingredient ingredient, Medicine medicine) {
		this.ingredient = ingredient;
		this.medicine = medicine;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "IM_ID", unique = true, nullable = false)
	public Long getImId() {
		return this.imId;
	}

	public void setImId(Long imId) {
		this.imId = imId;
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
	@JoinColumn(name = "MEDICINE_ID", nullable = false)
	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

}
