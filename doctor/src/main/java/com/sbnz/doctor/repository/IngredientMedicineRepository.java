package com.sbnz.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Ingredient;
import com.sbnz.doctor.model.Ingredientmedicine;
import com.sbnz.doctor.model.Medicine;

public interface IngredientMedicineRepository extends JpaRepository<Ingredientmedicine, Long> {

	public List<Ingredientmedicine> findByIngredient(Ingredient i);

	public List<Ingredientmedicine> findByMedicine(Medicine m);
	
}
