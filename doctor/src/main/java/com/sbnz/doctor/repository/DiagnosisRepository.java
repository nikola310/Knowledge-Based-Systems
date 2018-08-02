package com.sbnz.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Diagnosis;
import com.sbnz.doctor.model.User;

/**
 * @author Nikola
 *
 */
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

	public List<Diagnosis> getDiagnosisByUser(User u);
}
