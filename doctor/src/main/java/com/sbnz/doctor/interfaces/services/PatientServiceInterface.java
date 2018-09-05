/**
 * 
 */
package com.sbnz.doctor.interfaces.services;

import com.sbnz.doctor.dto.PatientDTO;

/**
 * @author Nikola
 *
 */
public interface PatientServiceInterface extends ServiceInterface<PatientDTO> {

	public PatientDTO getAny();
}
