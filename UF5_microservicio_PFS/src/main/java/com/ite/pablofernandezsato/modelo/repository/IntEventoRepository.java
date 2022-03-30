package com.ite.pablofernandezsato.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.pablofernandezsato.modelo.entitysbeans.Evento;

public interface IntEventoRepository extends JpaRepository<Evento, Integer>{

	@Query("SELECT e FROM Evento e WHERE e.destacado = 's'")
	public List<Evento> findDestacados();
	
	@Query("SELECT e FROM Evento e WHERE e.nombre LIKE %?1%")
	public List<Evento> findByNombre(String nombre);
	
	@Query("SELECT e FROM Evento e WHERE e.estado = 'activo'")
	public List<Evento> findActivos();
	

}
