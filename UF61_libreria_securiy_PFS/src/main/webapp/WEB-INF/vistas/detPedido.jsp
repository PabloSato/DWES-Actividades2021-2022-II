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
		<h2>
			Pedido <span>${idPedido}</span>
		</h2>
		<div class="centro">
			<table>
				<tr>
					<th>Titulo</th>
					<th>Cantidad</th>
					<th>Precio</th>
					<th>Ver Libro</th>
				</tr>
				<sec:authorize access="hasAuthority('ROL_CLIENTE')">
				<c:forEach var="ele" items="${listaPedido}">
					<tr>
						<td>${ele.libro.titulo}</td>
						<td>${ele.cantidad}</td>
						<td>${ele.precioVenta}<span>€</span></td>
						<td><a href="/cliente/ver/${ele.libro.isbn}" class="eli">ver
								libro</a></td>
					</tr>
				</c:forEach>
				</sec:authorize>
				<sec:authorize access="hasAuthority('ROL_ADMON')">
				<c:forEach var="ele" items="${listaPedido}">
					<tr>
						<td>${ele.libro.titulo}</td>
						<td>${ele.cantidad}</td>
						<td>${ele.precioVenta}<span>€</span></td>
						<td><a href="/admon/ver/${ele.libro.isbn}" class="eli">ver
								libro</a></td>
					</tr>
				</c:forEach>
				</sec:authorize>
				<tr>
					<td colspan="3"></td>
					<td class="black">Total: <span>${total}</span> €
					</td>
				</tr>
			</table>

			<sec:authorize access="hasAuthority('ROL_ADMON')">
				<div class="comprar">
					<p class="add">
						<a href="/admon/pedidos">Volver</a>
					</p>
				</div>
			</sec:authorize>
		</div>
	</section>
</body>
</html>
