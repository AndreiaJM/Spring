package br.org.generation.lojaDeGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.lojaDeGames.model.Categoria;

public interface categoriaRepository extends JpaRepository<Categoria, Long>{
	
	public List<Categoria>findAllByGeneroContainingIgnoreCase ( String  genero );

}
