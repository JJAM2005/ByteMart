package com.ByteMart.ByteMart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ByteMart.ByteMart.repository.*;
import com.ByteMart.ByteMart.modelo.Usuario;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> findById(Integer id) {
		return usuarioRepository.findById(id);
	}
	@Override
	public Optional<Usuario> get(Integer id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	@Override
	public void update(Usuario usuario) {
		usuarioRepository.save(usuario);		
	}
	@Override
	public void delete(Integer id) {
		usuarioRepository.deleteById(id);		
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
		
}
