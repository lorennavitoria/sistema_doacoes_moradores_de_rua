package edu.ifmt.doacoes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PoloDeDistribuicao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPolo;
	private String nomeDoPolo;
	private String localizacao;
	private String horarioDeFuncionamento;
	
	public PoloDeDistribuicao() {
		super();
	}

	public Long getIdPolo() {
		return idPolo;
	}
	

	public void setIdPolo(Long idPolo) {
		this.idPolo = idPolo;
	}

	public String getNomeDoPolo() {
		return nomeDoPolo;
	}

	public void setNomeDoPolo(String nomeDoPolo) {
		this.nomeDoPolo = nomeDoPolo;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getHorarioDeFuncionamento() {
		return horarioDeFuncionamento;
	}

	public void setHorarioDeFuncionamento(String horarioDeFuncionamento) {
		this.horarioDeFuncionamento = horarioDeFuncionamento;
	}
	
	
	

}
