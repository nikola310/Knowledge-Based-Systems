package com.sbnz.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.dto.MedicineDTO;
import com.sbnz.interfaces.converters.MedicineConverterInterface;
import com.sbnz.model.Medicine;

@Component
@Transactional
public class MedicineConverter implements MedicineConverterInterface {

	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public MedicineDTO entityToDto(Medicine entity) {
		MedicineDTO dto;

		try {
			dto = mapper.map(entity, MedicineDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public Medicine DtoToEntity(MedicineDTO dto) {
		Medicine entity;

		try {
			entity = mapper.map(dto, Medicine.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return entity;
	}

}
