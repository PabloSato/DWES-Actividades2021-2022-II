package com.ite.springsecurity.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.springsecurity.modelo.entity.LineasPedido;
import com.ite.springsecurity.modelo.repository.IntLineasPedidoRepository;

@Service
public class LineasPedidoDaoImpl implements IntLineasPedidoDao {

	@Autowired
	private IntLineasPedidoRepository ilinp;

	@Override
	public List<LineasPedido> findAll() {

		return ilinp.findAll();
	}

	@Override
	public List<LineasPedido> findByPedido(int idPedido) {

		return ilinp.findByPedido(idPedido);
	}

	@Override
	public LineasPedido findByOrden(int numOrden) {

		return ilinp.findById(numOrden).orElse(null);
	}

	@Override
	public int insertOne(LineasPedido lineasPedido) {
		int filas = 0;
		try {
			ilinp.save(lineasPedido);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int deleteOne(int numOrden) {
		int filas = 0;
		try {
			ilinp.deleteById(numOrden);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

}
