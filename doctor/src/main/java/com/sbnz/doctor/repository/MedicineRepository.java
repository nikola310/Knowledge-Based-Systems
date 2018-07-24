package com.sbnz.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Medicine;

/**
 * @author Nikola
 *
 */
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}
