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
		<h2>libros por tema</h2>

		<div class="conten">
			<div class="centro">
				<h4>
					Búsqueda: <span>${busqueda}</span>
				</h4>
				<c:choose>
					<c:when test="${size lt 1 }">
						<h5>
							<span>No hay Libros disponibles</span>
						</h5>
					</c:when>
					<c:otherwise>
						<table>
							<tr>
								<th class="cover">Portada</th>
								<th>Título</th>
								<th>Autor</th>
								<th>Precio</th>
								<th>Ver</th>
								<th>#</th>
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
									<td><a href="/cliente/ver/${ele.isbn}" class="ver">ver</a></td>
									<td><a href="#" class="edit">añadir</a></td>

								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</section>
</body>
</html>
