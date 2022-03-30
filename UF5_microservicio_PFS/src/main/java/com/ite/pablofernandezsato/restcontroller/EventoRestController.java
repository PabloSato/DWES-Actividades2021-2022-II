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

import com.ite.pablofernandezsato.modelo.dao.IntEventoDao;
import com.ite.pablofernandezsato.modelo.entitysbeans.Evento;
import com.ite.pablofernandezsato.modelo.entitysbeans.Plazas;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/eventos")
public class EventoRestController {

	@Autowired
	private IntEventoDao ieve;

	// -------------------------------------------------ACTIVOS
	@GetMapping("/activos")
	public List<Evento> verActivos() {
		return ieve.findActivos();
	}

	// -------------------------------------------------DESTACADOS
	@GetMapping("/destacados")
	public List<Evento> verDestacados() {
		return ieve.findDestacados();
	}

	// -------------------------------------------------FIND BY NOM
	@GetMapping("/buscarEventos/{nombre}")
	public List<Evento> verEventosByNom(@PathVariable("nombre") String nombre) {
		return ieve.findByName(nombre);
	}

	// -------------------------------------------------FIND BY ID
	@GetMapping("/verUno/{idEvento}")
	public Evento verUno(@PathVariable("idEvento") int idEvento) {
		return ieve.findById(idEvento);
	}

	// -------------------------------------------------PLAZAS
	@GetMapping("/plazasQuedan/{idEvento}")
	public Plazas verPlazasEvento(@PathVariable("idEvento") int idEvento) {

		Plazas plazas = new Plazas(ieve.findPlazas(idEvento));
		return plazas;
	}

	// ------------------------------------------------- ALTA
	@PostMapping("/alta")
	public String altaEvento(@RequestBody Evento evento) {
		return (ieve.insertOne(evento) == 1) ? "Evento dado de Alta" : "Error al dar de Alta";
	}

	// ------------------------------------------------- MODIFICAR
	@PutMapping("/modificar")
	public String updateEvento(@RequestBody Evento evento) {
		return (ieve.updateOne(evento) == 1) ? "Evento Modificado" : "Error al modificar el Evento";
	}

	// ------------------------------------------------- ELIMINAR
	@DeleteMapping("/eliminar/{idEvento}")
	public String delEvento(@PathVariable("idEvento") int idEvento) {
		return (ieve.deleteById(idEvento) == 1) ? "Evento Eliminado" : "Error al eliminar el Evento";
	}

}
