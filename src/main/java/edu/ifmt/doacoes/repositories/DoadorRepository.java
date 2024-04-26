package edu.ifmt.doacoes.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.ifmt.doacoes.model.Administrador;
import edu.ifmt.doacoes.model.Doacao;
import edu.ifmt.doacoes.model.Doador;
import edu.ifmt.doacoes.model.dto.DoadorRankingDTO;

public interface DoadorRepository extends JpaRepository<Doador, Long> {

	@Query("SELECT a FROM Doador a " +
	        "WHERE UPPER(a.nome) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR a.nome IS NULL " +
	        "OR UPPER(a.cpf) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR a.cpf IS NULL")
	Page<Doador> buscaGenerica(String pesquisa, Pageable pageable);
	
	
	@Query("SELECT d.nome, COUNT(d) " +
		       "FROM Doacao do " +
		       "JOIN do.doador d " +
		       "WHERE do.polo.idPolo = :idPolo " +
		       "GROUP BY d.idDoador " +
		       "ORDER BY COUNT(d) DESC")
		List<Object[]> rankingMaioresDoadoresByPoloTeste(Long idPolo);
		
		@Query("SELECT NEW edu.ifmt.doacoes.model.dto.DoadorRankingDTO(d.nome, COUNT(d)) " +
			       "FROM Doacao do " +
			       "JOIN do.doador d " +
			       "WHERE do.polo.idPolo = :idPolo " +
			       "GROUP BY d.idDoador, d.nome " +
			       "ORDER BY COUNT(d) DESC")
		List<DoadorRankingDTO> rankingMaioresDoadoresByPolo(Long idPolo);

	
	
}
