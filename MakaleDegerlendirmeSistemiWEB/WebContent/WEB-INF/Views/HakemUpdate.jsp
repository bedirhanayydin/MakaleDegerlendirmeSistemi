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

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/HakemList"
					class="nav-link">HAKEM LISTESI</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${hak != null}">
					<form action="<%=request.getContextPath()%>/HakemUp" method="post">
				</c:if>
				<c:if test="${hak == null}">
					<form action="<%=request.getContextPath()%>/HakemInsert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${hak != null}"> <!--EĞER SEÇİLMİŞ HAKEM VARSA NULL DEĞİLSE HAKEM GÜNCELLE YAZSIN EĞER HAKEM NULL SA YENİ HAKEM EKLE YAZIYOR VE 2 SAYFA YAPMAKTANSA 1 SAYFA YAPTIM DEĞERE GÖRE İŞLEM YAPTIRIYORUM-->
            			HAKEM GUNCELLE
            		</c:if>
						<c:if test="${hak == null}">
            			YENI HAKEM EKLE
            		</c:if>
					</h2>
				</caption>

				<c:if test="${hak != null}">
					<input type="hidden" name="id" value="<c:out value='${hak.id}' />" />
				</c:if>
				
				
				
				<fieldset class="form-group">
					<label>ADI</label> <input type="text"
						value="<c:out value='${hak.name}' />" class="form-control"
						name="name">
				</fieldset>

				<fieldset class="form-group">
					<label>SOYADI</label> <input type="text"
						value="<c:out value='${hak.lastname}' />" class="form-control"
						name="lastname">
				</fieldset>

				<fieldset class="form-group">
					<label>E-MAIL</label> <input type="text"
						value="<c:out value='${hak.email}' />" class="form-control"
						name="email">
				</fieldset>
				
				<fieldset class="form-group">
					<label>ŞİFRE</label> <input type="password"
						value="<c:out value='${hak.password}' />" class="form-control"
						name="password">
				</fieldset>
				<fieldset class="form-group">
						  <select name="Kategori" class="form-control" id ="kategori">
						  <c:forEach var="kat" items="${katlist}">
						  <c:if test = "${kat.name == hak.kategori.name }">
						  <option value='${kat.id}' selected>${kat.name}</option>
						  </c:if>
						  <c:if test = "${kat.name != hak.kategori.name }">
						  <option value='${kat.id}' >${kat.name}</option>
						  </c:if>
 			 				</c:forEach>
 						 </select>
				</fieldset>
				<button type="submit" class="btn btn-success">Kaydet</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>