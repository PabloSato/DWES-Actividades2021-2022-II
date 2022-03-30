package com.ite.pablofernandezsato.modelo.dao;

import java.util.List;

import com.ite.pablofernandezsato.modelo.entitysbeans.Evento;

public interface IntEventoDao {
	
	List<Evento> findAll();
	Evento findById(int idEvento);
	List<Evento> findDestacados();
	List<Evento> findByName(String nombre);
	List<Evento> findActivos();
	int findPlazas(int idEvento);
	int insertOne(Evento evento);
	int updateOne(Evento evento);
	int deleteById(int idEvento);
}
