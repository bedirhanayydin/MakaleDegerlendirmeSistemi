<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Makale listesi</title>
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
			<h3 class="text-center">Tüm Makale Listesi</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/HakemHome" class="btn btn-success">Anasayfaya don</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>DOSYA ADI</th>
						<th>ACIKLAMA</th>
						<th>EK OLARAK EKLENEN</th>
						<th>YUKLEME TARIHI</th>
						<th>HAKEME ILETILME TARIHI</th>
						<th></th>
						<th>HAKEM KABUL TARIHI</th>
						<th>RAPOR YUKLEME TARIHI</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="emp" items="${maktohaks}">
						<tr>
							<td><c:out value="${emp.mak.fileName}" /></td>
							<td><c:out value="${emp.mak.yazarBilgi}" /></td>
							<td><c:out value="${emp.mak.makaleBilgi}" /></td>
							<td><c:out value="${emp.mak.yuklenDate}" /></td>
							<td><c:out value="${emp.mak.hakemDate}" /></td>
							<td></td>
							<td><c:out value="${emp.kabulDate}" /></td>
							<td><c:out value="${emp.raporDate}" /></td>
							
							
							<td><a class="btn btn-success"  href="Hakem_MakDown?id=<c:out value='${emp.mak.ID}' />">Makale indir..</a></td>
							<c:if test="${emp.raporDate != null}">
							<td><a  class="btn btn-success"  href="Hakem_RapDown?id=<c:out value='${emp.id}' />">Rapor indir..</a></td>
							</c:if>	
							<c:if test="${emp.raporDate == null}">
							
							<td><button class="btn btn-success" onclick="RapUp(${emp.id})" style="width:auto;">Rapor Yukle..</button></td>
							</c:if>
							
						</tr>
					</c:forEach>
					<!-- class="fa fa-file-pdf-o w3-xxxlarge" -->
				</tbody>

			</table>
		</div>
	</div>


<!--  File Upload -->



<div id="id01" class="modal">
  
  <form class="modal-content animate" method="post" action="<%=request.getContextPath()%>/Hakem_RapUpload" enctype="multipart/form-data">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>

    </div>

    <div class="container">
    	 <label for="puan"><b>Makaleye Puan: </b></label>
    	 <input type="number" name="puan"  min="0" max="100" step="1" value="50">
    
        <input type="file" name="file" accept=".pdf" class="w3-bar-item  w3-black" /> <br />
       <input type="hidden" id="mak001" name="id" value="-1"/>
        <button type="submit" class="w3-bar-item w3-right w3-black"><i class="fa fa-upload "></i>YUKLE</button>
      
   
    </div>


  </form>
</div>

<!-- File Upload End -->

<script>

function  RapUp(event) {
	document.getElementById('id01').style.display='block';
	document.getElementById('mak001').value = event;
}

</script>


</body>
</html>