/**
 * 
 */
package com.sbnz.doctor.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Nikola
 *
 */
public class IngredientDTO {

	private long ingredientId;
	@NotNull
	@Size(min = 5, max = 250)
	private String ingredientName;

	public IngredientDTO() {
	}

	public IngredientDTO(long ingredientId, String ingredientName) {
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
	}

	public long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(long ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

}
