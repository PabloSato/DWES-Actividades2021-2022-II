<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ACCESO - LOGIN</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
</head>
<body>
	<div class="wrap">
		<section>
			<h2>Accede</h2>
			<fieldset>
				<form action="/cuenta/login" method="post">
					<div class="box">
						<label>Introduce Cuenta: </label> <input type="number" name="idCuenta"
							placeholder="Número de cuenta"/>
					</div>
					
					<br />
					<div class="btnsForm">
						<input type="submit" value="Login" /> <input type="reset"
							value="Reset" />
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
