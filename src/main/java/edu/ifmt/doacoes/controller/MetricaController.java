package edu.ifmt.doacoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifmt.doacoes.model.dto.DoadorRankingDTO;
import edu.ifmt.doacoes.model.dto.PercentualMoradoresAtendidosDTO;
import edu.ifmt.doacoes.repositories.DoadorRepository;
import edu.ifmt.doacoes.repositories.MoradorDeRuaRepository;
import edu.ifmt.doacoes.repositories.MoradorDoacaoRepository;

@RestController
@RequestMapping("/metrica")
public class MetricaController {

	@Autowired
	DoadorRepository doadorRepository;

	@Autowired
	MoradorDeRuaRepository moradorDeRuaRepository;

	@Autowired
	MoradorDoacaoRepository moradorDoacaoRepository;

	@GetMapping("/maioresdoadoes/{idPolo}")
	public List<DoadorRankingDTO> rankingMaioresDoadoresByPolo(@PathVariable Long idPolo) {
		return doadorRepository.rankingMaioresDoadoresByPolo(idPolo);
	}

	@GetMapping("/percentual-moradores-atendidos")
	public PercentualMoradoresAtendidosDTO calcularPercentualMoradoresAtendidos() {
		Long totalMoradoresDeRua = moradorDeRuaRepository.count();
		Long moradoresReceberamDoacoes = moradorDoacaoRepository.countDistinctMoradores();

		return new PercentualMoradoresAtendidosDTO(totalMoradoresDeRua, moradoresReceberamDoacoes);
	}
}
