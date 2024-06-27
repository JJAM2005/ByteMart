package com.ByteMart.ByteMart.service;

import java.util.List;
import java.util.Optional;

import com.ByteMart.ByteMart.modelo.Usuario;

public interface IUsuarioService {
	List<Usuario> findAll();
	Optional<Usuario> findById(Integer id);
	Usuario save (Usuario usuario);
	Optional<Usuario> findByEmail(String email);
	List<Usuario> getAllUsuarios();
	
}