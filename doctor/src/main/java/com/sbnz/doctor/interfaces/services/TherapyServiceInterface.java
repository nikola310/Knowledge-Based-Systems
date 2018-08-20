package com.sbnz.doctor.interfaces.services;

import java.util.List;

import com.sbnz.doctor.dto.TherapyDTO;

public interface TherapyServiceInterface extends ServiceInterface<TherapyDTO> {
	
	public List<TherapyDTO> addInBulk(List<TherapyDTO> dtos);
}
