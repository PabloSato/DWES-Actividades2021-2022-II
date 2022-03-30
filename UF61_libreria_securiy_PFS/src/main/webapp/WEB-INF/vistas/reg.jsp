<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
<title>REGISTRO</title>
</head>
<body>
	<header>
		<div id="logo">
			<h2>LIBRERIA</h2>
		</div>
		<nav>
			<ul>
				<li><a href="/inicio">home</a></li>
			</ul>
		</nav>
	</header>
	<section class="fondo_claro">
		<div class="formu">
			<h2>Registro</h2>
			<fieldset>
				<form action="/cliente/registro" method="post">
					<h3>Añadir Datos:</h3>
					<hr />
					<div class="campo">
						<label for="nombre">Nombre: </label> <input type="text"
							name="nombre" id="nombre" placeholder="añade tu nombre..." />
					</div>
					<div class="campo">
						<label for="apellidos">Apellidos: </label> <input type="text"
							name="apellido" id="apellidos"
							placeholder="añade tu apellidos..." />
					</div>
					<div class="campo">
						<label for="direccion">Dirección: </label> <input type="text"
							name="direccion" id="direccion"
							placeholder="añade tu direccion..." />
					</div>
					<div class="campo">
						<label for="email">E-mail: </label> <input type="text"
							name="email" id="email" placeholder="añade tu email..." />
					</div>
					<hr />
					<div class="campo">
						<label for="username">Username: </label> <input type="text"
							name="username" id="username" placeholder="añade username..." />
					</div>
					<div class="campo">
						<label for="pass">Password: </label> <input type="text"
							name="password" id="pass" placeholder="****" />
					</div>
					<div class="campoSub">
						<input type="submit" value="Registrar" /> <input type="reset"
							value="Reset" />
					</div>
				</form>
			</fieldset>
			<!-- ********** COMPRA *************** -->
			<jsp:include page="include-compra.jsp"></jsp:include>
			<!-- ********** /COMPRA *************** -->
		</div>
	</section>
</body>
</html>