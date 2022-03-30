<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Gestión de Eventos</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet"> 
</head>
<body>
	<div class="wrap">

		<header>
			<nav>
				<ul>
					<li><a href="/cuenta/operacion/ingreso">Ingresar</a></li>
					<li><a href="/cuenta/operacion/extracto">Extraer</a></li>
					<li><a href="/cuenta/todos">Ver movimientos</a></li>
					<li><a href="/cuenta/transfer">Transferencia</a></li>
					<li><a href="/cuenta/salir">Salir</a></li>
				</ul>
			</nav>
		</header>
		<section>
			<div class="user">
				<h6>Cuenta nº: ${cuenta.idCuenta}</h6>
			</div>
		</section>
	</div>
</body>
</html>
