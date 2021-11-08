package br.org.generation.farmacia.controller;

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

import br.org.generation.farmacia.model.Categoria;
import br.org.generation.farmacia.repository.categoriaRepository;

@RestController
@CrossOrigin (origins = "*", allowedHeaders="*")
@RequestMapping ("/categorias")
public class categoriaController {
	
	@Autowired
	private  categoriaRepository CategoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(CategoriaRepository.findAll());
		
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Categoria> getById(@PathVariable long id){
		return CategoriaRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity<List<Categoria>> getByCategoria(@PathVariable String categoria){
		return ResponseEntity.ok(CategoriaRepository.findAllByTipoContainingIgnoreCase(categoria));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria tipo){
		return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaRepository.save(tipo));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria) {
					
		return CategoriaRepository.findById(categoria.getId())
				.map(resposta -> {
					return ResponseEntity.ok().body(CategoriaRepository.save(categoria));
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable long id) {
		
		return CategoriaRepository.findById(id)
				.map(resposta -> {
					CategoriaRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());

	}
	}