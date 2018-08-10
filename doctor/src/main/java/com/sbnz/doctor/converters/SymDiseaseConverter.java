package com.sbnz.doctor.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.SymDiseaseDTO;
import com.sbnz.doctor.interfaces.converters.SymDiseaseConverterInterface;
import com.sbnz.doctor.model.Symptomdisease;
import com.sbnz.doctor.repository.DiseaseRepository;
import com.sbnz.doctor.repository.SymptomRepository;

@Component
@Transactional
public class SymDiseaseConverter implements SymDiseaseConverterInterface {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private DiseaseRepository diseaseRepo;

	@Autowired
	private SymptomRepository symptomRepo;

	@Override
	public SymDiseaseDTO entityToDto(Symptomdisease entity) {
		SymDiseaseDTO dto;

		try {
			dto = mapper.map(entity, SymDiseaseDTO.class);
			dto.setDiseaseId(entity.getSdId());
			dto.setDiseaseName(entity.getDisease().getDiseaseName());
			dto.setSymptomId(entity.getSymptom().getSymId());
			dto.setSymptomDesc(entity.getSymptom().getSymDesc());
			dto.setSdSpecific(entity.isSdSpecific());
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public Symptomdisease DtoToEntity(SymDiseaseDTO dto) {
		Symptomdisease entity;

		try {
			entity = mapper.map(dto, Symptomdisease.class);
			entity.setDisease(diseaseRepo.getOne(dto.getDiseaseId()));
			entity.setSymptom(symptomRepo.getOne(dto.getSymptomId()));
			entity.setSdSpecific(dto.isSdSpecific());
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
