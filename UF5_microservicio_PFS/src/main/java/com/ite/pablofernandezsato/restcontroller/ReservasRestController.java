package com.ite.pablofernandezsato.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ite.pablofernandezsato.modelo.dao.IntReservaDao;
import com.ite.pablofernandezsato.modelo.entitysbeans.Reserva;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/reservas")
public class ReservasRestController {

	@Autowired
	private IntReservaDao ires;

	// ------------------------------------------------- FINDALL
	@GetMapping("/todos")
	public List<Reserva> findAll() {
		return ires.findAll();
	}

	// ------------------------------------------------- FINDBYID
	@GetMapping("/verUno/{idReserva}")
	public Reserva findById(@PathVariable("idReserva") int idReserva) {
		return ires.findById(idReserva);
	}

	// ------------------------------------------------- FINDBYEVENTO
	@GetMapping("/verXEvento/{idEvento}")
	public List<Reserva> findByEvento(@PathVariable("idEvento") int idEvento) {
		return ires.findByEvento(idEvento);
	}

	// ------------------------------------------------- FINDBYUSUARIO
	@GetMapping("/verXUsuario/{idUsuario}")
	public List<Reserva> findByUsuario(@PathVariable("idUsuario") int idUsuario) {
		return ires.findByUsuario(idUsuario);
	}

	// ------------------------------------------------- ALTA
	@PostMapping("/alta")
	public String insertOne(@RequestBody Reserva reserva) {
		return (ires.insertOne(reserva) == 1) ? "Reserva dada de Alta" : "Error al Reservar";
	}

	// ------------------------------------------------- UPDATE
	@PutMapping("/modificar")
	public String updateOne(@RequestBody Reserva reserva) {
		return (ires.updateReserva(reserva) == 1) ? "Reserva modificada" : "Error al modificar";
	}

	// ------------------------------------------------- DELETE
	@DeleteMapping("/eliminar/{idReserva}")
	public String deleteOne(@PathVariable("idReserva") int idReserva) {
		return (ires.deleteById(idReserva) == 1) ? "Reserva eliminada" : "Error al Eliminar";
	}

}
