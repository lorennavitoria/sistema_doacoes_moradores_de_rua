package edu.ifmt.doacoes.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.ifmt.doacoes.model.Administrador;
import edu.ifmt.doacoes.model.MoradorDeRua;

public interface MoradorDeRuaRepository extends JpaRepository<MoradorDeRua, Long> {

	@Query("SELECT a FROM MoradorDeRua a " +
	        "WHERE (UPPER(a.nome) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR a.nome IS NULL) " +
	        "AND (UPPER(a.localizacao) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR a.localizacao IS NULL) " +
	        "AND ((UPPER(a.cpf) LIKE UPPER(CONCAT('%', :pesquisa, '%'))) OR a.cpf IS NULL)")
	Page<MoradorDeRua> buscaGenerica(String pesquisa, Pageable pageable);


    @Query("SELECT COUNT(m) FROM MoradorDeRua m")
    Long countTotalMoradoresDeRua();

}
