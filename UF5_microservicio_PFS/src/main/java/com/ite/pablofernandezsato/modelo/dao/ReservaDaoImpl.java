package com.ite.pablofernandezsato.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.pablofernandezsato.modelo.entitysbeans.Reserva;
import com.ite.pablofernandezsato.modelo.repository.IntReservaRepository;

@Service
public class ReservaDaoImpl implements IntReservaDao {

	@Autowired
	private IntReservaRepository ires;

	@Override
	public List<Reserva> findAll() {

		return ires.findAll();
	}

	@Override
	public List<Reserva> findByEvento(int idEvento) {
		
		return ires.findByEvento(idEvento);
	}

	@Override
	public List<Reserva> findByUsuario(int idUsuario) {
		
		return ires.findByUsuario(idUsuario);
	}

	@Override
	public Reserva findById(int idReserva) {

		return ires.findById(idReserva).orElse(null);
	}

	@Override
	public int insertOne(Reserva reserva) {
		if (findById(reserva.getIdReserva()) == null) {
			ires.save(reserva);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int updateReserva(Reserva reserva) {
		if (findById(reserva.getIdReserva()) != null) {
			ires.save(reserva);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteById(int idReserva) {
		if (findById(idReserva) != null) {
			ires.deleteById(idReserva);
			return 1;
		} else {
			return 0;
		}
	}

}
