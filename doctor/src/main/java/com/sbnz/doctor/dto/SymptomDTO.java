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
	@NotNull
	private boolean symSystem;

	public SymptomDTO() {
	}

	public SymptomDTO(long symId, String symDesc) {
		this.symId = symId;
		this.symDesc = symDesc;
	}

	public SymptomDTO(long symId, @NotNull @Size(min = 5, max = 300) String symDesc,
			@NotNull @Size(min = 5, max = 5) String symCode, @NotNull boolean symSystem) {
		this.symId = symId;
		this.symDesc = symDesc;
		this.symCode = symCode;
		this.symSystem = symSystem;
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

	public boolean isSymSystem() {
		return symSystem;
	}

	public void setSymSystem(boolean symSystem) {
		this.symSystem = symSystem;
	}
}
