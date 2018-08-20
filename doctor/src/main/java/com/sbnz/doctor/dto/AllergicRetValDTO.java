package com.sbnz.doctor.dto;

import java.util.ArrayList;

public class AllergicRetValDTO {

	private ArrayList<String> ingredients;
	private ArrayList<String> medicine;

	public AllergicRetValDTO() {
		this.ingredients = new ArrayList<>();
		this.medicine = new ArrayList<>();
	}

	public AllergicRetValDTO(ArrayList<String> ingredients, ArrayList<String> medicine) {
		this.ingredients = ingredients;
		this.medicine = medicine;
	}

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}

	public ArrayList<String> getMedicine() {
		return medicine;
	}

	public void setMedicine(ArrayList<String> medicine) {
		this.medicine = medicine;
	}

}
