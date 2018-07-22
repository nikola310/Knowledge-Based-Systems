package com.sbnz.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.dto.SymptomDTO;
import com.sbnz.interfaces.converters.SymptomConverterInterface;
import com.sbnz.interfaces.services.SymptomServiceInterface;
import com.sbnz.model.Symptom;
import com.sbnz.repository.SymptomRepository;

/**
 * 
 * @author Nikola
 *
 */
@Service
@Transactional
public class SymptomService implements SymptomServiceInterface {

	@Autowired
	private SymptomRepository repository;

	@Autowired
	private SymptomConverterInterface converter;

	@Override
	public SymptomDTO Create(SymptomDTO dto) {
		try {

			Symptom entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SymptomDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SymptomDTO> ReadAll() {
		ArrayList<SymptomDTO> list = new ArrayList<SymptomDTO>();

		try {
			for (Symptom entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public SymptomDTO Update(SymptomDTO dto) {
		try {
			repository.save(converter.DtoToEntity(dto));
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return null;
	}

	@Override
	public SymptomDTO Delete(long id) {
		Symptom entity = repository.findOne(id);
		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

}
