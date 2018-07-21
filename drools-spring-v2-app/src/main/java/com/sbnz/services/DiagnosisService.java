package com.sbnz.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.dto.DiagnosisDTO;
import com.sbnz.interfaces.converters.DiagnosisConverterInterface;
import com.sbnz.interfaces.services.DiagnosisServiceInterface;
import com.sbnz.model.Diagnosis;
import com.sbnz.repository.DiagnosisRepository;

/**
 * @author Nikola
 *
 */
@Service
@Transactional
public class DiagnosisService implements DiagnosisServiceInterface {

	@Autowired
	private DiagnosisRepository repository;

	@Autowired
	private DiagnosisConverterInterface converter;

	@Override
	public DiagnosisDTO Create(DiagnosisDTO dto) {
		try {

			Diagnosis entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DiagnosisDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DiagnosisDTO> ReadAll() {
		ArrayList<DiagnosisDTO> list = new ArrayList<DiagnosisDTO>();

		try {
			for (Diagnosis entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public DiagnosisDTO Update(DiagnosisDTO dto) {
		try {

			Diagnosis entity = repository.findOne(dto.getDiagnosisId());

			if (entity != null) {
				entity.setDiagnosisDate(dto.getDiagnosisDate());
				repository.save(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public DiagnosisDTO Delete(long id) {
		Diagnosis entity = repository.findOne(id);
		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}
		
		repository.delete(entity);

		return converter.entityToDto(entity);
	}

}
