package edu.ifmt.doacoes.model.dto;

public class PercentualMoradoresAtendidosDTO {
    private Long totalMoradoresDeRua;
    private Long moradoresReceberamDoacoes;
    private double percentualAtendidos;

    public PercentualMoradoresAtendidosDTO(Long totalMoradoresDeRua, Long moradoresReceberamDoacoes) {
        this.totalMoradoresDeRua = totalMoradoresDeRua;
        this.moradoresReceberamDoacoes = moradoresReceberamDoacoes;
        this.percentualAtendidos = calcularPercentualAtendidos(totalMoradoresDeRua, moradoresReceberamDoacoes);
    }

    private double calcularPercentualAtendidos(Long totalMoradoresDeRua, Long moradoresReceberamDoacoes) {
        if (totalMoradoresDeRua == 0) {
            return 0.0; //Evitar divisão por zero
        }

        return (double) moradoresReceberamDoacoes / totalMoradoresDeRua * 100;
    }

	public Long getTotalMoradoresDeRua() {
		return totalMoradoresDeRua;
	}

	public void setTotalMoradoresDeRua(Long totalMoradoresDeRua) {
		this.totalMoradoresDeRua = totalMoradoresDeRua;
	}

	public Long getMoradoresReceberamDoacoes() {
		return moradoresReceberamDoacoes;
	}

	public void setMoradoresReceberamDoacoes(Long moradoresReceberamDoacoes) {
		this.moradoresReceberamDoacoes = moradoresReceberamDoacoes;
	}

	public double getPercentualAtendidos() {
		return percentualAtendidos;
	}

	public void setPercentualAtendidos(double percentualAtendidos) {
		this.percentualAtendidos = percentualAtendidos;
	}

   
}
