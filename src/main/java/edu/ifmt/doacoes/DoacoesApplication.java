package edu.ifmt.doacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifmt.doacoes.controller.AdministradorController;
import edu.ifmt.doacoes.model.Administrador;
import edu.ifmt.doacoes.repositories.AdministradorRepository;
import edu.ifmt.doacoes.utils.JPAUtil;
import jakarta.persistence.EntityManager;

@SpringBootApplication
public class DoacoesApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(DoacoesApplication.class, args);
		
		
	}

}
