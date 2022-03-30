package com.ite.springsecurity.modelo.service;

import java.util.List;

import com.ite.springsecurity.modelo.entity.LineasPedido;

public interface IntLineasPedidoDao {

	List<LineasPedido> findAll();

	List<LineasPedido> findByPedido(int idPedido);

	LineasPedido findByOrden(int numOrden);

	int insertOne(LineasPedido lineasPedido);

	int deleteOne(int numOrden);

}
