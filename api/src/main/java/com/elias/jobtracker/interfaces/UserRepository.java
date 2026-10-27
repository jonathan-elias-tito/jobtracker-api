package com.elias.jobtracker.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elias.jobtracker.entities.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {

}
