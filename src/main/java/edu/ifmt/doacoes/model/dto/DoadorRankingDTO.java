package edu.ifmt.doacoes.model.dto;

public class DoadorRankingDTO {
    private String nome;
    private Long quantidadeDoacoes;
    
    public DoadorRankingDTO(String nome, Long quantidadeDoacoes) {
        this.nome = nome;
        this.quantidadeDoacoes = quantidadeDoacoes;
    }
    
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getQuantidadeDoacoes() {
		return quantidadeDoacoes;
	}
	public void setQuantidadeDoacoes(Long quantidadeDoacoes) {
		this.quantidadeDoacoes = quantidadeDoacoes;
	}

   
}
