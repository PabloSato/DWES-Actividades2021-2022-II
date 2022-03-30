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
<title>LOGIN</title>
</head>
<body>
	<header>
		<div id="logo">
			<h2>LIBRERIA</h2>
		</div>
		<nav>
			<ul>
				<li><a href="/inicio">home</a></li>
			</ul>
		</nav>
	</header>
	<section class="fondo_claro">
		<div class="formu">
			<h2>Login</h2>
			<fieldset>
				<form action="/login" method="post">
					<div class="campo">
						<label for="username">Username: </label> <input type="text"
							name="username" id="username" placeholder="introduce username..." />
					</div>
					<div class="campo">
						<label for="pass">Password: </label> <input type="text"
							name="password" id="pass" placeholder="****" />
					</div>

					<div class="campoSub">
						<input type="submit" value="Login" /> <input type="reset"
							value="Reset" />
					</div>
				</form>
			</fieldset>
			<!-- ********** COMPRA *************** -->
			<jsp:include page="include-compra.jsp"></jsp:include>
			<!-- ********** /COMPRA *************** -->
		</div>
	</section>
</body>
</html>