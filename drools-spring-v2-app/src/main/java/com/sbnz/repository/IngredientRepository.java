/**
 * 
 */
package com.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.model.Ingredient;

/**
 * @author Nikola
 *
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
