/**
 * 
 */
package com.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.model.Symptom;

/**
 * @author Nikola
 *
 */
public interface SymptomRepository extends JpaRepository<Symptom, Long> {

}
