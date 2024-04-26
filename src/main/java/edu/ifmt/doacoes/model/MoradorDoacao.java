package edu.ifmt.doacoes.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MoradorDoacao {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idMoradorDoacao;
	    @ManyToOne
	    @JoinColumn(name = "idMorador")
	    private MoradorDeRua morador;
	    @ManyToOne
	    @JoinColumn(name = "idDoacao")
	    private Doacao doacao;
	    private LocalDateTime dataHoraEntrega;
		public MoradorDoacao() {
			super();
		}
		
		public Long getIdMoradorDoacao() {
			return idMoradorDoacao;
		}
		public void setIdMoradorDoacao(Long idMoradorDoacao) {
			this.idMoradorDoacao = idMoradorDoacao;
		}
		public MoradorDeRua getMorador() {
			return morador;
		}
		public void setMorador(MoradorDeRua morador) {
			this.morador = morador;
		}

		public Doacao getDoacao() {
			return doacao;
		}
		public void setDoacao(Doacao doacao) {
			this.doacao = doacao;
		}
		public LocalDateTime getDataHoraEntrega() {
			return dataHoraEntrega;
		}
		public void setDataHoraEntrega(LocalDateTime dataHoraEntrega) {
			this.dataHoraEntrega = dataHoraEntrega;
		}
	    
	    
	    
}
