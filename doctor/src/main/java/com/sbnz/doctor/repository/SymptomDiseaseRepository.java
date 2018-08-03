package com.sbnz.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Disease;
import com.sbnz.doctor.model.Symptom;
import com.sbnz.doctor.model.Symptomdisease;

public interface SymptomDiseaseRepository extends JpaRepository<Symptomdisease, Long> {

	public List<Symptomdisease> findByDisease(Disease d);

	public List<Symptomdisease> findBySymptom(Symptom s);
}
