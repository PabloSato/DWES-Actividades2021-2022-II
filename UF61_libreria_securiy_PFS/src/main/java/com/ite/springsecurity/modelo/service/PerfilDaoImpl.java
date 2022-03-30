package com.ite.springsecurity.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.springsecurity.modelo.entity.Perfile;
import com.ite.springsecurity.modelo.repository.IntPerfilRepository;

@Service
public class PerfilDaoImpl implements IntPerfilDao {

	@Autowired
	private IntPerfilRepository iper;

	@Override
	public List<Perfile> findAll() {

		return iper.findAll();
	}

	@Override
	public Perfile findById(int idPerfile) {

		return iper.findById(idPerfile).orElse(null);
	}

	@Override
	public int insertOne(Perfile perfile) {
		int filas = 0;
		try {
			iper.save(perfile);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int deleteOne(int idPperfile) {
		int filas = 0;
		try {
			iper.deleteById(idPperfile);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

}
