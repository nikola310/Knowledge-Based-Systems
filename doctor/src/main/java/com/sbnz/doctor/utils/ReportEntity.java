package com.sbnz.doctor.utils;

import java.util.ArrayList;
import java.util.HashSet;

import com.sbnz.doctor.dto.DiseaseDTO;
import com.sbnz.doctor.dto.PatientDTO;
import com.sbnz.doctor.dto.ReportDiagnosis;
import com.sbnz.doctor.dto.TherapyReport;

/**
 * @author Nikola
 *
 */
public class ReportEntity {

	private ArrayList<PatientDTO> patients;
	private ArrayList<ReportDiagnosis> diagnoses;
	private ArrayList<TherapyReport> therapies;
	private ArrayList<DiseaseDTO> diseases;
	private ArrayList<Long> retVal;
	private HashSet<Long> lekari;

	public ReportEntity(ArrayList<PatientDTO> patients, ArrayList<ReportDiagnosis> diagnoses,
			ArrayList<TherapyReport> therapies, ArrayList<DiseaseDTO> diseases, ArrayList<Long> retVal) {
		this.patients = patients;
		this.diagnoses = diagnoses;
		this.therapies = therapies;
		this.diseases = diseases;
		this.retVal = retVal;
	}

	public ReportEntity(ArrayList<PatientDTO> patients, ArrayList<ReportDiagnosis> diagnoses) {
		this.patients = patients;
		this.diagnoses = diagnoses;
		this.diseases = new ArrayList<>();
		this.therapies = new ArrayList<>();
		this.retVal = new ArrayList<>();
		this.lekari = new HashSet<>();
	}

	public ReportEntity() {
		this.diagnoses = new ArrayList<>();
		this.diseases = new ArrayList<>();
		this.therapies = new ArrayList<>();
		this.patients = new ArrayList<>();
		this.retVal = new ArrayList<>();
		this.lekari = new HashSet<>();
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

	public ArrayList<Long> getRetVal() {
		return retVal;
	}

	public void setRetVal(ArrayList<Long> retVal) {
		this.retVal = retVal;
	}

	public ArrayList<TherapyReport> getTherapies() {
		return therapies;
	}

	public void setTherapies(ArrayList<TherapyReport> therapies) {
		this.therapies = therapies;
	}

	public HashSet<Long> getLekari() {
		return lekari;
	}

	public void setLekari(HashSet<Long> lekari) {
		this.lekari = lekari;
	}

}
