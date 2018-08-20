package com.sbnz.doctor.utils;

import java.util.ArrayList;

import com.sbnz.doctor.dto.AllergyDTO;
import com.sbnz.doctor.dto.MedicineallergyDTO;
import com.sbnz.doctor.dto.PatientDTO;

/**
 * @author Nikola
 *
 */
public class CheckAllergyObject {

	private long patientId;
	private ArrayList<AllergyDTO> allergies;
	private ArrayList<MedicineallergyDTO> medAllergies;
	private ArrayList<AllergyDTO> isAllergic;
	private ArrayList<MedicineallergyDTO> isMedAllergic;

	public CheckAllergyObject() {
		this.allergies = new ArrayList<>();
		this.medAllergies = new ArrayList<>();
		this.isAllergic = new ArrayList<>();
		this.isMedAllergic = new ArrayList<>();
	}

	public CheckAllergyObject(long patientId, PatientDTO patient, ArrayList<AllergyDTO> allergies,
			ArrayList<MedicineallergyDTO> medAllergies, ArrayList<AllergyDTO> isAllergic,
			ArrayList<MedicineallergyDTO> isMedAllergic) {
		this.patientId = patientId;
		this.allergies = allergies;
		this.medAllergies = medAllergies;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public ArrayList<AllergyDTO> getAllergies() {
		return allergies;
	}

	public void setAllergies(ArrayList<AllergyDTO> allergies) {
		this.allergies = allergies;
	}

	public ArrayList<MedicineallergyDTO> getMedAllergies() {
		return medAllergies;
	}

	public void setMedAllergies(ArrayList<MedicineallergyDTO> medAllergies) {
		this.medAllergies = medAllergies;
	}

	public ArrayList<AllergyDTO> getIsAllergic() {
		return isAllergic;
	}

	public void setIsAllergic(ArrayList<AllergyDTO> isAllergic) {
		this.isAllergic = isAllergic;
	}

	public ArrayList<MedicineallergyDTO> getIsMedAllergic() {
		return isMedAllergic;
	}

	public void setIsMedAllergic(ArrayList<MedicineallergyDTO> isMedAllergic) {
		this.isMedAllergic = isMedAllergic;
	}

}
