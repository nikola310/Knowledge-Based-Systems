package com.sbnz.doctor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.IngredientMedDTO;
import com.sbnz.doctor.interfaces.converters.IngredientMedConverterInterface;
import com.sbnz.doctor.interfaces.services.IngredientMedServiceInterface;
import com.sbnz.doctor.model.Ingredientmedicine;
import com.sbnz.doctor.repository.IngredientMedicineRepository;
import com.sbnz.doctor.repository.IngredientRepository;
import com.sbnz.doctor.repository.MedicineRepository;

@Service
@Transactional
public class IngredientMedService implements IngredientMedServiceInterface {

	@Autowired
	private IngredientMedicineRepository repository;

	@Autowired
	private IngredientMedConverterInterface converter;

	@Autowired
	private IngredientRepository ingredientRepo;

	@Autowired
	private MedicineRepository medRepo;

	@Override
	public IngredientMedDTO Create(IngredientMedDTO dto) {
		try {

			Ingredientmedicine entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IngredientMedDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IngredientMedDTO> ReadAll() {
		ArrayList<IngredientMedDTO> list = new ArrayList<IngredientMedDTO>();

		try {
			for (Ingredientmedicine entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public IngredientMedDTO Update(IngredientMedDTO dto) {
		try {

			Ingredientmedicine entity = repository.getOne(dto.getImId());

			if (entity != null) {
				entity.setIngredient(ingredientRepo.getOne(dto.getIngredientId()));
				entity.setMedicine(medRepo.getOne(dto.getMedicineId()));
				repository.save(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public IngredientMedDTO Delete(long id) {
		Ingredientmedicine entity = repository.getOne(id);
		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

	@Override
	public List<IngredientMedDTO> getByIngredient(long ing) {
		ArrayList<IngredientMedDTO> list = new ArrayList<IngredientMedDTO>();

		try {
			for (Ingredientmedicine entity : repository.findByIngredient(ingredientRepo.getOne(ing))) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public List<IngredientMedDTO> getByMedicine(long med) {
		ArrayList<IngredientMedDTO> list = new ArrayList<IngredientMedDTO>();

		try {
			for (Ingredientmedicine entity : repository.findByMedicine(medRepo.getOne(med))) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public IngredientMedDTO[] addInBulk(IngredientMedDTO[] dtos) {
		try {

			for (IngredientMedDTO tmp : dtos) {
				Ingredientmedicine entity = converter.DtoToEntity(tmp);
				repository.save(entity);
			}

			return dtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
