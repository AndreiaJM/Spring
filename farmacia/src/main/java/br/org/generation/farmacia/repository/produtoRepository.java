package br.org.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.farmacia.model.Produto;
@Repository
public interface produtoRepository extends JpaRepository<Produto, Long>{
	
	public List <Produto> findAllByNomeContainingIgnoreCase(String nome);
	
	public List <Produto> findByNomeAndDistribuidor(String nome, String distribuidor);
	
	public List <Produto> findByNomeOrDistribuidor(String nome, String distribuidor);

}