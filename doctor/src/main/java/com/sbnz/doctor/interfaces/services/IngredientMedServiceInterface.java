package com.sbnz.doctor.interfaces.services;

import java.util.List;

import com.sbnz.doctor.dto.IngredientMedDTO;

public interface IngredientMedServiceInterface extends ServiceInterface<IngredientMedDTO> {

	public List<IngredientMedDTO> getByIngredient(long ing);

	public List<IngredientMedDTO> getByMedicine(long med);

	public IngredientMedDTO[] addInBulk(IngredientMedDTO[] dtos);
}
