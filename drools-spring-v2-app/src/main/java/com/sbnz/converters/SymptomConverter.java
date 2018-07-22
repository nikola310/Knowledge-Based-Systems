package com.sbnz.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.dto.SymptomDTO;
import com.sbnz.interfaces.converters.SymptomConverterInterface;
import com.sbnz.model.Symptom;

@Component
@Transactional
public class SymptomConverter implements SymptomConverterInterface {

	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public SymptomDTO entityToDto(Symptom entity) {
		SymptomDTO dto;

		try {
			dto = mapper.map(entity, SymptomDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public Symptom DtoToEntity(SymptomDTO dto) {
		Symptom entity;

		try {
			entity = mapper.map(dto, Symptom.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
