package edu.ifmt.doacoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ifmt.doacoes.model.Administrador;
import edu.ifmt.doacoes.model.Doacao;
import edu.ifmt.doacoes.model.Doador;
import edu.ifmt.doacoes.model.PoloDeDistribuicao;
import edu.ifmt.doacoes.repositories.AdministradorRepository;
import edu.ifmt.doacoes.repositories.DoacaoRepository;
import edu.ifmt.doacoes.repositories.DoadorRepository;
import edu.ifmt.doacoes.repositories.PoloDeDistribuicaoRepository;
import jakarta.transaction.Transactional;
@RestController
@RequestMapping("/polodistribuicao")
public class PoloDeDistribuicaoController {

	@Autowired
	PoloDeDistribuicaoRepository poloDistribuicaoRepository;
	
	@Autowired
	AdministradorRepository administradorRepository;
	
	@Autowired DoacaoRepository doacaoRepository;
	
	@PostMapping
	public ResponseEntity<PoloDeDistribuicao> createPoloDistribuicao(@RequestBody PoloDeDistribuicao poloDistribuicao){
		PoloDeDistribuicao savedPolo = poloDistribuicaoRepository.save(poloDistribuicao);
		return new ResponseEntity<>(savedPolo, HttpStatus.CREATED);
	}
	
	 @GetMapping("/{id}")
	    public ResponseEntity<PoloDeDistribuicao> getPoloById(@PathVariable Long id) {
	        Optional<PoloDeDistribuicao> polo = poloDistribuicaoRepository.findById(id);
	        return polo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
	 
	   @GetMapping("/all")
	    public List<PoloDeDistribuicao> getAllPolosDistribuicoes() {
	        List<PoloDeDistribuicao> polos = poloDistribuicaoRepository.findAll();
	        return polos;
	    }
	   
	   @PutMapping("/{id}")
	    public ResponseEntity<PoloDeDistribuicao> updatePolo(@PathVariable Long id, @RequestBody PoloDeDistribuicao polo) {
	        Optional<PoloDeDistribuicao> existingPoloOptional = poloDistribuicaoRepository.findById(id);
	        if (existingPoloOptional.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        PoloDeDistribuicao existingPolo = existingPoloOptional.get();
	        existingPolo.setLocalizacao(polo.getLocalizacao());
	        existingPolo.setHorarioDeFuncionamento(polo.getHorarioDeFuncionamento());
	        existingPolo.setNomeDoPolo(polo.getNomeDoPolo());
	        PoloDeDistribuicao updatedPolo = poloDistribuicaoRepository.save(existingPolo);
	        return new ResponseEntity<>(updatedPolo, HttpStatus.OK);
	    }
	
   
	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deletePolo(@PathVariable Long id){
	        Optional<PoloDeDistribuicao> polo = poloDistribuicaoRepository.findById(id);
	        if (polo.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Polo não encontrado");
	            
	        }

	        List<Administrador> listaAdministrador = administradorRepository.buscaAdministradorByIdPolo(id);
	        List<Doacao> listaDoacao = doacaoRepository.buscaDoacaoByIdPolo(id);
	        if (!listaAdministrador.isEmpty() || !listaDoacao.isEmpty()) {
	           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não pode excluir Polo, pois existem registros vinculados a ele");
	        }
	        
	        try {
	            poloDistribuicaoRepository.deleteById(id);
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Polo excluído com sucesso");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o polo");
	        }

	    }
	    
	  
	    
	    
	    
	    
	    @GetMapping("/buscagenerica")
	    public Page<PoloDeDistribuicao> buscaGenerica(@RequestParam String pesquisa, Pageable pageable) {
	        return poloDistribuicaoRepository.buscaGenerica(pesquisa, pageable);
	    }
	
}
