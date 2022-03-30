package com.ite.pablofernandezsato.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.pablofernandezsato.modelo.entitysbeans.Usuario;
import com.ite.pablofernandezsato.modelo.repository.IntUsuarioRepository;

@Service
public class UsuarioDaoImpl implements IntUsuarioDao {

	@Autowired
	private IntUsuarioRepository iuser;

	@Override
	public List<Usuario> findAll() {

		return iuser.findAll();
	}

	@Override
	public Usuario findById(int idUsuario) {

		return iuser.findById(idUsuario).orElse(null);
	}

	@Override
	public Usuario findByLogin(String username, String password) {
		
		return iuser.findByLogin(username, password);
	}
	@Override
	public int insertOne(Usuario usuario) {
		if (findById(usuario.getIdUsuario()) == null) {
			iuser.save(usuario);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int updateOne(Usuario usuario) {
		if (findById(usuario.getIdUsuario()) != null) {
			iuser.save(usuario);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteOne(int idUsuario) {
		if (findById(idUsuario) != null) {
			iuser.deleteById(idUsuario);
			return 1;
		} else {
			return 0;
		}
	}

}
