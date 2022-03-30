package com.ite.springsecurity.modelo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.springsecurity.modelo.entity.Perfile;
import com.ite.springsecurity.modelo.entity.Usuario;
import com.ite.springsecurity.modelo.repository.IntUsuarioRepository;

@Service
public class UsuarioDaoImpl implements IntUsuarioDao {

	@Autowired
	private IntUsuarioRepository user;

	@Override
	public List<Usuario> findAll() {

		return user.findAll();
	}

	@Override
	public List<Usuario> findByPerfil(int idPerfil) {
		
		List<Usuario> usuLista = new ArrayList<Usuario>();
		
		for(Usuario ele: findAll()) {
			for(Perfile per: ele.getPerfiles()) {
				if(per.getIdPerfil() == idPerfil) {
					usuLista.add(ele);
				}
			}
		}
		
		return usuLista;
	}

	@Override
	public Usuario findByUsername(String username) {

		return user.findById(username).orElse(null);
	}

	@Override
	public Usuario findByEmail(String email) {
		
		return user.findByEmail(email);
	}

	@Override
	public int insertOne(Usuario usuario) {
		int filas = 0;
		try {
			user.save(usuario);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int deleteOne(String username) {
		int filas = 0;
		try {
			user.deleteById(username);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public List<Usuario> findCliente() {
		
		return user.findCliente();
	}

	@Override
	public List<Usuario> findAdmin() {
		
		return user.findAdmin();
	}

}
