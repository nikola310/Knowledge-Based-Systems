package com.sbnz.doctor.utils;

import java.util.ArrayList;

import com.sbnz.doctor.dto.IngredientDTO;
import com.sbnz.doctor.dto.MedicineDTO;

/**
 * @author Nikola
 *
 */
public class MedicineList {

	private ArrayList<MedicineDTO> meds;
	private ArrayList<IngredientDTO> ingredients;
	private ArrayList<MedicineDTO> alergic;

	public MedicineList(ArrayList<MedicineDTO> meds, ArrayList<IngredientDTO> ingredients,
			ArrayList<MedicineDTO> alergic) {
		this.meds = meds;
		this.ingredients = ingredients;
		this.alergic = alergic;
	}

	public ArrayList<IngredientDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<IngredientDTO> ingredients) {
		this.ingredients = ingredients;
	}

	public ArrayList<MedicineDTO> getMeds() {
		return meds;
	}

	public void setMeds(ArrayList<MedicineDTO> meds) {
		this.meds = meds;
	}

	public ArrayList<MedicineDTO> getAlergic() {
		return alergic;
	}

	public void setAlergic(ArrayList<MedicineDTO> alergic) {
		this.alergic = alergic;
	}

	public MedicineList(ArrayList<MedicineDTO> meds, ArrayList<MedicineDTO> alergic) {
		this.meds = meds;
		this.alergic = alergic;
	}

	public MedicineList() {
	}

}
