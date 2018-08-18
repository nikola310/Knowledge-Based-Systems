package com.sbnz.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Medicine;
import com.sbnz.doctor.model.Medicineallergy;
import com.sbnz.doctor.model.Patient;

public interface MedicineallergyRepository extends JpaRepository<Medicineallergy, Long> {

	public List<Medicineallergy> findByPatient(Patient p);

	public List<Medicineallergy> findByMedicine(Medicine m);

}
