package com.sbnz.doctor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.PatientDTO;
import com.sbnz.doctor.interfaces.converters.PatientConverterInterface;
import com.sbnz.doctor.interfaces.services.PatientServiceInterface;
import com.sbnz.doctor.model.Allergy;
import com.sbnz.doctor.model.Patient;
import com.sbnz.doctor.repository.AllergyRepository;
import com.sbnz.doctor.repository.PatientRepository;

/**
 * 
 * @author Nikola
 *
 */
@Service
@Transactional
public class PatientService implements PatientServiceInterface {

	@Autowired
	private PatientRepository repository;

	@Autowired
	private PatientConverterInterface converter;

	@Autowired
	private AllergyRepository allergyRepo;

	@Override
	public PatientDTO Create(PatientDTO dto) {
		try {

			Patient entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PatientDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PatientDTO> ReadAll() {
		ArrayList<PatientDTO> list = new ArrayList<PatientDTO>();

		try {
			for (Patient entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public PatientDTO Update(PatientDTO dto) {
		try {
			repository.save(converter.DtoToEntity(dto));
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return null;
	}

	@Override
	public PatientDTO Delete(long id) {
		Patient entity = repository.getOne(id);
		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		for (Allergy a : allergyRepo.findByPatient(entity)) {
			allergyRepo.delete(a);
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

}
