package com.sbnz.doctor.dto;

public class AllergyDTO {

	private long allergyId;
	private long ingredientId;
	private long patientId;
	private String ingredientName;
	private String patient;

	public long getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(long allergyId) {
		this.allergyId = allergyId;
	}

	public long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(long ingredientId) {
		this.ingredientId = ingredientId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public AllergyDTO() {
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public AllergyDTO(long allergyId, long ingredientId, long patientId, String ingredientName, String patient) {
		super();
		this.allergyId = allergyId;
		this.ingredientId = ingredientId;
		this.patientId = patientId;
		this.ingredientName = ingredientName;
		this.patient = patient;
	}
}
