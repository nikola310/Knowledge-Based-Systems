package com.sbnz.doctor.utils;

public class MapRetVal {

	private String code;
	private Double value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public MapRetVal(String code, Double value) {
		this.code = code;
		this.value = value;
	}

}
