package com.sbnz.doctor.utils;

import java.util.ArrayList;
import java.util.Date;

import com.sbnz.doctor.dto.DiagnosisDTO;
import com.sbnz.doctor.dto.DiseaseDTO;
import com.sbnz.doctor.dto.PatientDTO;

/**
 * @author Nikola
 *
 */
public class ReportEntity {

	private Date yearLimit;
	private Date now;
	private ArrayList<PatientDTO> patients;
	private ArrayList<DiagnosisDTO> diagnoses;
	private ArrayList<DiseaseDTO> diseases;
	private ArrayList<Long> zavisnici;

	public ReportEntity(Date yearLimit, ArrayList<PatientDTO> patients, ArrayList<DiagnosisDTO> diagnoses) {
		this.yearLimit = yearLimit;
		this.patients = patients;
		this.diagnoses = diagnoses;
	}

	public ReportEntity() {
	}

	public ArrayList<DiseaseDTO> getDiseases() {
		return diseases;
	}

	public void setDiseases(ArrayList<DiseaseDTO> diseases) {
		this.diseases = diseases;
	}

	public Date getYearLimit() {
		return yearLimit;
	}

	public void setYearLimit(Date yearLimit) {
		this.yearLimit = yearLimit;
	}

	public ArrayList<PatientDTO> getPatients() {
		return patients;
	}

	public void setPatients(ArrayList<PatientDTO> patients) {
		this.patients = patients;
	}

	public ArrayList<DiagnosisDTO> getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(ArrayList<DiagnosisDTO> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	public ArrayList<Long> getZavisnici() {
		return zavisnici;
	}

	public void setZavisnici(ArrayList<Long> zavisnici) {
		this.zavisnici = zavisnici;
	}

}
