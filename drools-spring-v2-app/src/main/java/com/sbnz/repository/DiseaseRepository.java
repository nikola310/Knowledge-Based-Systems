package com.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.model.Disease;

/**
 * @author Nikola
 *
 */
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

}
