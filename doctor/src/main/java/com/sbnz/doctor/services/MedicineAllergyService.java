package com.sbnz.doctor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.MedicineallergyDTO;
import com.sbnz.doctor.interfaces.converters.MedicineAllergyConverterInterface;
import com.sbnz.doctor.interfaces.services.MedicineAllergyServiceInterface;
import com.sbnz.doctor.model.Medicineallergy;
import com.sbnz.doctor.repository.MedicineRepository;
import com.sbnz.doctor.repository.MedicineallergyRepository;
import com.sbnz.doctor.repository.PatientRepository;

/**
 * @author Nikola
 *
 */
@Service
@Transactional
public class MedicineAllergyService implements MedicineAllergyServiceInterface {

	@Autowired
	private MedicineallergyRepository repository;

	@Autowired
	private MedicineAllergyConverterInterface converter;

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private MedicineRepository medRepo;

	@Override
	public MedicineallergyDTO Create(MedicineallergyDTO dto) {
		try {

			Medicineallergy entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MedicineallergyDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MedicineallergyDTO> ReadAll() {
		ArrayList<MedicineallergyDTO> list = new ArrayList<MedicineallergyDTO>();

		try {
			for (Medicineallergy entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public MedicineallergyDTO Update(MedicineallergyDTO dto) {
		try {
			repository.save(converter.DtoToEntity(dto));
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return null;
	}

	@Override
	public MedicineallergyDTO Delete(long id) {
		Medicineallergy entity = repository.getOne(id);

		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

	@Override
	public List<MedicineallergyDTO> getByMedicine(long id) {
		ArrayList<MedicineallergyDTO> list = new ArrayList<MedicineallergyDTO>();

		try {
			for (Medicineallergy entity : repository.findByMedicine(medRepo.getOne(id))) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public List<MedicineallergyDTO> getByPatient(long id) {
		ArrayList<MedicineallergyDTO> list = new ArrayList<MedicineallergyDTO>();

		try {
			for (Medicineallergy entity : repository.findByPatient(patientRepo.getOne(id))) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public MedicineallergyDTO[] addInBulk(MedicineallergyDTO[] dtos) {
		try {

			for (MedicineallergyDTO tmp : dtos) {
				Medicineallergy entity = converter.DtoToEntity(tmp);
				repository.save(entity);
			}

			return dtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
