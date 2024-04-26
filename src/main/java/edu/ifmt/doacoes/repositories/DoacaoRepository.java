package edu.ifmt.doacoes.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.ifmt.doacoes.model.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao, Long>{

	@Query("SELECT a FROM Doacao a "
	        + "WHERE UPPER(a.doador.nome) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR a.doador.nome IS NULL "
	        + "OR UPPER(a.polo.nomeDoPolo) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR a.polo.nomeDoPolo IS NULL "
	        + "OR UPPER(a.tipoItemDoacao.nomeTipo) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR a.tipoItemDoacao.nomeTipo IS NULL")
	Page<Doacao> buscaGenerica(String pesquisa, Pageable pageable);
	
	
	@Query("SELECT d FROM Doacao d "
			+"WHERE d.doador.idDoador =:idDoador")
	List<Doacao> buscaDoacaoByIdDoador(Long idDoador);

	
	@Query("SELECT d FROM Doacao d "
			+"WHERE d.tipoItemDoacao.idTipoItemDoacao =:idTipoDoacao")
	List<Doacao> buscaDoacaoByTipoDoacao(Long idTipoDoacao);
}
