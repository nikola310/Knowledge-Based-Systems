/**
 * 
 */
package com.sbnz.doctor.dto;

/**
 * @author Nikola
 *
 */
public class IngredientMedDTO {

	private long imId;
	private long ingredientId;
	private long medicineId;
	private String ingredientName;
	private String medicineName;

	public IngredientMedDTO(long imId, long ingredientId, long medicineId, String ingredientName, String medicineName) {
		this.imId = imId;
		this.ingredientId = ingredientId;
		this.medicineId = medicineId;
		this.ingredientName = ingredientName;
		this.medicineName = medicineName;
	}

	public long getImId() {
		return imId;
	}

	public void setImId(long imId) {
		this.imId = imId;
	}

	public long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(long ingredientId) {
		this.ingredientId = ingredientId;
	}

	public long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(long medicineId) {
		this.medicineId = medicineId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
}
