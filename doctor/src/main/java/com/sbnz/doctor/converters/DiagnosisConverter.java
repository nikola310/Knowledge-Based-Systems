package com.sbnz.doctor.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.DiagnosisDTO;
import com.sbnz.doctor.interfaces.converters.DiagnosisConverterInterface;
import com.sbnz.doctor.model.Diagnosis;

/**
 * 
 * @author Nikola
 *
 */
@Component
@Transactional
public class DiagnosisConverter implements DiagnosisConverterInterface {

	private ModelMapper mapper = new ModelMapper();

	@Override
	public DiagnosisDTO entityToDto(Diagnosis entity) {
		DiagnosisDTO dto;

		try {
			dto = mapper.map(entity, DiagnosisDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public Diagnosis DtoToEntity(DiagnosisDTO dto) {
		Diagnosis entity;

		try {
			entity = mapper.map(dto, Diagnosis.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
