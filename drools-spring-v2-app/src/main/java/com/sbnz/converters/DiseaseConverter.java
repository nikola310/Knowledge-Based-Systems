package com.sbnz.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.dto.DiseaseDTO;
import com.sbnz.interfaces.converters.DiseaseConverterInterface;
import com.sbnz.model.Disease;
import com.sbnz.repository.DiagnosisRepository;
import com.sbnz.repository.MedicineRepository;

@Component
@Transactional
public class DiseaseConverter implements DiseaseConverterInterface {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private MedicineRepository medicineRepo;

	@Autowired
	private DiagnosisRepository diagnosisRepo;

	@Override
	public DiseaseDTO entityToDto(Disease entity) {
		DiseaseDTO dto;

		try {
			dto = mapper.map(entity, DiseaseDTO.class);
			dto.setMedicineId(entity.getMedicine().getMedicineId());
			dto.setDiagnosisId(entity.getDiagnosis().getDiagnosisId());
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
			entity.setDiagnosis(diagnosisRepo.getOne(dto.getDiagnosisId()));
			entity.setMedicine(medicineRepo.getOne(dto.getMedicineId()));
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
