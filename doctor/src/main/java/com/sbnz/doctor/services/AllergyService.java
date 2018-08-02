package com.sbnz.doctor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sbnz.doctor.dto.AllergyDTO;
import com.sbnz.doctor.interfaces.converters.AllergyConverterInterface;
import com.sbnz.doctor.interfaces.services.AllergyServiceInterface;
import com.sbnz.doctor.model.Allergy;
import com.sbnz.doctor.repository.AllergyRepository;
import com.sbnz.doctor.repository.IngredientRepository;
import com.sbnz.doctor.repository.PatientRepository;

public class AllergyService implements AllergyServiceInterface {

	@Autowired
	private AllergyRepository repository;

	@Autowired
	private AllergyConverterInterface converter;

	@Autowired
	private IngredientRepository ingredientRepo;

	@Autowired
	private PatientRepository patientRepo;

	@Override
	public AllergyDTO Create(AllergyDTO dto) {
		try {

			Allergy entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AllergyDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AllergyDTO> ReadAll() {
		ArrayList<AllergyDTO> list = new ArrayList<AllergyDTO>();

		try {
			for (Allergy entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public AllergyDTO Update(AllergyDTO dto) {
		try {

			Allergy entity = repository.getOne(dto.getAllergyId());

			if (entity != null) {
				entity.setIngredient(ingredientRepo.getOne(dto.getIngredientId()));
				entity.setPatient(patientRepo.getOne(dto.getPatientId()));
				repository.save(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public AllergyDTO Delete(long id) {
		Allergy entity = repository.getOne(id);
		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

}
