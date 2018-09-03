package com.sbnz.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.Patient;
import com.sbnz.doctor.model.Therapy;
import com.sbnz.doctor.model.User;

public interface TherapyRepository extends JpaRepository<Therapy, Long> {

	public List<Therapy> findByPatient(Patient p);

	public List<Therapy> getTherapyByUser(User u);

}
