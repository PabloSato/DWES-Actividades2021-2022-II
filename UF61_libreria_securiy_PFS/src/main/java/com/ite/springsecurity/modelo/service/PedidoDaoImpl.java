package com.ite.springsecurity.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.springsecurity.modelo.entity.Pedido;
import com.ite.springsecurity.modelo.repository.IntPedidoRepository;

@Service
public class PedidoDaoImpl implements IntPedidoDao {

	@Autowired
	private IntPedidoRepository iped;

	@Override
	public List<Pedido> findAll() {

		return iped.findAll();
	}

	@Override
	public List<Pedido> findByUsuario(String username) {

		return iped.findByUsuario(username);
	}

	@Override
	public Pedido findById(int idPedido) {

		return iped.findById(idPedido).orElse(null);
	}

	@Override
	public int insertOne(Pedido pedido) {
		int filas = 0;
		try {
			iped.save(pedido);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int deleteOne(int idPedido) {
		int filas = 0;
		try {
			iped.deleteById(idPedido);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

}
