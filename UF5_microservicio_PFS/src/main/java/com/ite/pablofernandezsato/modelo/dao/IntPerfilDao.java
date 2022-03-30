package com.ite.pablofernandezsato.modelo.dao;

import java.util.List;

import com.ite.pablofernandezsato.modelo.entitysbeans.Perfile;

public interface IntPerfilDao {
	
	List<Perfile> findAll();
	Perfile findById(int idPerfil);
	int insertOne(Perfile perfile);
	int deleteOne(int idPerfile);

}
