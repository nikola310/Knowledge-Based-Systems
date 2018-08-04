package com.sbnz.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Allergy;
import com.sbnz.doctor.model.Ingredient;
import com.sbnz.doctor.model.Patient;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {

	public List<Allergy> findByIngredient(Ingredient i);

	public List<Allergy> findByPatient(Patient p);

}
