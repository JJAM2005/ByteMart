package com.ByteMart.ByteMart.service;

import java.util.List;
import java.util.Optional;

import com.ByteMart.ByteMart.modelo.Orden;
import com.ByteMart.ByteMart.modelo.Usuario;

public interface IOrdenService {
	List<Orden> findAll();
	Optional<Orden> findById(Integer id);
	Orden save (Orden orden);
	String generarNumeroOrden();
	List<Orden> findByUsuario (Usuario usuario);
}
