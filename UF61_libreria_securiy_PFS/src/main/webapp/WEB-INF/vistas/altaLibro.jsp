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
			<h2>Nuevo Libro</h2>
			<fieldset>
				<form action="/admon/altaLibro" method="post"
					enctype="multipart/form-data">
					<h3>Añadir Datos:</h3>
					<hr />
					<div class="campo">
						<label for="isbn">ISBN: </label> <input type="number" name="isbn"
							id="isbn" placeholder="añade isbn..." />
					</div>
					<div class="campo">
						<label for="titulo">Título: </label> <input type="text"
							name="titulo" id="titulo" placeholder="añade titulo..." />
					</div>
					<div class="campo">
						<label for="autor">Autor: </label> <input type="text" name="autor"
							id="autor" placeholder="añade autor..." />
					</div>
					<div class="campo">
						<label for="precio">Precio: </label> <input type="number"
							name="precioUnitario" id="precio" placeholder="añade precio..." />
					</div>
					<div class="campo">
						<label for="paginas">Páginas: </label> <input type="number"
							name="paginas" id="paginas" placeholder="añade páginas..." />
					</div>
					<div class="campo">
						<label for="imagen">Portada: <cite>no más de 1MB</cite></label> <input
							type="file" name="file" id="imagen">
					</div>
					<div class="campo">
						<label for="novedad">Novedad: </label>
						<div class="radio">
							<input type="radio" name="novedad" value="s" />Si <input
								type="radio" name="novedad" value="n" />No
						</div>
					</div>
					<div class="campo">
						<label for="tema">Tema: </label> <select name="tema.idTema"
							id="tema">
							<option selected disabled>--</option>
							<c:forEach var="ele" items="${listaTema}">
								<option value="${ele.idTema}">${ele.descTema}</option>
							</c:forEach>
						</select>
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