package com.elias.jobtracker.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elias.jobtracker.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

public User findByEmail(String email);
}
