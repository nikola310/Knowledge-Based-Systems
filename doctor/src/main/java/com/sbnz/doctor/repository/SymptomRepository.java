package com.sbnz.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Symptom;

/**
 * @author Nikola
 *
 */
public interface SymptomRepository extends JpaRepository<Symptom, Long> {

	public Symptom getSymptomBySymCode(String code);
}
