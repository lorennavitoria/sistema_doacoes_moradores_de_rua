package edu.ifmt.doacoes.controller;

import edu.ifmt.doacoes.model.Doacao;
import edu.ifmt.doacoes.model.Doador;
import edu.ifmt.doacoes.repositories.DoacaoRepository;
import edu.ifmt.doacoes.repositories.DoadorRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doador")
public class DoadorController {

    @Autowired
    DoadorRepository doadorRepository;
    
    @Autowired
    DoacaoRepository doacaoRepository;

    // Create
    @PostMapping
    public ResponseEntity<Doador> createDoador(@RequestBody Doador doador) {
        Doador savedDoador = doadorRepository.save(doador);
        return new ResponseEntity<>(savedDoador, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<Doador> getDoadorById(@PathVariable Long id) {
        Optional<Doador> doador = doadorRepository.findById(id);
        return doador.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public List<Doador> getAllDoadores() {
        List<Doador> doadores = doadorRepository.findAll();
        return doadores;
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Doador> updateDoador(@PathVariable Long id, @RequestBody Doador doador) {
        Optional<Doador> existingDoadorOptional = doadorRepository.findById(id);
        if (existingDoadorOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Doador existingDoador = existingDoadorOptional.get();
        existingDoador.setNome(doador.getNome());
        existingDoador.setContato(doador.getContato());
        existingDoador.setCpf(doador.getCpf());

        Doador updatedDoador = doadorRepository.save(existingDoador);
        return new ResponseEntity<>(updatedDoador, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoador(@PathVariable Long id) {
        Optional<Doador> doadorOptional = doadorRepository.findById(id);
        if (doadorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doador não encontrado");
        }

        List<Doacao> listaDoacao = doacaoRepository.buscaDoacaoByIdDoador(id);
        if (!listaDoacao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não pode excluir Doador, pois existem registros vinculados a ele");
        }

        try {
            doadorRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Doador excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o doador");
        }
    }


    @GetMapping("/buscagenerica")
    public Page<Doador> buscaGenerica(@RequestParam String pesquisa, Pageable pageable) {
        return doadorRepository.buscaGenerica(pesquisa, pageable);
    }
}
