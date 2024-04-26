package edu.ifmt.doacoes.controller;

import edu.ifmt.doacoes.model.MoradorDoacao;
import edu.ifmt.doacoes.repositories.MoradorDoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moradordoacao")
public class MoradorDoacaoController {

    @Autowired
    private MoradorDoacaoRepository moradorDoacaoRepository;

    // Create
    @PostMapping
    public ResponseEntity<MoradorDoacao> createMoradorDoacao(@RequestBody MoradorDoacao moradorDoacao) {
        moradorDoacao.setDataHoraEntrega(LocalDateTime.now()); // Definindo a data e hora atuais para a entrega
        MoradorDoacao savedMoradorDoacao = moradorDoacaoRepository.save(moradorDoacao);
        return new ResponseEntity<>(savedMoradorDoacao, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<MoradorDoacao> getMoradorDoacaoById(@PathVariable Long id) {
        Optional<MoradorDoacao> moradorDoacao = moradorDoacaoRepository.findById(id);
        return moradorDoacao.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public List<MoradorDoacao> getAllMoradoresDoacao() {
        return moradorDoacaoRepository.findAll();
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<MoradorDoacao> updateMoradorDoacao(@PathVariable Long id, @RequestBody MoradorDoacao moradorDoacao) {
        Optional<MoradorDoacao> existingMoradorDoacaoOptional = moradorDoacaoRepository.findById(id);
        if (existingMoradorDoacaoOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        MoradorDoacao existingMoradorDoacao = existingMoradorDoacaoOptional.get();
        existingMoradorDoacao.setMorador(moradorDoacao.getMorador());
        existingMoradorDoacao.setDoacao(moradorDoacao.getDoacao());

        MoradorDoacao updatedMoradorDoacao = moradorDoacaoRepository.save(existingMoradorDoacao);
        return new ResponseEntity<>(updatedMoradorDoacao, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoradorDoacao(@PathVariable Long id) {
        Optional<MoradorDoacao> moradorDoacao = moradorDoacaoRepository.findById(id);
        if (moradorDoacao.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        moradorDoacaoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Generic Search
    @GetMapping("/buscagenerica")
    public Page<MoradorDoacao> searchMoradorDoacao(@RequestParam String pesquisa, Pageable pageable) {
        return moradorDoacaoRepository.buscaGenerica(pesquisa, pageable);
    }
}
