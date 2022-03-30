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
		<h2>Clientes</h2>
		<div class="conten">
			<div class="centro">
				<h4></h4>
				<c:choose>
					<c:when test="${size lt 1 }">
						<h5>
							<span>No hay Temas disponibles</span>
						</h5>
					</c:when>
					<c:otherwise>
						<table>
							<tr>
								<th>Username</th>
								<th>email</th>
								<th>Dirección</th>
								<th>Fecha de Alta</th>
								<th>Ver</th>
							</tr>
							<c:forEach var="ele" items="${listaClientes}">
								<tr>
									<td>
										<h5>${ele.username}</h5>
									</td>
									<td>
										<p>
											<span>${ele.email}</span>
										</p>
									</td>
									<td>
										<p>
											${ele.direccion}
										</p>
									</td>
									<td>
										<p>
											${ele.fechaAlta}
										</p>
									</td>
									<td><a href="/admon/detUser/${ele.username}" class="ver">ver</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!-- ********** COMPRA *************** -->
		<jsp:include page="include-compra.jsp"></jsp:include>
		<!-- ********** /COMPRA *************** -->
	</section>
</body>
</html>