package com.elias.jobtracker.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elias.jobtracker.entities.Candidatura;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {

}
