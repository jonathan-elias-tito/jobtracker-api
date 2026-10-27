package com.elias.jobtracker.entities;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Curriculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String resumoProfissional;
	private String experiencias;
	private String formacao;
	@ManyToOne
	@JoinColumn(name="user_id")
	private Usuario user;

	public Curriculo() {
	}

	public Curriculo(Long id, String resumo_profissional, String experiencias, String formacao, Usuario user_Id) {
		this.id = id;
		this.resumoProfissional = resumoProfissional;
		this.experiencias = experiencias;
		this.formacao = formacao;
		this.user = user;
	}

	public String getResumoProfissional() {
		return resumoProfissional;
	}

	public void setResumoProfissional(String resumo_profissional) {
		this.resumoProfissional = resumo_profissional;
	}

	public String getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(String experiencias) {
		this.experiencias = experiencias;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public Long getId() {
		return id;
	}

	public Usuario getUser() {
		return user;
	}

}
