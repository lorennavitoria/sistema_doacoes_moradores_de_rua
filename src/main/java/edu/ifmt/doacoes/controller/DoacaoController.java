package edu.ifmt.doacoes.controller;

import edu.ifmt.doacoes.model.Doacao;
import edu.ifmt.doacoes.repositories.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {

    @Autowired
    private DoacaoRepository doacaoRepository;

    // Create
    @PostMapping
    public ResponseEntity<Doacao> createDoacao(@RequestBody Doacao doacao) {
        doacao.setData(LocalDateTime.now()); // Definindo a data atual para a doação
        Doacao savedDoacao = doacaoRepository.save(doacao);
        return new ResponseEntity<>(savedDoacao, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<Doacao> getDoacaoById(@PathVariable Long id) {
        Optional<Doacao> doacao = doacaoRepository.findById(id);
        return doacao.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public Iterable<Doacao> getAllDoacoes() {
        return doacaoRepository.findAll();
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Doacao> updateDoacao(@PathVariable Long id, @RequestBody Doacao doacao) {
        Optional<Doacao> existingDoacaoOptional = doacaoRepository.findById(id);
        if (existingDoacaoOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Doacao existingDoacao = existingDoacaoOptional.get();
        existingDoacao.setDoador(doacao.getDoador());
        existingDoacao.setPolo(doacao.getPolo());
        existingDoacao.setTipoItemDoacao(doacao.getTipoItemDoacao());
        existingDoacao.setQuantidade(doacao.getQuantidade());

        Doacao updatedDoacao = doacaoRepository.save(existingDoacao);
        return new ResponseEntity<>(updatedDoacao, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoacao(@PathVariable Long id) {
        Optional<Doacao> doacao = doacaoRepository.findById(id);
        if (doacao.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        doacaoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscagenerica")
    public Page<Doacao> buscaGenerica(@RequestParam String pesquisa, Pageable pageable) {
        return doacaoRepository.buscaGenerica(pesquisa, pageable);
    }
}
