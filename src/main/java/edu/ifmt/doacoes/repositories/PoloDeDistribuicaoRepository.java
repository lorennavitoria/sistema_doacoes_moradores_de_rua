package edu.ifmt.doacoes.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import edu.ifmt.doacoes.model.PoloDeDistribuicao;

@Repository
@EnableJpaRepositories
public interface PoloDeDistribuicaoRepository extends JpaRepository<PoloDeDistribuicao, Long>{

	
	@Query("SELECT p FROM PoloDeDistribuicao p " +
	        "WHERE (UPPER(p.nomeDoPolo) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR p.nomeDoPolo IS NULL) " +
	        "OR (UPPER(p.localizacao) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR p.localizacao IS NULL)")
	Page<PoloDeDistribuicao> buscaGenerica(String pesquisa, Pageable pageable);

}
