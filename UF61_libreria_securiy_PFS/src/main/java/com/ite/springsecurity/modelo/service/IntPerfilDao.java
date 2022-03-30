package com.ite.springsecurity.modelo.service;

import java.util.List;

import com.ite.springsecurity.modelo.entity.Perfile;

public interface IntPerfilDao {

	List<Perfile> findAll();

	Perfile findById(int idPerfile);

	int insertOne(Perfile perfile);

	int deleteOne(int idPerfil);

}
