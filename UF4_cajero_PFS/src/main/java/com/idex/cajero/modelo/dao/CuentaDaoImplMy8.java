package com.idex.cajero.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idex.cajero.modelo.beans.Cuenta;
import com.idex.cajero.modelo.repository.IntCuentaRepository;

@Service
public class CuentaDaoImplMy8 implements IntCuentaDao{
	
	@Autowired
	private IntCuentaRepository icur;

	@Override
	public List<Cuenta> findAll() {
		
		return icur.findAll();
	}

	@Override
	public Cuenta findById(int idCuenta) {
		
		return icur.findById(idCuenta).orElse(null);
	}

	@Override
	public int insertOne(Cuenta cuenta) {
		int filas = 0;
		try {
			icur.save(cuenta);
			filas = 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int deleteOne(int idCuenta) {
		int filas = 0;
		try {
			icur.deleteById(idCuenta);
			filas = 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

}
