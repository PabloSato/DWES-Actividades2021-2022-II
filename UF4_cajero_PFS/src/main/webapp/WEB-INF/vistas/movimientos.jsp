<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>VER MOVIMIENTOS</title>
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
			<h2>Cuenta: ${cuenta.idCuenta}</h2>
			<br>
			<h4>Saldo: ${cuenta.saldo}</h4>
			<div class="tablas">
				<div class="tabla_1">
					<table>
						<tr>
							<th>Cantidad</th>
							<th>Fecha</th>
							<th>Tipo</th>
						</tr>
						<tr>
							<c:forEach var="ele" items="${movimientos}">
								<tr>
									<td>${ele.cantidad}</td>
									<td>${ele.fecha }</td>
									<c:choose>
										<c:when test="${ele.operacion eq 'ingreso' }">
											<td class="green">${ele.operacion}</td>
										</c:when>
										<c:when test="${ele.operacion eq 'extracto'}">
											<td class="eli">${ele.operacion}</td>
										</c:when>
										<c:when test="${ele.operacion eq 'transferencia saliente' }">
											<td class="med">${ele.operacion}</td>
										</c:when>
										<c:when test="${ele.operacion eq 'transferencia entrante' }">
											<td class="med1">${ele.operacion}</td>
										</c:when>
									</c:choose>

								</tr>
							</c:forEach>
						</tr>
					</table>
				</div>
			</div>
		</section>
	</div>
</body>
</html>
