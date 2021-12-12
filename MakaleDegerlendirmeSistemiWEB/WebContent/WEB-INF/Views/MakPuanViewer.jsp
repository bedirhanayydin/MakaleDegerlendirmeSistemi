<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Makale Listesi</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	


<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
 
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 30%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 25px;
  top: 0;
  color: #000;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}

/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
  
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}
</style>


	

	
</head>
<body>

	
	<br>
  
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div>  <embed src="dosya/klasor.pdf" type="application/pdf" height="300px" width="100%"> -->

		<div class="container">
			<h3 class="text-center">Makale Listesi</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/AlanEditorHome" class="btn btn-success">Anasayfaya don</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
					
						<th>YAZAR ADI</th>
						<th>YAZAR SOYADI</th>
						<th>YAZAR MAIL</th>
						<th></th>
						<th>DOSYA ADI</th>
						<th>MAKALE BILGISI</th>
						<th>YUKLEME TARIHI</th>
						<th>MAKALE EDITORDE (?)</th>
						<th>MAKALE ALAN EDITORUNEDE (?)</th>
						<th>MAKALE HAKEMLERDE (?)</th>
						<th>PUAN (%100)</th>
						
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="emp" items="${maklist}">
						<tr>
							<td><c:out value="${emp.yazar.name}" /></td>
							<td><c:out value="${emp.yazar.lastname}" /></td>
							<td><c:out value="${emp.yazar.email}" /></td>
							<td></td>
							<td><c:out value="${emp.fileName}" /></td>
							<td><c:out value="${emp.makaleBilgi}" /></td>
							<td><c:out value="${emp.yuklenDate}" /></td>
							<td><c:out value="${emp.editorDate}" /></td>
							<td><c:out value="${emp.aEditorDate}" /></td>
							<td><c:out value="${emp.hakemDate}" /></td>
								<td><c:out value="${emp.puan}" /></td>
								
							<c:if test="${emp.revizyon == 1 }">
							<td><a class="btn btn-success"  href="#" style="width:auto; background-color: #b02310">Yazara Revizyon Isteminde Bulunulmus</a></td>
							</c:if>
								
							<td><a class="btn btn-success"  href="MakViewer?id=<c:out value='${emp.ID}' />">Makele indir..</a></td>
							
							<c:if test="${ctrl == -1 }">
							<td>
							<form action="<%=request.getContextPath()%>/AlanEditor_MakaleDurum?id=${emp.ID}" method="post">
							<button type="submit" class="btn btn-success"  style="width:auto;">Hakem Incelemeleri</button>
							</form>
							</td>
							</c:if>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
	

	
	
	
</body>
</html>