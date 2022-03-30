package com.ite.springsecurity.modelo.service;

import java.util.List;

import com.ite.springsecurity.modelo.entity.Tema;

public interface IntTemasDao {

	List<Tema> findAll();

	Tema findById(int idTema);

	int insertOne(Tema tema);

	int deleteOne(int idTema);

}
