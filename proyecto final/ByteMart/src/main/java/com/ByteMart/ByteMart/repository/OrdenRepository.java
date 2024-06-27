package com.ByteMart.ByteMart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ByteMart.ByteMart.modelo.Orden;
import com.ByteMart.ByteMart.modelo.Usuario;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
	List<Orden> findByUsuario (Usuario usuario); 
    
}
