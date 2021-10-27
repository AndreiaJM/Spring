package br.org.generation.PrimeiroProjeto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PrimeiroProjeto")
public class Exercicio1 {
	
	@GetMapping
	public String habilidadesMentalidades(){
		
		return "Persistencia, Crescimento e Atenção aos detalhes";
	}

}
