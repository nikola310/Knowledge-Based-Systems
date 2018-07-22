/**
 * 
 */
package com.sbnz.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Nikola
 *
 */
public class DiseaseDTO {

	private long diseaseId;
	@NotNull
	@Size(max = 200, min = 5)
	private String diseaseName;
	@NotNull
	private long diagnosisId;
	@NotNull
	private long medicineId;

	public DiseaseDTO() {
		super();
	}

	public DiseaseDTO(long diseaseId, String diseaseName, long diagnosisId, long medicineId) {
		super();
		this.diseaseId = diseaseId;
		this.diseaseName = diseaseName;
		this.diagnosisId = diagnosisId;
		this.medicineId = medicineId;
	}

	public long getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(long diseaseId) {
		this.diseaseId = diseaseId;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public long getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(long diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(long medicineId) {
		this.medicineId = medicineId;
	}
}
