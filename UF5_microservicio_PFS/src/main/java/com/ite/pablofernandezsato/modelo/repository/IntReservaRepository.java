package com.ite.pablofernandezsato.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.pablofernandezsato.modelo.entitysbeans.Reserva;

public interface IntReservaRepository extends JpaRepository<Reserva, Integer>{
	
	@Query("SELECT r FROM Reserva r WHERE r.evento.idEvento = ?1")
	public List<Reserva> findByEvento(int idEvento);
	
	@Query("SELECT r FROM Reserva r WHERE r.usuario.idUsuario = ?1")
	public List<Reserva> findByUsuario(int idUsuario);

}
