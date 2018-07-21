package com.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.model.Patient;

/**
 * 
 * @author Nikola
 *
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
