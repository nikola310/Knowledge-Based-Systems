package com.sbnz.doctor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.SymDiseaseDTO;
import com.sbnz.doctor.interfaces.converters.SymDiseaseConverterInterface;
import com.sbnz.doctor.interfaces.services.SymDiseaseServiceInterface;
import com.sbnz.doctor.model.Symptomdisease;
import com.sbnz.doctor.repository.DiseaseRepository;
import com.sbnz.doctor.repository.SymptomDiseaseRepository;
import com.sbnz.doctor.repository.SymptomRepository;

@Service
@Transactional
public class SymDiseaseService implements SymDiseaseServiceInterface {

	@Autowired
	private SymptomDiseaseRepository repository;

	@Autowired
	private SymDiseaseConverterInterface converter;

	@Autowired
	private SymptomRepository symptomRepo;

	@Autowired
	private DiseaseRepository diseaseRepo;

	@Override
	public SymDiseaseDTO Create(SymDiseaseDTO dto) {
		try {

			Symptomdisease entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SymDiseaseDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SymDiseaseDTO> ReadAll() {
		ArrayList<SymDiseaseDTO> list = new ArrayList<SymDiseaseDTO>();

		try {
			for (Symptomdisease entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public SymDiseaseDTO Update(SymDiseaseDTO dto) {
		try {

			Symptomdisease entity = repository.getOne(dto.getSdId());

			if (entity != null) {
				entity.setDisease(diseaseRepo.getOne(dto.getDiseaseId()));
				entity.setSymptom(symptomRepo.getOne(dto.getSymptomId()));
				repository.save(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public SymDiseaseDTO Delete(long id) {
		Symptomdisease entity = repository.getOne(id);
		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

	@Override
	public List<SymDiseaseDTO> getBySymptom(long sym) {
		ArrayList<SymDiseaseDTO> list = new ArrayList<SymDiseaseDTO>();

		try {
			for (Symptomdisease entity : repository.findBySymptom(symptomRepo.getOne(sym))) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public List<SymDiseaseDTO> getByDisease(long dis) {
		ArrayList<SymDiseaseDTO> list = new ArrayList<SymDiseaseDTO>();

		try {
			for (Symptomdisease entity : repository.findByDisease(diseaseRepo.getOne(dis))) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public SymDiseaseDTO[] addInBulk(SymDiseaseDTO[] dtos) {
		try {

			for (SymDiseaseDTO tmp : dtos) {
				Symptomdisease entity = converter.DtoToEntity(tmp);
				repository.save(entity);
			}

			return dtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
