package com.ite.pablofernandezsato.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.pablofernandezsato.modelo.entitysbeans.Tipo;
import com.ite.pablofernandezsato.modelo.repository.IntTipoRepository;

@Service
public class TipoDaoImpl implements IntTipoDao {

	@Autowired
	private IntTipoRepository itipor;

	@Override
	public List<Tipo> findAll() {

		return itipor.findAll();
	}

	@Override
	public Tipo findById(int idTipo) {

		return itipor.findById(idTipo).orElse(null);
	}

	@Override
	public int inserOne(Tipo tipo) {
		if (findById(tipo.getIdTipo()) == null) {
			itipor.save(tipo);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int updateOne(Tipo tipo) {
		if (findById(tipo.getIdTipo()) != null) {
			itipor.save(tipo);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteById(int idTipo) {
		if (findById(idTipo) != null) {
			itipor.deleteById(idTipo);
			return 1;
		} else {
			return 0;
		}
	}

}
