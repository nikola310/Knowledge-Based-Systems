package com.sbnz.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Diagnosis;

/**
 * @author Nikola
 *
 */
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

}
