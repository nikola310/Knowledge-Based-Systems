package com.sbnz.doctor.monitoring;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

/**
 * Klasa predstavlja jedan otkucaj srca
 * 
 * @author Nikola
 *
 */
@Role(Type.EVENT)
@Expires(value = "10s")
public class Heartbeat {

	private long patientId;

	public Heartbeat() {
	}

	public Heartbeat(long patientId) {
		this.patientId = patientId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

}
