package com.ite.pablofernandezsato.modelo.dao;

import java.util.List;

import com.ite.pablofernandezsato.modelo.entitysbeans.Usuario;

public interface IntUsuarioDao {

	List<Usuario> findAll();
	Usuario findById(int idUsuario);
	Usuario findByLogin(String username, String password);
	int insertOne(Usuario usuario);
	int updateOne(Usuario usuario);
	int deleteOne(int idUsuario);
}
