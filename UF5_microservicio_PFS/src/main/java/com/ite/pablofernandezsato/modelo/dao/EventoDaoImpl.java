package com.ite.pablofernandezsato.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.pablofernandezsato.modelo.entitysbeans.Evento;
import com.ite.pablofernandezsato.modelo.entitysbeans.Reserva;
import com.ite.pablofernandezsato.modelo.repository.IntEventoRepository;

@Service
public class EventoDaoImpl implements IntEventoDao {

	@Autowired
	private IntEventoRepository iever;
	@Autowired
	private IntReservaDao ires;

	@Override
	public List<Evento> findAll() {

		return iever.findAll();
	}

	@Override
	public Evento findById(int idEvento) {

		return iever.findById(idEvento).orElse(null);
	}

	@Override
	public List<Evento> findDestacados() {

		return iever.findDestacados();
	}

	@Override
	public List<Evento> findByName(String nombre) {
		List<Evento> lista = iever.findByNombre(nombre.toLowerCase());
		return lista;
	}

	@Override
	public List<Evento> findActivos() {

		return iever.findActivos();
	}

	@Override
	public int findPlazas(int idEvento) {
		List<Reserva> listaEvento = ires.findByEvento(idEvento);
		int cantidad = 0;
		for (Reserva ele : listaEvento) {
			cantidad += ele.getCantidad();
		}
		Evento evento = findById(idEvento);
		int plazas = evento.getAforoMaximo();
		int quedan = plazas - cantidad;
		return quedan;
	}

	@Override
	public int insertOne(Evento evento) {
		if (findById(evento.getIdEvento()) == null) {
			iever.save(evento);
			return 1;
		} else {

			return 0;
		}
	}

	@Override
	public int updateOne(Evento evento) {
		if (findById(evento.getIdEvento()) != null) {
			iever.save(evento);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteById(int idEvento) {
		if (findById(idEvento) != null) {
			iever.deleteById(idEvento);
			return 1;
		} else {
			return 0;
		}
	}

}
