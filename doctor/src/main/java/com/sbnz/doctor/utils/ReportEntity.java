package com.sbnz.doctor.utils;

import java.util.ArrayList;

import com.sbnz.doctor.dto.DiseaseDTO;
import com.sbnz.doctor.dto.PatientDTO;
import com.sbnz.doctor.dto.ReportDiagnosis;

/**
 * @author Nikola
 *
 */
public class ReportEntity {

	private ArrayList<PatientDTO> patients;
	private ArrayList<ReportDiagnosis> diagnoses;
	private ArrayList<DiseaseDTO> diseases;
	private ArrayList<Long> zavisnici;

	public ReportEntity(ArrayList<PatientDTO> patients, ArrayList<ReportDiagnosis> diagnoses) {
		this.patients = patients;
		this.diagnoses = diagnoses;
		this.diseases = new ArrayList<>();
		this.zavisnici = new ArrayList<>();
	}

	public ReportEntity() {
		this.diagnoses = new ArrayList<>();
		this.diseases = new ArrayList<>();
		this.patients = new ArrayList<>();
		this.zavisnici = new ArrayList<>();
	}

	public ArrayList<DiseaseDTO> getDiseases() {
		return diseases;
	}

	public void setDiseases(ArrayList<DiseaseDTO> diseases) {
		this.diseases = diseases;
	}

	public ArrayList<PatientDTO> getPatients() {
		return patients;
	}

	public void setPatients(ArrayList<PatientDTO> patients) {
		this.patients = patients;
	}

	public ArrayList<ReportDiagnosis> getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(ArrayList<ReportDiagnosis> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public ArrayList<Long> getZavisnici() {
		return zavisnici;
	}

	public void setZavisnici(ArrayList<Long> zavisnici) {
		this.zavisnici = zavisnici;
	}

}
