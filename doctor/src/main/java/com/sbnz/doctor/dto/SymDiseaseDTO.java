package com.sbnz.doctor.dto;

public class SymDiseaseDTO {
	private long sdId;
	private long diseaseId;
	private long symptomId;
	private String diseaseName;
	private String symptomDesc;
	private boolean sdSpecific;

	public boolean isSdSpecific() {
		return sdSpecific;
	}

	public void setSdSpecific(boolean sdSpecific) {
		this.sdSpecific = sdSpecific;
	}

	public long getSdId() {
		return sdId;
	}

	public void setSdId(long sdId) {
		this.sdId = sdId;
	}

	public long getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(long diseaseId) {
		this.diseaseId = diseaseId;
	}

	public long getSymptomId() {
		return symptomId;
	}

	public void setSymptomId(long symptomId) {
		this.symptomId = symptomId;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getSymptomDesc() {
		return symptomDesc;
	}

	public void setSymptomDesc(String symptomDesc) {
		this.symptomDesc = symptomDesc;
	}

	public SymDiseaseDTO() {
	}

	public SymDiseaseDTO(long sdId, long diseaseId, long symptomId, String diseaseName, String symptomDesc) {
		super();
		this.sdId = sdId;
		this.diseaseId = diseaseId;
		this.symptomId = symptomId;
		this.diseaseName = diseaseName;
		this.symptomDesc = symptomDesc;
	}

}
