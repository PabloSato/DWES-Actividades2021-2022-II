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
<header>
		<div id="logo">
			<h2>LIBRERIA</h2>
		</div>
		<nav>
			<ul>
				<sec:authorize access="hasAuthority('ROL_ADMON')">
					<li><a href="/admon/inicio">home</a></li>
					<li><a href="/admon/usuarios">usuarios</a></li>
					<li><a href="/admon/perfiles">perfiles</a></li>
					<li><a href="/admon/clientes">clientes</a></li>
					<li><a href="/admon/verTemas">temas</a></li>
				</sec:authorize>
				<sec:authorize access="hasAuthority('ROL_CLIENTE')">
					<li><a href="/cliente/inicio">home</a></li>
					<li><a href="/cliente/verCarrito">ver carrito</a></li>
				</sec:authorize>
				<li><a href="/logout">salir</a></li>
			</ul>
		</nav>
	</header>