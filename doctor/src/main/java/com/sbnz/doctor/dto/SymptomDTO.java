/**
 * 
 */
package com.sbnz.doctor.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Nikola
 *
 */
public class SymptomDTO {
	private long symId;
	@NotNull
	@Size(min = 5, max = 300)
	private String symDesc;
	@NotNull
	@Size(min = 5, max = 5)
	private String symCode;

	public SymptomDTO() {
		super();
	}

	public SymptomDTO(long symId, String symDesc) {
		super();
		this.symId = symId;
		this.symDesc = symDesc;
	}

	public long getSymId() {
		return symId;
	}

	public void setSymId(long symId) {
		this.symId = symId;
	}

	public String getSymDesc() {
		return symDesc;
	}

	public void setSymDesc(String symDesc) {
		this.symDesc = symDesc;
	}

	public String getSymCode() {
		return symCode;
	}

	public void setSymCode(String symCode) {
		this.symCode = symCode;
	}
}
