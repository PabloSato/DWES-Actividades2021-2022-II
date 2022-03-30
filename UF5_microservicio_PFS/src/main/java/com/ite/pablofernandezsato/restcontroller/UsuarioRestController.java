package com.ite.pablofernandezsato.restcontroller;

import java.util.ArrayList;
import java.util.Date;
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

import com.ite.pablofernandezsato.modelo.dao.IntPerfilDao;
import com.ite.pablofernandezsato.modelo.dao.IntUsuarioDao;
import com.ite.pablofernandezsato.modelo.entitysbeans.Perfile;
import com.ite.pablofernandezsato.modelo.entitysbeans.Usuario;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("rest/usuarios")
public class UsuarioRestController {

	@Autowired
	private IntUsuarioDao iuser;
	@Autowired
	private IntPerfilDao iper;

	// ------------------------------------------------- FINDALL
	@GetMapping("/todos")
	public List<Usuario> findAll() {
		return iuser.findAll();
	}

	// ------------------------------------------------- FINDBYID
	@GetMapping("/verUno/{idUsuario}")
	public Usuario findById(@PathVariable("idUsuario") int idUsuario) {
		return iuser.findById(idUsuario);
	}

	// ------------------------------------------------- FINDBYID
	@PostMapping("/login")
	public Usuario findByLogin(@RequestBody Usuario usuario) {
		return iuser.findByLogin(usuario.getUsername(), usuario.getPassword());
	}

	// ------------------------------------------------- ALTA
	@PostMapping("/alta")
	public String insertOne(@RequestBody Usuario usuario) {
		Perfile perfil = iper.findById(1);
		List<Perfile> lista = new ArrayList<Perfile>();
		lista.add(perfil);
		usuario.setPerfiles(lista);
		System.out.println(usuario);
		return (iuser.insertOne(usuario) == 1) ? "Usuario dado de Alta" : "Error en el Alta";
	}

	// ------------------------------------------------- MODIFICAR
	@PutMapping("/modificar")
	public String updateOne(@RequestBody Usuario usuario) {
		return (iuser.updateOne(usuario) == 1) ? "Usuario modiicado" : "Error al modificar";
	}

	// ------------------------------------------------- DELETE
	@DeleteMapping("/eliminar/{idUsuario}")
	public String deleteOne(@PathVariable("idUsuario") int idUsuario) {
		return (iuser.deleteOne(idUsuario) == 1) ? "Usuario eliminado" : "Error al eliminar";
	}

}
