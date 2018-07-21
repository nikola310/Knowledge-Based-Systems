package com.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.model.Medicine;

/**
 * @author Nikola
 *
 */
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}
