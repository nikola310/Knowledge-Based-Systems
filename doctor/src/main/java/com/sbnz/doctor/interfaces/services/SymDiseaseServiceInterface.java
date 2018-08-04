package com.sbnz.doctor.interfaces.services;

import java.util.List;

import com.sbnz.doctor.dto.SymDiseaseDTO;

public interface SymDiseaseServiceInterface extends ServiceInterface<SymDiseaseDTO> {

	public List<SymDiseaseDTO> getBySymptom(long sym);

	public List<SymDiseaseDTO> getByDisease(long dis);
	
	public SymDiseaseDTO[] addInBulk(SymDiseaseDTO[] dtos);

}
