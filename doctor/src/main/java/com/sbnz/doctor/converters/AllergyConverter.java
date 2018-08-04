package com.sbnz.doctor.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.AllergyDTO;
import com.sbnz.doctor.interfaces.converters.AllergyConverterInterface;
import com.sbnz.doctor.model.Allergy;
import com.sbnz.doctor.repository.IngredientRepository;
import com.sbnz.doctor.repository.PatientRepository;

@Component
@Transactional
public class AllergyConverter implements AllergyConverterInterface {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private IngredientRepository ingredientRepo;

	@Override
	public AllergyDTO entityToDto(Allergy entity) {
		AllergyDTO dto;

		try {
			dto = new AllergyDTO();
			dto.setAllergyId(entity.getAllergyId());
			dto.setIngredientId(entity.getIngredient().getIngredientId());
			dto.setIngredientName(entity.getIngredient().getIngredientName());
			dto.setPatientId(entity.getPatient().getPatientId());
			dto.setPatient(entity.getPatient().getPatientName() + " " + entity.getPatient().getPatientSurname());
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public Allergy DtoToEntity(AllergyDTO dto) {
		Allergy entity;

		try {
			entity = mapper.map(dto, Allergy.class);
			entity.setIngredient(ingredientRepo.getOne(dto.getIngredientId()));
			entity.setPatient(patientRepo.getOne(dto.getPatientId()));
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
