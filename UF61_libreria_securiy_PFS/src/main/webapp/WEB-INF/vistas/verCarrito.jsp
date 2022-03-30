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
		<h2>Carrito</h2>
		<div class="centro">
			<table>
				<tr>
					<th>ISBN</th>
					<th>Titulo</th>
					<th>#</th>
					<th>Precio</th>
				</tr>
				<c:forEach var="ele" items="${carrito}">
					<tr>
						<td>${ele.isbn}</td>
						<td>${ele.titulo}</td>
						<td><a href="/cliente/delCarrito/${ele.isbn}" class="eli">eliminar</a></td>
						<td>${ele.precioUnitario}<span>€</span></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3"></td>
					<td class="black">Total: <span>${total}</span> €
					</td>
				</tr>
			</table>
			<form action="/cliente/comprar" method="post"
				enctype="multipart/form-data">
				<div class="comprar">
					<button>Comprar</button>
				</div>
				<!-- ********** COMPRA *************** -->
				<jsp:include page="include-compra.jsp"></jsp:include>
				<!-- ********** /COMPRA *************** -->
			</form>
		</div>
	</section>
</body>
</html>
