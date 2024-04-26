package edu.ifmt.doacoes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TipoItemDoacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoItemDoacao;
	
	private String nomeTipo;
	
	private String unidadeMedida;
	
	
	public TipoItemDoacao() {
		super();
	}


	public Long getIdTipoItemDoacao() {
		return idTipoItemDoacao;
	}


	public void setIdTipoItemDoacao(Long idTipoItemDoacao) {
		this.idTipoItemDoacao = idTipoItemDoacao;
	}


	public String getUnidadeMedida() {
		return unidadeMedida;
	}


	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}


	public String getNomeTipo() {
		return nomeTipo;
	}


	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}
	
	
	

}
