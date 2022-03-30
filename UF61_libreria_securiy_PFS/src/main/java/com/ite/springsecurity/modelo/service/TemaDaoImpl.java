package com.ite.springsecurity.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.springsecurity.modelo.entity.Tema;
import com.ite.springsecurity.modelo.repository.IntTemasRepository;

@Service
public class TemaDaoImpl implements IntTemasDao {

	@Autowired
	private IntTemasRepository item;

	@Override
	public List<Tema> findAll() {

		return item.findAll();
	}

	@Override
	public Tema findById(int idTema) {

		return item.findById(idTema).orElse(null);
	}

	@Override
	public int insertOne(Tema tema) {
		int filas = 0;
		try {
			item.save(tema);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int deleteOne(int idTema) {
		int filas = 0;
		try {
			item.deleteById(idTema);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

}
