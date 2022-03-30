package com.ite.springsecurity.modelo.service;

import java.util.List;

import com.ite.springsecurity.modelo.entity.Pedido;

public interface IntPedidoDao {

	List<Pedido> findAll();

	List<Pedido> findByUsuario(String username);

	Pedido findById(int idPedido);

	int insertOne(Pedido pedido);

	int deleteOne(int idPedido);

}
