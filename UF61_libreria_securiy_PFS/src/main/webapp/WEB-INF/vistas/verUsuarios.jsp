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
		<h2>Usuarios</h2>

		<div class="conten">
			<div class="centro">
				<h4>
					Administradores: <span>${sizeAdmin}</span>
				</h4>
				<c:choose>
					<c:when test="${sizeAdmin lt 1 }">
						<h5>
							<span>No hay usuarios con Perfil Administrador</span>
						</h5>
					</c:when>
					<c:otherwise>
						<table>
							<tr>
								<th>Username</th>
								<th>email</th>
								<th>Fecha Alta</th>
								<th>Ver</th>
								<th>Opciones</th>
							</tr>
							<c:forEach var="ele" items="${listaAdmin}">
								<tr>
									<td>
										<p>${ele.username}</p>
									</td>
									<td>
										<p>
											<span>${ele.email}</span>
										</p>
									</td>
									<td>
										<p>${ele.fechaAlta}</p>
									</td>
									<td><a href="/admon/detUser/${ele.username}" class="ver">ver</a></td>
									<td>...</td>
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
			<br>
			<div class="centro">
				<h4>
					Clientes: <span>${sizeClient}</span>
				</h4>
				<c:choose>
					<c:when test="${sizeClient lt 1 }">
						<h5>
							<span>No hay usuarios con Perfil Cliente</span>
						</h5>
					</c:when>
					<c:otherwise>
						<table>
							<tr>
								<th>Username</th>
								<th>email</th>
								<th>Fecha Alta</th>
								<th>Ver</th>
								<th>Opciones</th>
							</tr>
							<c:forEach var="ele" items="${listaCliente}">
								<tr>
									<td>
										<p>${ele.username}</p>
									</td>
									<td>
										<p>
											<span>${ele.email}</span>
										</p>
									</td>
									<td>
										<p>${ele.fechaAlta}</p>
									</td>
									<td><a href="/admon/detUser/${ele.username}" class="ver">ver</a></td>
									<td><a href="#" class="eli">eliminar</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose>

				<!-- ********** COMPRA *************** -->
				<jsp:include page="include-compra.jsp"></jsp:include>
				<!-- ********** /COMPRA *************** -->
			</div>
		</div>
	</section>
</body>
</html>