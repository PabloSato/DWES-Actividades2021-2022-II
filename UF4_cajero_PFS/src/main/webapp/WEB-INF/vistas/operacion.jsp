<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OPERACIÓN - ${orden }</title>
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
			<fieldset>
				<form action="/cuenta/operacion/${orden}" method="post">
					<div class="box">
						<label>Introduce Cantidad:</label> <input type="number"
							name="cantidad" placeholder="000000" />
					</div>

					<br />
					<div class="btnsForm">
						<c:choose>
							<c:when test="${orden eq 'ingreso'}">
								<input type="submit" value="Ingresar" />
								<input type="reset" value="Reset" />
							</c:when>
							<c:when test="${orden eq 'extracto'}">
								<input type="submit" value="Extraer" />
								<input type="reset" value="Reset" />
							</c:when>
						</c:choose>

					</div>
				</form>
			</fieldset>
			<div class="msj">
				<h3>${requestScope.mensaje}</h3>
			</div>

		</section>
	</div>
</body>
</html>
