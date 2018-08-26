package com.sbnz.doctor.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.DiseaseDTO;
import com.sbnz.doctor.interfaces.converters.DiseaseConverterInterface;
import com.sbnz.doctor.model.Disease;

@Component
@Transactional
public class DiseaseConverter implements DiseaseConverterInterface {

	private ModelMapper mapper = new ModelMapper();

	@Override
	public DiseaseDTO entityToDto(Disease entity) {
		DiseaseDTO dto;

		try {
			dto = mapper.map(entity, DiseaseDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public Disease DtoToEntity(DiseaseDTO dto) {
		Disease entity;

		try {
			entity = mapper.map(dto, Disease.class);

		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
