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
		<h2>Pedidos</h2>
		<div class="centro">
			<table>
				<tr>
					<th>Cantidad de Libros</th>
					<th>Cantidad de Temas</th>
					<th>Gasto Total</th>
				</tr>
				<tr>
					<td>${libros} <span>libros</span></td>
					<td>${temas} <span>temas distintos</span></td>
					<td>${gasto} <span>€</span></td>

				</tr>
			</table>

			<sec:authorize access="hasAuthority('ROL_ADMON')">
				<div class="comprar">
					<a href="/admon/detUser/${username}">Volver</a>
				</div>
			</sec:authorize>
		</div>
	</section>
</body>
</html>
