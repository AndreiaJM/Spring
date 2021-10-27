package br.org.generation.SegundoProjeto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SegundoProjeto")
public class Objetivos {
	
	@GetMapping
	public String ObjetivosDeAprendizagem(){
		
		return "Spring Boot e voltar a estudar inglês";
	}

}
