package com.sbnz.doctor.interfaces.services;

import java.util.List;

import com.sbnz.doctor.dto.MedicineallergyDTO;

public interface MedicineAllergyServiceInterface extends ServiceInterface<MedicineallergyDTO> {

	List<MedicineallergyDTO> getByMedicine(long id);

	List<MedicineallergyDTO> getByPatient(long id);

	MedicineallergyDTO[] addInBulk(MedicineallergyDTO[] body);

}
