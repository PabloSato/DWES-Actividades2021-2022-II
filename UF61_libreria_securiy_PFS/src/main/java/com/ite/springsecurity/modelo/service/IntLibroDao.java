package com.ite.springsecurity.modelo.service;

import java.util.List;

import com.ite.springsecurity.modelo.entity.Libro;

public interface IntLibroDao {

	List<Libro> findAll();

	List<Libro> findByTema(int idTema);

	List<Libro> findByTitulo(String titulo);

	List<Libro> findNovedad();

	Libro findByIsbn(long isbn);

	int insertOne(Libro libro);

	int deleteOne(long isbn);

	int gastoTotalCliente(String username);

	int cantidadLibroCliente(String username);

	int cantidadTemasCliente(String username);

}
