package com.sbnz.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.dto.MedicineDTO;
import com.sbnz.interfaces.converters.MedicineConverterInterface;
import com.sbnz.interfaces.services.MedicineServiceInterface;
import com.sbnz.model.Medicine;
import com.sbnz.repository.MedicineRepository;

/**
 * 
 * @author Nikola
 *
 */
@Service
@Transactional
public class MedicineService implements MedicineServiceInterface {

	@Autowired
	private MedicineRepository repository;

	@Autowired
	private MedicineConverterInterface converter;

	@Override
	public MedicineDTO Create(MedicineDTO dto) {
		try {

			Medicine entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MedicineDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MedicineDTO> ReadAll() {
		ArrayList<MedicineDTO> list = new ArrayList<MedicineDTO>();

		try {
			for (Medicine entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public MedicineDTO Update(MedicineDTO dto) {
		try {
			repository.save(converter.DtoToEntity(dto));
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return null;
	}

	@Override
	public MedicineDTO Delete(long id) {
		Medicine entity = repository.findOne(id);
		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

}
