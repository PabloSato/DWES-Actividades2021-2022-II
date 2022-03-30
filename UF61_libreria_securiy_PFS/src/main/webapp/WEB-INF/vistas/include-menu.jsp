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
	<div class="derecha">
		<h4>Menu</h4>
		<nav>
			<ul>
				<sec:authorize access="hasAuthority('ROL_ADMON')">
					<li><a href="/admon/verLibros">Ver Todos Libros</a></li>
					<li><a href="/admon/altaLibro">Nuevo Libro</a></li>
					<li><a href="/admon/altaTema">Nuevo Tema</a></li>
					<li><a href="/admon/pedidos">Ver Pedidos</a></li>
				</sec:authorize>
				<sec:authorize access="hasAuthority('ROL_CLIENTE')">
					<li><a href="/cliente/verLibros">Ver Todos Libros</a></li>
					<li><a href="/cliente/misPedidos">Ver Mis Pedidos</a></li>
					<li><a href="/cliente/misDatos">mis datos</a></li>
				</sec:authorize>
			</ul>
		</nav>
	</div>