package com.elias.jobtracker.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elias.jobtracker.entities.Curriculo;

public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {

}
