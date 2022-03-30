package com.idex.cajero.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idex.cajero.modelo.beans.Movimiento;
import com.idex.cajero.modelo.repository.IntMovimientoRepository;

@Service
public class MovimientoDaoImplMy8 implements IntMoviemientoDao{
	
	@Autowired
	private IntMovimientoRepository imovr;

	@Override
	public List<Movimiento> findAll() {
		
		return imovr.findAll();
	}

	@Override
	public List<Movimiento> findByCuenta(int idCuenta) {
		
		return imovr.findByCuenta(idCuenta);
	}

	@Override
	public Movimiento findById(int idMovmiento) {
		
		return imovr.findById(idMovmiento).orElse(null);
	}

	@Override
	public int insertOne(Movimiento movimiento) {
		int filas = 0;
		try {
			imovr.save(movimiento);
			filas = 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int deleteOne(int idMovmiento) {
		int filas = 0;
		try {
			imovr.deleteById(idMovmiento);
			filas = 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

}
