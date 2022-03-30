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
	<!---------------------------------------------------------------------------CABECERA-->
	<header>
		<div id="logo">
			<h2>LIBRERIA</h2>
		</div>
		<nav>
			<ul>
				<li><a href="/cliente/registro">Registro</a></li>
			</ul>
		</nav>
	</header>
	<!------------------------------------------------------------------------PORTADA-->
	<section id="home" class="portada" style="background-image: /img/fondo1.jpeg">
		<h2>
			<a href="/login">ENTRAR</a>
		</h2>
	</section>
</body>
</html>
