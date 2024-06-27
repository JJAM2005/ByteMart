package com.ByteMart.ByteMart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ByteMart.ByteMart.modelo.DetalleOrden;
import com.ByteMart.ByteMart.repository.DetalleOrdenRepository;

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService{
	
	@Autowired
	private DetalleOrdenRepository detalleOrdenRepository;

	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}

}
