package com.sbnz.doctor.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.DiseaseDTO;
import com.sbnz.doctor.interfaces.converters.DiseaseConverterInterface;
import com.sbnz.doctor.model.Disease;
import com.sbnz.doctor.repository.DiagnosisRepository;
import com.sbnz.doctor.repository.MedicineRepository;

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
			if (entity.getMedicine() != null) {
				dto.setMedicineId(entity.getMedicine().getMedicineId());
			}
			if (entity.getDiagnosis() != null) {
				dto.setDiagnosisId(entity.getDiagnosis().getDiagnosisId());
			}
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
			if (dto.getDiagnosisId() != 0) {
				entity.setDiagnosis(diagnosisRepo.getOne(dto.getDiagnosisId()));
			} else {
				entity.setDiagnosis(null);
			}

			if (dto.getMedicineId() != 0) {
				entity.setMedicine(medicineRepo.getOne(dto.getMedicineId()));
			} else {
				entity.setMedicine(null);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
