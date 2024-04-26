package edu.ifmt.doacoes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MoradorDeRua {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMorador;
    private String nome;
    private int idade;
    private String genero;
    private String localizacao;
    private String necessidades;
    private String cpf;
    
	public MoradorDeRua() {
		super();
	}

	
	public Long getIdMorador() {
		return idMorador;
	}

	public void setIdMorador(Long idMorador) {
		this.idMorador = idMorador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getNecessidades() {
		return necessidades;
	}

	public void setNecessidades(String necessidades) {
		this.necessidades = necessidades;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
	
    
    

}
