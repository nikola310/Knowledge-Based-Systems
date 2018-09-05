package com.sbnz.doctor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.TherapyDTO;
import com.sbnz.doctor.dto.TherapyReport;
import com.sbnz.doctor.interfaces.converters.TherapyConverterInterface;
import com.sbnz.doctor.interfaces.services.TherapyServiceInterface;
import com.sbnz.doctor.model.Therapy;
import com.sbnz.doctor.repository.MedicineRepository;
import com.sbnz.doctor.repository.PatientRepository;
import com.sbnz.doctor.repository.TherapyRepository;
import com.sbnz.doctor.repository.UserRepository;

@Service
@Transactional
public class TherapyService implements TherapyServiceInterface {

	@Autowired
	private TherapyRepository repository;

	@Autowired
	private TherapyConverterInterface converter;

	@Autowired
	private MedicineRepository medRepo;

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public TherapyDTO Create(TherapyDTO dto) {
		try {

			Therapy entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TherapyDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TherapyDTO> ReadAll() {
		ArrayList<TherapyDTO> list = new ArrayList<TherapyDTO>();

		try {
			for (Therapy entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public TherapyDTO Update(TherapyDTO dto) {
		try {

			Therapy entity = repository.getOne(dto.getTherapyId());

			if (entity != null) {
				entity.setMedicine(medRepo.getOne(dto.getMedicineId()));
				entity.setPatient(patientRepo.getOne(dto.getPatientId()));
				repository.save(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public TherapyDTO Delete(long id) {
		Therapy entity = repository.getOne(id);
		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

	@Override
	public List<TherapyDTO> addInBulk(List<TherapyDTO> dtos) {
		try {

			for (TherapyDTO tmp : dtos) {
				Therapy entity = converter.DtoToEntity(tmp);
				repository.save(entity);
			}

			return dtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<TherapyReport> getInLastSixMonths() {
		try {
			ArrayList<TherapyReport> retVal = new ArrayList<>();
			for (Therapy entity : repository.findAll()) {
				retVal.add(new TherapyReport(entity));
			}
			return retVal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<TherapyReport> getTherapyReport() {
		try {
			ArrayList<TherapyReport> retVal = new ArrayList<>();
			for (Therapy entity : repository.findAll()) {
				retVal.add(new TherapyReport(entity));
			}
			return retVal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<TherapyDTO> readByUser(long user) {
		ArrayList<TherapyDTO> list = new ArrayList<TherapyDTO>();

		try {
			for (Therapy entity : repository.getTherapyByUser(userRepo.getOne(user))) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

}
