package com.sbnz.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Allergy;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {

}
