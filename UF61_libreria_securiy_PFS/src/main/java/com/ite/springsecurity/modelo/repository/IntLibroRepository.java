package com.ite.springsecurity.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.springsecurity.modelo.entity.Libro;

public interface IntLibroRepository extends JpaRepository<Libro, Long> {

	@Query("SELECT l FROM Libro l WHERE l.tema.idTema = ?1")
	List<Libro> findByTema(int idTema);

	@Query("SELECT l FROM Libro l WHERE l.novedad = 's'")
	List<Libro> findByNovedad();

	@Query("SELECT l FROM Libro l WHERE l.titulo LIKE %?1%")
	List<Libro> findByTitulo(String titulo);

	@Query("SELECT SUM(lp.precioVenta) FROM LineasPedido lp INNER JOIN Pedido p ON p.idPedido = lp.pedido.idPedido INNER JOIN Usuario u ON u.username = p.usuario.username WHERE u.username = ?1")
	int gastoTotalCliente(String username);

	@Query("SELECT COUNT(lp.libro) FROM LineasPedido lp INNER JOIN Pedido p ON p.idPedido = lp.pedido.idPedido INNER JOIN Usuario u ON u.username = p.usuario.username WHERE u.username = ?1")
	int cantidadLibrosCliente(String username);

	@Query("SELECT COUNT(DISTINCT t.idTema) FROM Pedido p INNER JOIN LineasPedido lp ON p.idPedido = lp.pedido.idPedido INNER JOIN Libro l ON l.isbn = lp.libro.isbn INNER JOIN Tema t ON l.tema.idTema = t.idTema WHERE p.usuario.username = ?1")
	int cantidadTemasCliente(String username);
}
