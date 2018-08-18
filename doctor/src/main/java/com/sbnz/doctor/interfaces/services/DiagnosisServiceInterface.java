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

	public boolean hadFever(long patient);

	public boolean hasHipte(long patient);

	public boolean hasDiab(long patient);

	public boolean hasHighTem(long patient);

	public boolean receivedAntibiotics(long patient);
}
