package com.ByteMart.ByteMart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ByteMart.ByteMart.modelo.Producto;
import com.ByteMart.ByteMart.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository productoRepository;
	
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }
	public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Optional<Producto> get(Integer id) {
		return productoRepository.findById(id);
	}

	@Override
	public void update(Producto producto) {
		productoRepository.save(producto);		
	}

	@Override
	public void delete(Integer id) {
		productoRepository.deleteById(id);		
	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

}
