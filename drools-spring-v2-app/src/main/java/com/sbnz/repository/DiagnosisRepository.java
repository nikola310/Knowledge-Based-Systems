package com.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.model.Diagnosis;

/**
 * @author Nikola
 *
 */
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

}
