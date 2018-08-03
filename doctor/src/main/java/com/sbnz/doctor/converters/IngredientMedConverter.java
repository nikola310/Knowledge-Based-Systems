package com.sbnz.doctor.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.IngredientMedDTO;
import com.sbnz.doctor.interfaces.converters.IngredientMedConverterInterface;
import com.sbnz.doctor.model.Ingredientmedicine;
import com.sbnz.doctor.repository.IngredientRepository;
import com.sbnz.doctor.repository.MedicineRepository;

@Component
@Transactional
public class IngredientMedConverter implements IngredientMedConverterInterface {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private MedicineRepository medRepo;

	@Autowired
	private IngredientRepository ingredientRepo;

	@Override
	public IngredientMedDTO entityToDto(Ingredientmedicine entity) {
		IngredientMedDTO dto;

		try {
			dto = mapper.map(entity, IngredientMedDTO.class);
			dto.setIngredientId(entity.getIngredient().getIngredientId());
			dto.setIngredientName(entity.getIngredient().getIngredientName());
			dto.setMedicineId(entity.getMedicine().getMedicineId());
			dto.setMedicineName(entity.getMedicine().getMedicineName());
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public Ingredientmedicine DtoToEntity(IngredientMedDTO dto) {
		Ingredientmedicine entity;

		try {
			entity = mapper.map(dto, Ingredientmedicine.class);
			entity.setIngredient(ingredientRepo.getOne(dto.getIngredientId()));
			entity.setMedicine(medRepo.getOne(dto.getMedicineId()));
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
