package com.sbnz.doctor.interfaces.services;

import java.util.List;

import com.sbnz.doctor.dto.AllergyDTO;

public interface AllergyServiceInterface extends ServiceInterface<AllergyDTO> {

	public List<AllergyDTO> getByIngredient(long ing);

	public List<AllergyDTO> getByPatient(long patient);

	public AllergyDTO[] addInBulk(AllergyDTO[] dtos);

	public List<AllergyDTO> getByMedicine(long medicine);
}
