package com.sbnz.doctor.monitoring;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

/**
 * @author Nikola
 *
 */
@Role(Type.EVENT)
@Expires(value = "12h")
public class UrinationAmount {

	private long patientId;
	private double amount;

	public UrinationAmount(long patientId, double amount) {
		this.patientId = patientId;
		this.amount = amount;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
