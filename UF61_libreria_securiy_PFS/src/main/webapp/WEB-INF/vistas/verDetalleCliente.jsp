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
				<img src="<c:url value="/img/icon.jpeg"/>" alt="${user.username}" />
			</div>
			<div class="data">
				<h2>Datos Cliente</h2>
				<div class="info">
					<h3>${user.username}</h3>
					<p>
						nombre: <span>${user.nombre}</span>
					</p>
					<p>
						apellidos: <span>${user.apellido}</span>
					</p>
					<p>
						email: <span>${user.email}</span>
					</p>
					<p>
						fecha alta: <span>${user.fechaAlta}</span>
					</p>
					<p>
						Dirección: <br /> <span>${user.direccion}</span>
					</p>
				</div>
				<sec:authorize access="hasAuthority('ROL_CLIENTE')">
					<div class="comprar">
						<a href="/cliente/editDatos">Editar</a>
					</div>
				</sec:authorize>
				<sec:authorize access="hasAuthority('ROL_ADMON')">
					<div class="comprar">
						<a href="/admon/verPedidos/${user.username}">Pedidos</a>
					</div>
				</sec:authorize>
				<!-- ********** COMPRA *************** -->
				<jsp:include page="include-compra.jsp"></jsp:include>
				<!-- ********** /COMPRA *************** -->
			</div>
		</div>
	</section>
</body>
</html>