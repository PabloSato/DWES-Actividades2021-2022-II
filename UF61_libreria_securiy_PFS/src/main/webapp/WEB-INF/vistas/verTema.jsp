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
		<h2>libros por tema</h2>

		<div class="conten">
			<div class="centro">
				<h4>
					Tema: <span>${tema.descTema}</span>
				</h4>
				<c:choose>
					<c:when test="${size lt 1 }">
						<h5>
							<span>No hay Libros disponibles</span>
						</h5>
					</c:when>
					<c:otherwise>
						<jsp:include page="include-tablaLibros.jsp"></jsp:include>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</section>
</body>
</html>
