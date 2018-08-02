package com.sbnz.doctor.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.PatientDTO;
import com.sbnz.doctor.interfaces.converters.PatientConverterInterface;
import com.sbnz.doctor.model.Patient;

@Component
@Transactional
public class PatientConverter implements PatientConverterInterface {

	private ModelMapper mapper = new ModelMapper();

//	@Autowired
//	private UserRepository userRepo;

	@Override
	public PatientDTO entityToDto(Patient entity) {
		PatientDTO dto;

		try {
			dto = mapper.map(entity, PatientDTO.class);
			// dto.setUserId(entity.getgetUser().getUserId());
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
//			entity.setUser(userRepo.getOne(dto.getUserId()));
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
