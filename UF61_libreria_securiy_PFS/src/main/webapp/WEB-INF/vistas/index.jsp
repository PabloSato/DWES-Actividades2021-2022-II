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
		<h2>libros</h2>
		<!-- ********** BUSCADOR *************** -->
		<jsp:include page="include-buscador.jsp"></jsp:include>
		<!-- ********** /BUSCADOR *************** -->
		<div class="contenido">
			<div class="centro">
				<h4>Novedades</h4>
				<div class="comprar">
					<p class="msg">
						<span>${requestScope.msg}</span>
					</p>
				</div>
				<c:choose>
					<c:when test="${size lt 1 }">
						<h5>
							<span>No hay Libros disponibles</span>
						</h5>
					</c:when>
					<c:otherwise>
						<!-- ********** LIBROS *************** -->
						<jsp:include page="include-tablaLibros.jsp"></jsp:include>
						<!-- ********** /LIBROS *************** -->
					</c:otherwise>
				</c:choose>
			</div>
			<!-- ********** MENU *************** -->
			<jsp:include page="include-menu.jsp"></jsp:include>
			<!-- ********** /MENU *************** -->
		</div>
	</section>
</body>
</html>
