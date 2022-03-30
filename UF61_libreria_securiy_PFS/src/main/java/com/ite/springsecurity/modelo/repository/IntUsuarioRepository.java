package com.ite.springsecurity.modelo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.springsecurity.modelo.entity.Usuario;

public interface IntUsuarioRepository extends JpaRepository<Usuario, String>{
	
	@Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u INNER JOIN u.perfiles up WHERE up.idPerfil = 2")
	List<Usuario> findCliente();
	
	@Query("SELECT u FROM Usuario u INNER JOIN u.perfiles up WHERE up.idPerfil = 1")
	List<Usuario> findAdmin();
	

	
	
}
