package com.ite.springsecurity.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.springsecurity.modelo.entity.Pedido;

public interface IntPedidoRepository extends JpaRepository<Pedido, Integer>{
	
	@Query("SELECT p FROM Pedido p WHERE p.usuario.username = ?1")
	List<Pedido> findByUsuario(String username);

}
