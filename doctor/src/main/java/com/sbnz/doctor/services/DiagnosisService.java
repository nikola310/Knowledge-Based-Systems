package com.sbnz.doctor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.DiagnosisDTO;
import com.sbnz.doctor.dto.MyDiagnosisDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.converters.DiagnosisConverterInterface;
import com.sbnz.doctor.interfaces.converters.UserConverterInterface;
import com.sbnz.doctor.interfaces.services.DiagnosisServiceInterface;
import com.sbnz.doctor.model.Diagnosis;
import com.sbnz.doctor.model.Patient;
import com.sbnz.doctor.repository.DiagnosisRepository;

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

	@Autowired
	private UserConverterInterface userConverter;

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

			Diagnosis entity = repository.getOne(dto.getDiagnosisId());

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
		Diagnosis entity = repository.getOne(id);
		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

	@Override
	public List<MyDiagnosisDTO> getDiagnoses(UserDTO user) {
		ArrayList<MyDiagnosisDTO> list = new ArrayList<MyDiagnosisDTO>();

		try {
			for (Diagnosis entity : repository.getDiagnosisByUser(userConverter.DtoToEntity(user))) {
				MyDiagnosisDTO tmp = new MyDiagnosisDTO();
				tmp.setDate(entity.getDiagnosisDate());
				tmp.setDiagnosisId(entity.getDiagnosisId());
				tmp.setDisease(entity.getDisease().getDiseaseName());
				Patient p = entity.getPatient();
				tmp.setPatient(p.getPatientName() + " " + p.getPatientSurname());
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}