package com.sbnz.doctor.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.TherapyDTO;
import com.sbnz.doctor.interfaces.converters.TherapyConverterInterface;
import com.sbnz.doctor.model.Therapy;
import com.sbnz.doctor.repository.MedicineRepository;
import com.sbnz.doctor.repository.PatientRepository;

@Component
@Transactional
public class TherapyConverter implements TherapyConverterInterface {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private MedicineRepository medRepo;

	@Autowired
	private PatientRepository patientRepo;

	@Override
	public TherapyDTO entityToDto(Therapy entity) {
		TherapyDTO dto;

		try {
			dto = mapper.map(entity, TherapyDTO.class);
			dto.setMedicineId(entity.getMedicine().getMedicineId());
			dto.setPatientId(entity.getPatient().getPatientId());
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public Therapy DtoToEntity(TherapyDTO dto) {
		Therapy entity;

		try {
			entity = mapper.map(dto, Therapy.class);
			entity.setMedicine(medRepo.getOne(dto.getMedicineId()));
			entity.setPatient(patientRepo.getOne(dto.getPatientId()));
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
