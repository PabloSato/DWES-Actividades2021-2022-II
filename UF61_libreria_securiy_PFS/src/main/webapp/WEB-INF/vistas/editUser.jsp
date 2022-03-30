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
<title>LIBRERIA</title>
</head>
<body>
	<!-- ********** HEADER *************** -->
	<jsp:include page="include-header.jsp"></jsp:include>
	<!-- ********** HEADER *************** -->
	<section class="fondo_claro">
		<!-- ********** USER *************** -->
		<jsp:include page="include-user.jsp"></jsp:include>
		<!-- ********** /USER *************** -->
		<div class="formu">
			<h2>Editar Usuario</h2>
			<fieldset>
				<form action="/cliente/editDatos" method="post">
					<h3>Editar Datos:</h3>
					<hr />
					<div class="campo">
						<label for="nombre">Nombre: </label> <input type="text"
							name="nombre" id="nombre" value="${user.nombre}" />
					</div>
					<div class="campo">
						<label for="apellidos">Apellidos: </label> <input type="text"
							name="apellido" id="apellidos"
							 value="${user.apellido}" />
					</div>
					<div class="campo">
						<label for="direccion">Dirección: </label> <input type="text"
							name="direccion" id="direccion"
							 value="${user.direccion}" />
					</div>
					<div class="campo">
						<label for="email">E-mail: </label> <input type="text"
							name="email" id="email" value="${user.email}" />
					</div>
					<hr />
					<div class="campoSub">
						<input type="submit" value="Editar" /> <input type="reset"
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