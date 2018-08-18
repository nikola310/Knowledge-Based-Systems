package com.sbnz.doctor.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.MedicineallergyDTO;
import com.sbnz.doctor.interfaces.converters.MedicineAllergyConverterInterface;
import com.sbnz.doctor.model.Medicineallergy;
import com.sbnz.doctor.repository.MedicineRepository;
import com.sbnz.doctor.repository.PatientRepository;

@Component
@Transactional
public class MedicineAllergyConverter implements MedicineAllergyConverterInterface {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private MedicineRepository medRepo;

	@Autowired
	private PatientRepository patientRepo;

	@Override
	public MedicineallergyDTO entityToDto(Medicineallergy entity) {
		MedicineallergyDTO dto = new MedicineallergyDTO();

		try {
			dto.setMedId(entity.getMedId());
			dto.setMedicineId(entity.getMedicine().getMedicineId());
			dto.setMedicine(entity.getMedicine().getMedicineName());
			dto.setPatient(entity.getPatient().getPatientName() + " " + entity.getPatient().getPatientSurname());
			dto.setPatientId(entity.getPatient().getPatientId());
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public Medicineallergy DtoToEntity(MedicineallergyDTO dto) {
		Medicineallergy entity;

		try {
			entity = mapper.map(dto, Medicineallergy.class);
			entity.setMedicine(medRepo.getOne(dto.getMedicineId()));
			entity.setPatient(patientRepo.getOne(dto.getPatientId()));
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
