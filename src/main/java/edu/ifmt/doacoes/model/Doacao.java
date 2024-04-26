package edu.ifmt.doacoes.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class Doacao {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDoacao;
	@ManyToOne
	@JoinColumn(name = "idDoador")
	private Doador doador;
	@ManyToOne
	@JoinColumn(name = "idPolo")
	private PoloDeDistribuicao polo;

	private int quantidade;
	private LocalDateTime data;
	
	@ManyToOne
	@JoinColumn(name = "idTipoItemDoacao")
	private TipoItemDoacao tipoItemDoacao;
	
	public Doacao() {
		super();
	}

	public Long getIdDoacao() {
		return idDoacao;
	}

	public void setIdDoacao(Long idDoacao) {
		this.idDoacao = idDoacao;
	}

	public Doador getDoador() {
		return doador;
	}

	public void setDoador(Doador doador) {
		this.doador = doador;
	}

	public PoloDeDistribuicao getPolo() {
		return polo;
	}

	public void setPolo(PoloDeDistribuicao polo) {
		this.polo = polo;
	}

	
	public int getQuantidade() {
		return quantidade;
	}
	

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public TipoItemDoacao getTipoItemDoacao() {
		return tipoItemDoacao;
	}

	public void setTipoItemDoacao(TipoItemDoacao tipoItemDoacao) {
		this.tipoItemDoacao = tipoItemDoacao;
	}
	
	

}
