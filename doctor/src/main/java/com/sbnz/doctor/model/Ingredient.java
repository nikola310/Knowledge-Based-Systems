package com.sbnz.doctor.model;
// Generated Aug 26, 2018 4:00:55 PM by Hibernate Tools 5.0.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ingredient generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ingredient", catalog = "sbnz")
public class Ingredient implements java.io.Serializable {

	private Long ingredientId;
	private String ingredientName;
	private Set<Ingredientmedicine> ingredientmedicines = new HashSet<Ingredientmedicine>(0);
	private Set<Allergy> allergies = new HashSet<Allergy>(0);

	public Ingredient() {
	}

	public Ingredient(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public Ingredient(String ingredientName, Set<Ingredientmedicine> ingredientmedicines, Set<Allergy> allergies) {
		this.ingredientName = ingredientName;
		this.ingredientmedicines = ingredientmedicines;
		this.allergies = allergies;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "INGREDIENT_ID", unique = true, nullable = false)
	public Long getIngredientId() {
		return this.ingredientId;
	}

	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}

	@Column(name = "INGREDIENT_NAME", nullable = false, length = 250)
	public String getIngredientName() {
		return this.ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient")
	public Set<Ingredientmedicine> getIngredientmedicines() {
		return this.ingredientmedicines;
	}

	public void setIngredientmedicines(Set<Ingredientmedicine> ingredientmedicines) {
		this.ingredientmedicines = ingredientmedicines;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient")
	public Set<Allergy> getAllergies() {
		return this.allergies;
	}

	public void setAllergies(Set<Allergy> allergies) {
		this.allergies = allergies;
	}

}
