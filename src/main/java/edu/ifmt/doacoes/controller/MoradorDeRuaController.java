package edu.ifmt.doacoes.controller;

import edu.ifmt.doacoes.model.MoradorDeRua;
import edu.ifmt.doacoes.repositories.MoradorDeRuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moradorderua")
public class MoradorDeRuaController {

    @Autowired
    private MoradorDeRuaRepository moradorDeRuaRepository;

    // Create
    @PostMapping
    public ResponseEntity<MoradorDeRua> createMoradorDeRua(@RequestBody MoradorDeRua moradorDeRua) {
        MoradorDeRua savedMoradorDeRua = moradorDeRuaRepository.save(moradorDeRua);
        return new ResponseEntity<>(savedMoradorDeRua, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<MoradorDeRua> getMoradorDeRuaById(@PathVariable Long id) {
        Optional<MoradorDeRua> moradorDeRua = moradorDeRuaRepository.findById(id);
        return moradorDeRua.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public List<MoradorDeRua> getAllMoradoresDeRua() {
        return moradorDeRuaRepository.findAll();
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<MoradorDeRua> updateMoradorDeRua(@PathVariable Long id, @RequestBody MoradorDeRua moradorDeRua) {
        Optional<MoradorDeRua> existingMoradorDeRuaOptional = moradorDeRuaRepository.findById(id);
        if (existingMoradorDeRuaOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        MoradorDeRua existingMoradorDeRua = existingMoradorDeRuaOptional.get();
        existingMoradorDeRua.setNome(moradorDeRua.getNome());
        existingMoradorDeRua.setIdade(moradorDeRua.getIdade());
        existingMoradorDeRua.setGenero(moradorDeRua.getGenero());
        existingMoradorDeRua.setLocalizacao(moradorDeRua.getLocalizacao());
        existingMoradorDeRua.setNecessidades(moradorDeRua.getNecessidades());
        existingMoradorDeRua.setCpf(moradorDeRua.getCpf());

        MoradorDeRua updatedMoradorDeRua = moradorDeRuaRepository.save(existingMoradorDeRua);
        return new ResponseEntity<>(updatedMoradorDeRua, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoradorDeRua(@PathVariable Long id) {
        Optional<MoradorDeRua> moradorDeRua = moradorDeRuaRepository.findById(id);
        if (moradorDeRua.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        moradorDeRuaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscagenerica")
    public Page<MoradorDeRua> buscaGenerica(@RequestParam String pesquisa, Pageable pageable) {
        return moradorDeRuaRepository.buscaGenerica(pesquisa, pageable);
    }
}
