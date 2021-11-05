package br.org.generation.lojaDeGames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.lojaDeGames.model.Categoria;
import br.org.generation.lojaDeGames.repository.categoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin ( origins  =  " * " , allowedHeaders  =  " * " )
public class categoriaController {

	@Autowired
	private categoriaRepository CategoriaRepository;
	
	@GetMapping
	private ResponseEntity <List<Categoria>> findAll(){
		return ResponseEntity.ok(CategoriaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity <Categoria> findById(@PathVariable long id){
		return CategoriaRepository.findById(id)
				.map(resp-> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
					
	}
	
	@GetMapping("/genero{genero}")
	private ResponseEntity<List<Categoria>> getByGenero(@PathVariable String genero){
		return ResponseEntity.ok(CategoriaRepository.findAllByGeneroContainingIgnoreCase(genero));
	}
	
	@PostMapping
	private ResponseEntity<Categoria> postCategoria (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaRepository.save(categoria));
	}
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id) {
		
		CategoriaRepository.deleteById(id);
	}
}
