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
			<h2>Nuevo TEMA</h2>
			<fieldset>
				<form action="/admon/altaTema" method="post">
					<h3>Añadir Datos:</h3>
					<hr />

					<div class="campo">
						<label for="desc">Descripción: </label> <input type="text"
							name="descTema" id="desc" placeholder="añade descripcion..." />
					</div>
					<div class="campo">
						<label for="abre">Abreviatura: </label> <input type="text"
							name="abreviatura" id="abre" maxLength="4" placeholder="añade abreviatura..." />
					</div>

					<div class="campoSub">
						<input type="submit" value="Añadir" /> <input type="reset"
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
