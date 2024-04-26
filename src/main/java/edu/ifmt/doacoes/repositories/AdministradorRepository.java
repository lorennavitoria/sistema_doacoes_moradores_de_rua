package edu.ifmt.doacoes.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import edu.ifmt.doacoes.model.Administrador;
import edu.ifmt.doacoes.model.Doacao;

@Repository
@EnableJpaRepositories
public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

	@Query("SELECT a FROM Administrador a "
	        + "WHERE UPPER(a.nome) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR a.nome IS NULL "
	        + "OR UPPER(a.funcao) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR a.funcao IS NULL "
	        + "OR UPPER(a.polo.nomeDoPolo) LIKE UPPER(CONCAT('%', :pesquisa, '%')) OR a.polo.nomeDoPolo IS NULL ")
	Page<Administrador> buscaGenerica(String pesquisa, Pageable pageable);

	
	@Query("SELECT a FROM Administrador a "
			+"WHERE a.polo.idPolo = :idPolo")
	List<Administrador> buscaAdministradorByIdPolo(Long idPolo);

}
