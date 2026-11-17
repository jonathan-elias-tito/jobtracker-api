package com.elias.jobtracker.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String role;     // Cargo da vaga (ex: Desenvolvedor Java Júnior)
	private String company;  // Nome da empresa
	private Date applicationDate;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "curriculum_id")
	private Curriculum curriculum;

	public Application() {
	}

	public Application(Long id, String role, String company, Date applicationDate, String status, Curriculum curriculum) {
		this.id = id;
		this.role = role;
		this.company = company;
		this.applicationDate = applicationDate;
		this.status = status;
		this.curriculum = curriculum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
}