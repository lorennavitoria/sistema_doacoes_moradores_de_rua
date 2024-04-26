package edu.ifmt.doacoes.controller;

import edu.ifmt.doacoes.model.Administrador;
import edu.ifmt.doacoes.repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	AdministradorRepository administradorRepository;
	
    // Create
    @PostMapping
    public ResponseEntity<Administrador> createAdministrador(@RequestBody Administrador administrador) {
        Administrador savedAdministrador = administradorRepository.save(administrador);
        return new ResponseEntity<>(savedAdministrador, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> getAdministradorById(@PathVariable Long id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        return administrador.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public List<Administrador> getAllAdministradores() {
        List<Administrador> administradores = administradorRepository.findAll();
        return administradores;
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Administrador> updateAdministrador(@PathVariable Long id, @RequestBody Administrador administrador) {
        Optional<Administrador> existingAdministradorOptional = administradorRepository.findById(id);
        if (existingAdministradorOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Administrador existingAdministrador = existingAdministradorOptional.get();
        existingAdministrador.setNome(administrador.getNome());
        existingAdministrador.setFuncao(administrador.getFuncao());
        existingAdministrador.setContato(administrador.getContato());

        Administrador updatedAdministrador = administradorRepository.save(existingAdministrador);
        return new ResponseEntity<>(updatedAdministrador, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrador(@PathVariable Long id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        
        
        
        
        
        if (administrador.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        administradorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/buscagenerica")
    public Page<Administrador> buscaGenerica(@RequestParam String pesquisa, Pageable pageable) {
        return administradorRepository.buscaGenerica(pesquisa, pageable);
    }
    

}
