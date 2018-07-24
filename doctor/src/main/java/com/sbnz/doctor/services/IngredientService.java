package com.sbnz.doctor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.IngredientDTO;
import com.sbnz.doctor.interfaces.converters.IngredientConverterInterface;
import com.sbnz.doctor.interfaces.services.IngredientServiceInterface;
import com.sbnz.doctor.model.Ingredient;
import com.sbnz.doctor.repository.IngredientRepository;
/**
 * 
 * @author Nikola
 *
 */
@Service
@Transactional
public class IngredientService implements IngredientServiceInterface {

	@Autowired
	private IngredientRepository repository;

	@Autowired
	private IngredientConverterInterface converter;

	@Override
	public IngredientDTO Create(IngredientDTO dto) {
		try {

			Ingredient entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IngredientDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IngredientDTO> ReadAll() {
		ArrayList<IngredientDTO> list = new ArrayList<IngredientDTO>();

		try {
			for (Ingredient entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public IngredientDTO Update(IngredientDTO dto) {
		try {
			repository.save(converter.DtoToEntity(dto));
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return null;
	}

	@Override
	public IngredientDTO Delete(long id) {
		Ingredient entity = repository.getOne(id);
		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

}
