package com.ite.pablofernandezsato.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.pablofernandezsato.modelo.entitysbeans.Usuario;

public interface IntUsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Query("SELECT u FROM Usuario u WHERE u.username = ?1 AND u.password = ?2")
	public Usuario findByLogin(String username, String password);

}
