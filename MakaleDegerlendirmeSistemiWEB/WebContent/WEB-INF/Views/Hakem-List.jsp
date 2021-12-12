<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Hakem Yonetim Sayfası</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	
		<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="#" class="navbar-brand"> Hakem Yonetim Sayfası </a>
			</div>

			
		</nav>
	</header>
	<br>
  
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div>  <embed src="dosya/klasor.pdf" type="application/pdf" height="300px" width="100%"> -->

		<div class="container">
			<h3 class="text-center">Hakem Listesi</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/AlanEditorHome" class="btn btn-success">Anasayfaya Dön</a>
					<a href="<%=request.getContextPath()%>/HakemUp?id=-1" class="btn btn-success">Hakem Ekle</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ADI</th>
						<th>SOYADI</th>
						<th>EMAIL ADRES</th>
						<th>SIFRE</th>
						<th>KATEGORI</th>
						
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="hak" items="${Haklist}">
						<tr>
							<td><c:out value="${hak.name}" /></td>
							<td><c:out value="${hak.lastname}" /></td>
							<td><c:out value="${hak.email}" /></td>
							<td><c:out value="*********" /></td>
							<td><c:out value="${hak.kategori.name}" /></td>
						
							<td><a  href="HakemDelete?id=<c:out value='${hak.id}' />">SIL</a></td>
							<td><a  href="HakemUp?id=<c:out value='${hak.id}' />">GUNCELLE</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>