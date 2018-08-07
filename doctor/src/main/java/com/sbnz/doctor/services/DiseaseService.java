package com.sbnz.doctor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.DiseaseDTO;
import com.sbnz.doctor.interfaces.converters.DiseaseConverterInterface;
import com.sbnz.doctor.model.Disease;
import com.sbnz.doctor.model.Symptomdisease;
import com.sbnz.doctor.repository.DiseaseRepository;
import com.sbnz.doctor.repository.SymptomDiseaseRepository;

/**
 * 
 * @author Nikola
 *
 */
@Service
@Transactional
public class DiseaseService implements com.sbnz.doctor.interfaces.services.DiseaseServiceInterface {

	@Autowired
	private DiseaseRepository repository;

	@Autowired
	private DiseaseConverterInterface converter;

	@Autowired
	private SymptomDiseaseRepository symDisRepo;

	@Override
	public DiseaseDTO Create(DiseaseDTO dto) {
		try {

			Disease entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DiseaseDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DiseaseDTO> ReadAll() {
		ArrayList<DiseaseDTO> list = new ArrayList<DiseaseDTO>();

		try {
			for (Disease entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public DiseaseDTO Update(DiseaseDTO dto) {
		try {
			repository.save(converter.DtoToEntity(dto));
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return null;
	}

	@Override
	public DiseaseDTO Delete(long id) {
		Disease entity = repository.getOne(id);

		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		for (Symptomdisease toDelete : symDisRepo.findByDisease(entity)) {
			symDisRepo.delete(toDelete);
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

	@Override
	public DiseaseDTO getByCode(String code) {
		try {
			return converter.entityToDto(repository.getDiseaseByDiseaseCode(code));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
