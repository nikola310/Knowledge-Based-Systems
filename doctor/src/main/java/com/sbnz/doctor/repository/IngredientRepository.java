/**
 * 
 */
package com.sbnz.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Ingredient;

/**
 * @author Nikola
 *
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
