package com.ite.pablofernandezsato.modelo.dao;

import java.util.List;

import com.ite.pablofernandezsato.modelo.entitysbeans.Tipo;

public interface IntTipoDao {

	List<Tipo> findAll();
	Tipo findById(int idTipo);
	int inserOne(Tipo tipo);
	int updateOne(Tipo tipo);
	int deleteById(int idTipo);
}
