package com.ite.springsecurity.modelo.service;

import java.util.List;

import com.ite.springsecurity.modelo.entity.Usuario;

public interface IntUsuarioDao {

	List<Usuario> findAll();

	List<Usuario> findCliente();

	List<Usuario> findAdmin();

	List<Usuario> findByPerfil(int idPerfil);

	Usuario findByUsername(String username);

	Usuario findByEmail(String email);

	int insertOne(Usuario usuario);

	int deleteOne(String username);

}
