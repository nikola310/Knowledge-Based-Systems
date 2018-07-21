package com.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.model.User;

/**
 * @author Nikola
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
