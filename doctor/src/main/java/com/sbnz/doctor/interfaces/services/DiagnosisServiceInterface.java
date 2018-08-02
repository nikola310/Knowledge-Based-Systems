/**
 * 
 */
package com.sbnz.doctor.interfaces.services;

import java.util.List;

import com.sbnz.doctor.dto.DiagnosisDTO;
import com.sbnz.doctor.dto.MyDiagnosisDTO;
import com.sbnz.doctor.dto.UserDTO;

/**
 * @author Nikola
 *
 */
public interface DiagnosisServiceInterface extends ServiceInterface<DiagnosisDTO> {

	public List<MyDiagnosisDTO> getDiagnoses(UserDTO user);
}
