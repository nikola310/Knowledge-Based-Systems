package com.sbnz.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.dto.PatientDTO;
import com.sbnz.interfaces.converters.PatientConverterInterface;
import com.sbnz.model.Patient;
import com.sbnz.repository.UserRepository;

@Component
@Transactional
public class PatientConverter implements PatientConverterInterface {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private UserRepository userRepo;

	@Override
	public PatientDTO entityToDto(Patient entity) {
		PatientDTO dto;

		try {
			dto = mapper.map(entity, PatientDTO.class);
			dto.setUserId(entity.getUser().getUserId());
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public Patient DtoToEntity(PatientDTO dto) {
		Patient entity;

		try {
			entity = mapper.map(dto, Patient.class);
			entity.setUser(userRepo.getOne(dto.getUserId()));
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
