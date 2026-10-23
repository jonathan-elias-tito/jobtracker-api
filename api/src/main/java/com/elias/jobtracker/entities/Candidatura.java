package com.elias.jobtracker.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Candidatura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dataCandidatura;
	private String status;
	@ManyToOne
	@JoinColumn(name="curriculo_id")
	private Curriculo curriculo;

	public Candidatura() {
	}

	public Candidatura(Long id, Date data_candidatura, String status, Curriculo curriculo_id) {
		super();
		this.id = id;
		this.dataCandidatura = dataCandidatura;
		this.status = status;
		this.curriculo=curriculo;
	}

	public Date getDataCandidatura() {
		return dataCandidatura;
	}

	public Long getid() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public Curriculo getCurriculo() {
		return curriculo;
	}
}
