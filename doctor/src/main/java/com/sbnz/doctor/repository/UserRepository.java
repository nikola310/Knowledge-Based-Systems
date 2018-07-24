package com.sbnz.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.doctor.model.User;

/**
 * @author Nikola
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	public User getUserByUserName(String username);

}
