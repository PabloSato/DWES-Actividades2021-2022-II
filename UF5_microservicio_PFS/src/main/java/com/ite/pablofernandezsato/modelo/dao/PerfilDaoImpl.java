package com.ite.pablofernandezsato.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.pablofernandezsato.modelo.entitysbeans.Perfile;
import com.ite.pablofernandezsato.modelo.repository.IntPerfilRepository;

@Service
public class PerfilDaoImpl implements IntPerfilDao {

	@Autowired
	private IntPerfilRepository iper;

	@Override
	public List<Perfile> findAll() {

		return iper.findAll();
	}

	@Override
	public Perfile findById(int idPerfil) {

		return iper.findById(idPerfil).orElse(null);
	}

	@Override
	public int insertOne(Perfile perfile) {
		if (findById(perfile.getIdPerfil()) == null) {
			iper.save(perfile);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteOne(int idPerfil) {
		if (findById(idPerfil) != null) {
			iper.deleteById(idPerfil);
			return 1;
		} else {
			return 0;
		}
	}

}
