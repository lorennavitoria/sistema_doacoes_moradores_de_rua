package edu.ifmt.doacoes.controller;

import edu.ifmt.doacoes.model.Administrador;
import edu.ifmt.doacoes.model.Doacao;
import edu.ifmt.doacoes.model.PoloDeDistribuicao;
import edu.ifmt.doacoes.model.TipoItemDoacao;
import edu.ifmt.doacoes.repositories.DoacaoRepository;
import edu.ifmt.doacoes.repositories.TipoItemDoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipoitemdoacao")
public class TipoItemDoacaoController {

    @Autowired
    TipoItemDoacaoRepository tipoItemDoacaoRepository;
    
    
    @Autowired
    DoacaoRepository doacaoRepository;

    // Create
    @PostMapping
    public ResponseEntity<TipoItemDoacao> createTipoItemDoacao(@RequestBody TipoItemDoacao tipoItemDoacao) {
        TipoItemDoacao savedTipoItemDoacao = tipoItemDoacaoRepository.save(tipoItemDoacao);
        return new ResponseEntity<>(savedTipoItemDoacao, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<TipoItemDoacao> getTipoItemDoacaoById(@PathVariable Long id) {
        Optional<TipoItemDoacao> tipoItemDoacao = tipoItemDoacaoRepository.findById(id);
        return tipoItemDoacao.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public List<TipoItemDoacao> getAllTipoItemDoacoes() {
        List<TipoItemDoacao> tipoItemDoacoes = tipoItemDoacaoRepository.findAll();
        return tipoItemDoacoes;
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<TipoItemDoacao> updateTipoItemDoacao(@PathVariable Long id, @RequestBody TipoItemDoacao tipoItemDoacao) {
        Optional<TipoItemDoacao> existingTipoItemDoacaoOptional = tipoItemDoacaoRepository.findById(id);
        if (existingTipoItemDoacaoOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TipoItemDoacao existingTipoItemDoacao = existingTipoItemDoacaoOptional.get();
        existingTipoItemDoacao.setNomeTipo(tipoItemDoacao.getNomeTipo());
        existingTipoItemDoacao.setUnidadeMedida(tipoItemDoacao.getUnidadeMedida());

        TipoItemDoacao updatedTipoItemDoacao = tipoItemDoacaoRepository.save(existingTipoItemDoacao);
        return new ResponseEntity<>(updatedTipoItemDoacao, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTipoItemDoacao(@PathVariable Long id){
        Optional<TipoItemDoacao> tipoItemDoacao = tipoItemDoacaoRepository.findById(id);
        if (tipoItemDoacao.isEmpty()) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Doação não encontrado");
            
        }
        
        List<Doacao> listaDoacao = doacaoRepository.buscaDoacaoByTipoDoacao(id);
        if (!listaDoacao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não pode excluir Tipo de Doação, pois existem registros vinculados a ele");
        }
        
      
        try {
            tipoItemDoacaoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tipo de Doação excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o tipo de doação");
        }
        
    }
}


