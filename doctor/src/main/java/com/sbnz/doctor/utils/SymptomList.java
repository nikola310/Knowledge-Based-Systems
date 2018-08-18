package com.sbnz.doctor.utils;

import java.util.ArrayList;
import java.util.HashMap;

import com.sbnz.doctor.dto.SymptomDTO;

/**
 * @author Nikola
 *
 */
public class SymptomList {

	private ArrayList<SymptomDTO> symptoms;

	// kod bolesti, verovatnoca da je ta bolest
	private HashMap<String, Double> mostLikelyDisease;

	private int flag;

	public SymptomList() {
		symptoms = new ArrayList<>();
		mostLikelyDisease = new HashMap<>();
	}

	public SymptomList(ArrayList<SymptomDTO> symptoms) {
		this.symptoms = symptoms;
		this.mostLikelyDisease = new HashMap<>();
	}

	public SymptomList(ArrayList<SymptomDTO> symptoms, HashMap<String, Double> mostLikelyDisease) {
		this.symptoms = symptoms;
		this.mostLikelyDisease = mostLikelyDisease;
	}

	public ArrayList<SymptomDTO> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(ArrayList<SymptomDTO> symptoms) {
		this.symptoms = symptoms;
	}

	public HashMap<String, Double> getMostLikelyDisease() {
		return mostLikelyDisease;
	}

	public void setMostLikelyDisease(HashMap<String, Double> mostLikelyDisease) {
		this.mostLikelyDisease = mostLikelyDisease;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
