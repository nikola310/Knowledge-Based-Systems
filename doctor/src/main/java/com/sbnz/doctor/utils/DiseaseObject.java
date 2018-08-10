package com.sbnz.doctor.utils;

/**
 * @author Nikola
 *
 */
public class DiseaseObject {

	private String code;
	private double probability;
	private boolean all;
	private int num;

	public DiseaseObject(String code, double probability, boolean all, int num) {
		this.code = code;
		this.probability = probability;
		this.all = all;
		this.num = num;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public boolean isAll() {
		return all;
	}

	public void setAll(boolean all) {
		this.all = all;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
