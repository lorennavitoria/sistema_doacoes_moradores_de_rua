package edu.ifmt.doacoes.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.ifmt.doacoes.model.MoradorDoacao;

public interface MoradorDoacaoRepository extends JpaRepository<MoradorDoacao, Long> {
	
	@Query("SELECT p FROM MoradorDoacao p " +
	        "WHERE (UPPER(p.morador.nome) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR p.morador.nome IS NULL) " +
	        "OR (UPPER(p.doacao.tipoItemDoacao.nomeTipo) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR p.doacao.tipoItemDoacao.nomeTipo IS NULL)")
	Page<MoradorDoacao> buscaGenerica(String pesquisa, Pageable pageable);


	  @Query("SELECT COUNT(DISTINCT m.morador) FROM MoradorDoacao m")
	  Long countDistinctMoradores();
}
