package com.ByteMart.ByteMart.service;

import java.util.List;
import java.util.Optional;

import com.ByteMart.ByteMart.modelo.Usuario;

public interface IUsuarioService {
	List<Usuario> findAll();
	Optional<Usuario> findById(Integer id);
	public Optional<Usuario> get(Integer id);
	Usuario save (Usuario usuario);
	public void update(Usuario usuario);
	public void delete(Integer id);
	Optional<Usuario> findByEmail(String email);
	List<Usuario> getAllUsuarios();
	
}