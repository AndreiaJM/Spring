package br.org.generation.farmacia.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.farmacia.model.Produto;
import br.org.generation.farmacia.repository.produtoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin ( origins  =  " * " , allowedHeaders  =  " * " )
public class produtoController {
	
	@Autowired
	private produtoRepository ProdutoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findByAll() {
		return ResponseEntity.ok(ProdutoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable long id){
		return ProdutoRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
			
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String nome){
		return ResponseEntity.ok(ProdutoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping 
	public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){ 
		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoRepository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto) {
					
		return ProdutoRepository.findById(produto.getId())
				.map(resposta -> {
					return ResponseEntity.ok().body(ProdutoRepository.save(produto));
				})
				.orElse(ResponseEntity.notFound().build());
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable long id) {
		
		return ProdutoRepository.findById(id)
				.map(resposta -> {
					ProdutoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	

	
	@GetMapping("/nome/{nome}/oulaboratorio/{laboratorio}")
	public ResponseEntity<List<Produto>> getByNomeOuLaboratorio(@PathVariable String nome, @PathVariable String distribuidor){
		return ResponseEntity.ok(ProdutoRepository.findByNomeOrDistribuidor(nome, distribuidor));
	}
	

	
	@GetMapping("/nome/{nome}/elaboratorio/{laboratorio}")
	public ResponseEntity<List<Produto>> getByNomeELaboratorio(@PathVariable String nome, @PathVariable String distribuidor){
		return ResponseEntity.ok(ProdutoRepository.findByNomeAndDistribuidor(nome, distribuidor));
	}
	

}

