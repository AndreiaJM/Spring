package br.org.generation.lojaDeGames.controller;

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

import br.org.generation.lojaDeGames.model.Produto;
import br.org.generation.lojaDeGames.repository.produtoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin ( origins  =  " * " , allowedHeaders  =  " * " )
public class produtoController {
	
	@Autowired
	private produtoRepository ProdutoRepository;
	
	@GetMapping
	private ResponseEntity <List<Produto>>findAll() {
		return ResponseEntity.ok(ProdutoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Produto> findById(@PathVariable long id){
		return ProdutoRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
		}
	
	@GetMapping("/nome/{nome}")
	private ResponseEntity<List<Produto>> findByDescricao(@PathVariable String nome){
		return ResponseEntity.ok(ProdutoRepository.findAllByNomeContainingIgnoreCase(nome));
		
	}
	@PutMapping
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(ProdutoRepository.save(produto));
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoRepository.save(produto));
	}
	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable long id){
		ProdutoRepository.deleteById(id);		
	}

	// Consulta pelo preço maior do que o preço digitado emm ordem crescente
	
	@GetMapping("/preco_maior/{preco}")
	public ResponseEntity<List<Produto>> getPrecoMaiorQue(@PathVariable BigDecimal preco){ 
		return ResponseEntity.ok(ProdutoRepository.findByPrecoGreaterThanOrderByPreco(preco));
	}
	
	// Consulta pelo preço menor do que o preço digitado em ordem decrescente
	
	@GetMapping("/preco_menor/{preco}")
	public ResponseEntity<List<Produto>> getPrecoMenorQue(@PathVariable BigDecimal preco){ 
		return ResponseEntity.ok(ProdutoRepository.findByPrecoLessThanOrderByPrecoDesc(preco));
	}
	
}
