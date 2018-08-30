package com.sbnz.doctor.monitoring;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

/**
 * @author Nikola
 *
 */
@Role(Type.EVENT)
@Expires(value = "30m")
public class ChangeOxygenLevel {

	private long patientId;
	private long amount;

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public ChangeOxygenLevel(long patientId, long amount) {
		this.patientId = patientId;
		this.amount = amount;
	}

}
