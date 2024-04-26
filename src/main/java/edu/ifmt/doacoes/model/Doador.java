package edu.ifmt.doacoes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Doador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDoador;
	private String nome;
	private String contato;
	private String cpf;

	public Doador() {
		super();
	}

	public Long getIdDoador() {
		return idDoador;
	}

	
	public void setIdDoador(Long idDoador) {
		this.idDoador = idDoador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	

}
