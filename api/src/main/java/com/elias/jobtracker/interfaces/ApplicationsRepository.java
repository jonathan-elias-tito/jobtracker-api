package com.elias.jobtracker.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elias.jobtracker.entities.Application;

public interface ApplicationsRepository extends JpaRepository<Application, Long> {

}
