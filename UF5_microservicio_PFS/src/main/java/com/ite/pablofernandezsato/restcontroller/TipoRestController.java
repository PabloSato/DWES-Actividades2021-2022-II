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

import com.ite.pablofernandezsato.modelo.dao.IntTipoDao;
import com.ite.pablofernandezsato.modelo.entitysbeans.Tipo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/tipos")
public class TipoRestController {
	
	@Autowired
	private IntTipoDao itipo;

	// ------------------------------------------------- ALTA
	@GetMapping("/todos")
	public List<Tipo> findAll(){
		return itipo.findAll();
	}
	// ------------------------------------------------- FINDBYID
	@GetMapping("/verUno/{idTipo}")
	public Tipo findById(@PathVariable("idTipo") int idTipo) {
		return itipo.findById(idTipo);
	}
	// ------------------------------------------------- ALTA
	@PostMapping("/alta")
	public String altaTipo(@RequestBody Tipo tipo) {
		return (itipo.inserOne(tipo) == 1) ? "Tipo dado de Alta": "Error en el Alta";
	}
	// ------------------------------------------------- UPDATE
	@PutMapping("/modificar")
	public String updateTipo(@RequestBody Tipo tipo) {
		return (itipo.updateOne(tipo) == 1) ? "Tipo modificado": "Error al modificar";
	}

	// ------------------------------------------------- ELIMINAR
	@DeleteMapping("/eliminar/{idTipo}")
	public String delTipo(@PathVariable("idTipo") int idTipo) {
		return (itipo.deleteById(idTipo) == 1) ? "Tipo Eliminado": "Error al Eliminar";
	}

}
