package com.sbnz.doctor.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.TherapyDTO;
import com.sbnz.doctor.interfaces.converters.TherapyConverterInterface;
import com.sbnz.doctor.model.Therapy;
import com.sbnz.doctor.repository.DiseaseRepository;
import com.sbnz.doctor.repository.MedicineRepository;
import com.sbnz.doctor.repository.PatientRepository;
import com.sbnz.doctor.repository.UserRepository;

@Component
@Transactional
public class TherapyConverter implements TherapyConverterInterface {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private MedicineRepository medRepo;

	@Autowired
	private DiseaseRepository diseaseRepo;

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public TherapyDTO entityToDto(Therapy entity) {
		TherapyDTO dto;

		try {
			dto = mapper.map(entity, TherapyDTO.class);
			dto.setMedicineId(entity.getMedicine().getMedicineId());
			dto.setPatientId(entity.getPatient().getPatientId());
			dto.setUserId(entity.getUser().getUserId());
			dto.setDiseaseId(entity.getDisease().getDiseaseId());
			dto.setDiseaseCode(entity.getDisease().getDiseaseCode());
			dto.setDiseaseName(entity.getDisease().getDiseaseName());
			dto.setMedicineName(entity.getMedicine().getMedicineName());
			dto.setPatientName(entity.getPatient().getPatientName() + " " + entity.getPatient().getPatientSurname());
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
			entity.setUser(userRepo.getOne(dto.getUserId()));
			entity.setDisease(diseaseRepo.getOne(dto.getDiseaseId()));
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
