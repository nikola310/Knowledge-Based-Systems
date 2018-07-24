package com.sbnz.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Patient;

/**
 * 
 * @author Nikola
 *
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
