package com.ite.springsecurity.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.springsecurity.modelo.entity.Libro;
import com.ite.springsecurity.modelo.repository.IntLibroRepository;

@Service
public class LibroDaoImpl implements IntLibroDao {

	@Autowired
	private IntLibroRepository ilib;

	@Override
	public List<Libro> findAll() {

		return ilib.findAll();
	}

	@Override
	public List<Libro> findByTema(int idTema) {

		return ilib.findByTema(idTema);
	}

	@Override
	public List<Libro> findByTitulo(String titulo) {
		
		return ilib.findByTitulo(titulo);
	}

	@Override
	public List<Libro> findNovedad() {

		return ilib.findByNovedad();
	}

	@Override
	public Libro findByIsbn(long isbn) {

		return ilib.findById(isbn).orElse(null);
	}

	@Override
	public int insertOne(Libro libro) {
		int filas = 0;
		try {
			ilib.save(libro);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int deleteOne(long isbn) {
		int filas = 0;
		try {
			ilib.deleteById(isbn);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int gastoTotalCliente(String username) {
		
		return ilib.gastoTotalCliente(username);
	}

	@Override
	public int cantidadLibroCliente(String username) {
		
		return ilib.cantidadLibrosCliente(username);
	}

	@Override
	public int cantidadTemasCliente(String username) {
		
		return ilib.cantidadTemasCliente(username);
	}

}
