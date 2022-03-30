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
			<h2>Editar Libro</h2>
			<fieldset>
				<form action="/admon/editar/${libro.isbn}" method="post"
					enctype="multipart/form-data">
					<h3>
						Editar Datos: <span>${libro.isbn}</span>
					</h3>
					<hr />

					<div class="campo">
						<label for="titulo">Título: </label> <input type="text"
							name="titulo" id="titulo" value="${libro.titulo}" />
					</div>
					<div class="campo">
						<label for="autor">Autor: </label> <input type="text" name="autor"
							id="autor" value="${libro.autor}" />
					</div>
					<div class="campo">
						<label for="precio">Precio: </label> <input type="number"
							name="precioUnitario" id="precio" value="${libro.precioUnitario}" />
					</div>
					<div class="campo">
						<label for="paginas">Páginas: </label> <input type="number"
							name="paginas" id="paginas" value="${libro.paginas}" />
					</div>
					<div class="campo">
						<label for="imagen">Portada: <cite>no más de 1MB</cite></label> <input
							type="file" name="file" id="imagen">
					</div>
					<div class="campo">
						<label for="novedad">Novedad: </label>
						<div class="radio">
							<c:choose>
								<c:when test="${libro.novedad eq 's' }">
									<input type="radio" name="novedad" value="s" checked />Si
               		<input type="radio" name="novedad" value="s" />No
              	</c:when>
								<c:otherwise>
									<input type="radio" name="novedad" value="s" />Si
                	<input type="radio" name="novedad" value="n" checked />No
              	</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="campo">
						<label for="tema">Tema: </label> <select name="tema.idTema"
							id="tema">
							<c:forEach var="ele" items="${listaTema}">
								<c:choose>
									<c:when test="${(libro.tema.idTema) == (ele.idTema) }">
										<option selected value="${ele.idTema}">${ele.descTema}</option>
									</c:when>
									<c:otherwise>
										<option value="${ele.idTema}">${ele.descTema}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>

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