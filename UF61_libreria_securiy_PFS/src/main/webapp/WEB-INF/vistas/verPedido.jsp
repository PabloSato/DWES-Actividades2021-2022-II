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
		<sec:authorize access="hasAuthority('ROL_ADMON')">
			<h2>Pedidos</h2>
		</sec:authorize>
		<sec:authorize access="hasAuthority('ROL_CLIENTE')">
			<h2>Pedidos</h2>
		</sec:authorize>
		<div class="small centro">
			<table>
				<sec:authorize access="hasAuthority('ROL_ADMON')">
					<tr>
						<th>ID Pedido</th>
						<th>Usuario</th>
						<th>Fecha</th>
						<th>Ver Pedidos</th>
					</tr>
					<c:forEach var="ele" items="${listaPedidos}">
						<tr>
							<td>${ele.idPedido}</td>
							<td>${ele.usuario.username}</td>
							<td>${ele.fechaAlta}</td>
							<td><a href="/admon/detPed/${ele.idPedido}" class="eli">ver</a></td>
						</tr>
					</c:forEach>
				</sec:authorize>
				<sec:authorize access="hasAuthority('ROL_CLIENTE')">
					<tr>
						<th>ID Pedido</th>
						<th>Fecha</th>
						<th>Ver Pedidos</th>
					</tr>
					<c:forEach var="ele" items="${listaPedidos}">
						<tr>
							<td>${ele.idPedido}</td>
							<td>${ele.fechaAlta}</td>
							<td><a href="/cliente/detPed/${ele.idPedido}" class="eli">ver</a></td>
						</tr>
					</c:forEach>
				</sec:authorize>
			</table>
		</div>
	</section>
</body>
</html>
