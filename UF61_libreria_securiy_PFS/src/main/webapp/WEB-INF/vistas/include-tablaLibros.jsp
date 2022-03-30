<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
<title>LIBRERIA</title>
</head>
<body>
	<table>
		<tr>
			<th class="cover">Portada</th>
			<th>Título</th>
			<th>Autor</th>
			<th>Precio</th>
			<th>Ver</th>
			<sec:authorize access="hasAuthority('ROL_CLIENTE')">
				<th>#</th>
			</sec:authorize>
			<sec:authorize access="hasAuthority('ROL_ADMON')">
				<th>Opciones</th>
			</sec:authorize>
		</tr>
		<c:forEach var="ele" items="${listaLibros}">
			<tr>
				<td class="cover">
					<div class="img">
						<img src="<c:url value="/img/books/${ele.imagen}"/>"
							alt="${ele.titulo}">
					</div>
				</td>
				<td>
					<h5>${ele.titulo}</h5>
				</td>
				<td>
					<p>
						<span>${ele.autor}</span>
					</p>
				</td>
				<td>
					<p>
						<span class="money">${ele.precioUnitario}</span> €
					</p>
				</td>
				<sec:authorize access="hasAuthority('ROL_ADMON')">
					<td><a href="/admon/ver/${ele.isbn}" class="ver">ver</a></td>
					<td><a href="/admon/editar/${ele.isbn}" class="edit">edit</a>/<a
						href="/admon/eliminar/${ele.isbn}" class="eli">eli</a></td>
				</sec:authorize>
				<sec:authorize access="hasAuthority('ROL_CLIENTE')">
					<td><a href="/cliente/ver/${ele.isbn}" class="ver">ver</a></td>
					<td><a href="/cliente/addCarrito/${ele.isbn}" class="edit">add</a></td>
				</sec:authorize>

			</tr>
		</c:forEach>
	</table>