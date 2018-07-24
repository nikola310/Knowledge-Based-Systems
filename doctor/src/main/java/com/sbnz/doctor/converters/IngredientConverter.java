package com.sbnz.doctor.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.IngredientDTO;
import com.sbnz.doctor.interfaces.converters.IngredientConverterInterface;
import com.sbnz.doctor.model.Ingredient;

@Component
@Transactional
public class IngredientConverter implements IngredientConverterInterface {

	private ModelMapper mapper = new ModelMapper();

	@Override
	public IngredientDTO entityToDto(Ingredient entity) {
		IngredientDTO dto;

		try {
			dto = mapper.map(entity, IngredientDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public Ingredient DtoToEntity(IngredientDTO dto) {
		Ingredient entity;

		try {
			entity = mapper.map(dto, Ingredient.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
