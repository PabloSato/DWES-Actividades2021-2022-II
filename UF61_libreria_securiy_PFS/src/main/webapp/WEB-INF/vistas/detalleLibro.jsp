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
	<!-- ********** HEADER *************** -->
	<jsp:include page="include-header.jsp"></jsp:include>
	<!-- ********** HEADER *************** -->
	<section class="fondo_claro">
		<!-- ********** USER *************** -->
		<jsp:include page="include-user.jsp"></jsp:include>
		<!-- ********** /USER *************** -->
		<div class="detalle">
			<div class="cubierta">
				<img src="<c:url value="/img/books/${libro.imagen}"/>"
					alt="${libro.titulo}" />
			</div>
			<div class="data">
				<h2>${libro.titulo}</h2>
				<div class="info">
					<h3>${libro.autor}</h3>
					<p>
						isbn: <span>${libro.isbn}</span>
					</p>
					<p>
						precio: <span>${libro.precioUnitario}</span> €
					</p>
					<p>
						pág: <span>${libro.paginas}</span>
					</p>
					<p>
						tema: <span>${libro.tema.descTema}</span>
					</p>
				</div>
				<div class="comprar">
					<sec:authorize access="hasAuthority('ROL_CLIENTE')">

						<a href="/cliente/addCarrito/${libro.isbn}">Añadir</a>

					</sec:authorize>
					<sec:authorize access="hasAuthority('ROL_ADMON')">

						<a href="/admon/editar/${libro.isbn}">Editar</a>

					</sec:authorize>
				</div>
			</div>
		</div>
	</section>
</body>
</html>