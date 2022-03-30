package com.ite.pablofernandezsato.modelo.dao;

import java.util.List;

import com.ite.pablofernandezsato.modelo.entitysbeans.Reserva;

public interface IntReservaDao {

	List<Reserva> findAll();
	List<Reserva> findByEvento(int idEvento);
	List<Reserva> findByUsuario(int idUsuario);
	Reserva findById(int idReserva);
	int insertOne(Reserva reserva);
	int updateReserva(Reserva reserva);
	int deleteById(int idReserva);
}
