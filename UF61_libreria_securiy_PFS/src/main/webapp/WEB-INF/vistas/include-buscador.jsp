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
	<div class="buscador">
		<sec:authorize access="hasAuthority('ROL_CLIENTE')">
			<form action="/cliente/buscar" method="post" class="titulos">
				<label></label>
				<div id="filtro">
					<input type="text" name="filtro" placeholder="añade busqueda..."
						autofocus>
				</div>
				<input type="submit" value="Buscar">
			</form>
			<form action="/cliente/tema" method="post" class="temas">
				<label></label>
				<div id="filtro2">
					<select name="tema.idTema">
						<option value="0" selected>--</option>
						<c:forEach var="ele" items="${temas}">
							<option value="${ele.idTema}">${ele.descTema}</option>
						</c:forEach>
					</select>
				</div>
				<input type="submit" value="Buscar">
			</form>
		</sec:authorize>
		<sec:authorize access="hasAuthority('ROL_ADMIN')">
			<form action="/admon/buscar" method="post" class="titulos">
				<label></label>
				<div id="filtro">
					<input type="text" name="filtro" placeholder="añade busqueda..."
						autofocus>
				</div>
				<input type="submit" value="Buscar">
			</form>
			<form action="/admon/tema" method="post" class="temas">
				<label></label>
				<div id="filtro2">
					<select name="tema.idTema">
						<option disabled selected>--</option>
						<c:forEach var="ele" items="${temas}">
							<option value="${ele.idTema}">${ele.descTema}</option>
						</c:forEach>
					</select>
				</div>
				<input type="submit" value="Buscar">
			</form>
		</sec:authorize>
	</div>