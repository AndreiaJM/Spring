package br.org.generation.lojagames.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.generation.lojagames.model.Usuario;
import br.org.generation.lojagames.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> cadastrarUsuario (Usuario usuario){
		
		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
		
	}

}
