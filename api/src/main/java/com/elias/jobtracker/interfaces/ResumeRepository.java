package com.elias.jobtracker.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elias.jobtracker.entities.Curriculum;

public interface ResumeRepository extends JpaRepository<Curriculum, Long> {

}
