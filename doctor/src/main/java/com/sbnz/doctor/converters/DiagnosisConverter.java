package com.sbnz.doctor.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.DiagnosisDTO;
import com.sbnz.doctor.interfaces.converters.DiagnosisConverterInterface;
import com.sbnz.doctor.model.Diagnosis;
import com.sbnz.doctor.repository.DiseaseRepository;
import com.sbnz.doctor.repository.PatientRepository;

/**
 * 
 * @author Nikola
 *
 */
@Component
@Transactional
public class DiagnosisConverter implements DiagnosisConverterInterface {

	@Autowired
	private DiseaseRepository diseaseRepo;

	@Autowired
	private PatientRepository patientRepo;

	private ModelMapper mapper = new ModelMapper();

	@Override
	public DiagnosisDTO entityToDto(Diagnosis entity) {
		DiagnosisDTO dto;

		try {
			dto = mapper.map(entity, DiagnosisDTO.class);
			dto.setDiseaseId(entity.getDisease().getDiseaseId());
			dto.setPatientId(entity.getPatient().getPatientId());
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
			entity.setDisease(diseaseRepo.getOne(dto.getDiseaseId()));
			entity.setPatient(patientRepo.getOne(dto.getPatientId()));
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
