package com.sbnz.doctor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.IngredientDTO;
import com.sbnz.doctor.dto.MedicineDTO;
import com.sbnz.doctor.interfaces.converters.IngredientConverterInterface;
import com.sbnz.doctor.interfaces.converters.MedicineConverterInterface;
import com.sbnz.doctor.interfaces.services.MedicineServiceInterface;
import com.sbnz.doctor.model.Ingredientmedicine;
import com.sbnz.doctor.model.Medicine;
import com.sbnz.doctor.model.Medicineallergy;
import com.sbnz.doctor.repository.IngredientMedicineRepository;
import com.sbnz.doctor.repository.MedicineRepository;
import com.sbnz.doctor.repository.MedicineallergyRepository;

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
	private IngredientMedicineRepository imRepo;

	@Autowired
	private MedicineConverterInterface converter;

	@Autowired
	private IngredientConverterInterface ingrConverter;

	@Autowired
	private MedicineallergyRepository medAllergyRepo;

	@Override
	public MedicineDTO Create(MedicineDTO dto) {
		try {

			Medicine entity = converter.DtoToEntity(dto);
			System.out.println(entity.getMedicineId());
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
		Medicine entity = repository.getOne(id);

		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		for (Ingredientmedicine toDelete : imRepo.findByMedicine(entity)) {
			imRepo.delete(toDelete);
		}

		for (Medicineallergy toDelete : medAllergyRepo.findByMedicine(entity)) {
			medAllergyRepo.delete(toDelete);
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

	@Override
	public List<IngredientDTO> getIngrediens(MedicineDTO m) {
		try {
			ArrayList<IngredientDTO> retVal = new ArrayList<>();
			for (Ingredientmedicine im : imRepo.findByMedicine(repository.getOne(m.getMedicineId()))) {
				retVal.add(ingrConverter.entityToDto(im.getIngredient()));
			}
			return retVal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
