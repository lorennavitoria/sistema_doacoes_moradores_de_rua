package edu.ifmt.doacoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import edu.ifmt.doacoes.model.PoloDeDistribuicao;
import edu.ifmt.doacoes.model.TipoItemDoacao;

@Repository
@EnableJpaRepositories
public interface TipoItemDoacaoRepository extends JpaRepository<TipoItemDoacao, Long>{

}
