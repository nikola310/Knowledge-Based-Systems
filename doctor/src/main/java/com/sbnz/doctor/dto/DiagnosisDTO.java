/**
 * 
 */
package com.sbnz.doctor.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola
 *
 */
public class DiagnosisDTO {

	private long diagnosisId;
	@NotNull
	private Date diagnosisDate;

	public long getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(long diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public Date getDiagnosisDate() {
		return diagnosisDate;
	}

	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}
}
