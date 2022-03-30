package com.idex.cajero.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idex.cajero.modelo.beans.Cuenta;

public interface IntCuentaRepository extends JpaRepository<Cuenta, Integer> {
	
	
}
