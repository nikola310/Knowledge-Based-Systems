package com.sbnz.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Therapy;

public interface TherapyRepository extends JpaRepository<Therapy, Long> {

}
