package com.idex.cajero.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idex.cajero.modelo.beans.Movimiento;

public interface IntMovimientoRepository extends JpaRepository<Movimiento, Integer>{
	
	@Query("SELECT m FROM Movimiento m WHERE m.cuenta.idCuenta = ?1 ORDER BY m.fecha DESC")
	public List<Movimiento> findByCuenta(int idCuenta);

}
