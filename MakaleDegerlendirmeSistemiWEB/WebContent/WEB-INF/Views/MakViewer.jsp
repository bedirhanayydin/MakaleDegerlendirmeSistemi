
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=iso-8859-9"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>Makale Bilgisi</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


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
  margin: 8px 0;
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
/* main window size */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 50%; /* Could be more or less, depending on screen size */
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


table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}






input[type=submit] {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 16px 32px;
  text-decoration: none;
  margin: 4px 2px;
  cursor: pointer;
}



</style>



<body>

<div class="w3-container">
	<br><br>
	<br><br>
  <h2 class="w3-container w3-blue" style=" width:50%; margin-left:25%; ">Yazar Adı Soyadı = ${mak.yazar.name}  ${mak.yazar.lastname}</h2>
 

  <div class="w3-card-4" style=" width:50%; margin-left:25%; ">
    <header class="w3-container w3-blue">
      <h1>Yazar Bilgisi = ${mak.yazarBilgi}</h1>
      <h5 class="w3-container w3-blue w3-right">${mak.yuklenDate}</h5>
    </header>

    <div class="w3-container">
      <p>${mak.makaleBilgi}.</p>
    </div>

    <footer class="w3-container w3-blue">
     
      <c:if test="${GHid == -1 }">   <!-- EĞER HAKEMSE BU İF BLOGUNU GÖRMEZ ALAN EDİTÖRÜ BURAYI GÖRÜR -->
       <h5><a href="MakViewer?id=${mak.ID}">MAKALEYİ GÖRÜNTÜLE</a></h5>
 	<c:if test="${mak.revizyon == 1 }">
 	<h4 class="w3-container w3-blue w3-right">Revizyon Istegi Gonderildi..</h4>
 	</c:if>
 
      <c:if test="${SecHakemList.size() <3}" >
      
    <button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">HAKEM SEC</button>
    </c:if>
     <c:if test="${SecHakemList.size() > 0}">
      <button onclick="document.getElementById('id02').style.display='block'" style="width:auto;">SECILMIS HAKEMLER</button>
      
      <c:if test="${mak.revizyon == 0 }">
      <form action="<%=request.getContextPath()%>/MakRevizyon?id=${mak.ID}" method="post">
       <button type="submit" style="width:auto;  background-color: #c4341d">REVIZYON ISTEGI GONDER</button>
      </form>
      </c:if>
      
      </c:if>
      </c:if>
      <c:if test="${GHid != -1 }">  <!--/*HAKEM SADECE BURAYI GÖRÜR  -->
        <form action="<%=request.getContextPath()%>/Hakem_MakKabRed?id=${mak.ID}" method="post">
       <h5><a href="Hakem_MakDown?id=${mak.ID}">MAKALEYİ İNDİR</a></h5>
     <!--   <button  style="width:auto;">KABUL ET</button>
      <button  style="width:auto; background-color: Red">REDDET</button>-->
       <input type="hidden"  name="HakID" value="${GHid}" />
      <input type="submit" value="KABUL_ET" name="cv" >
        <input type="submit" style="background-color: #b53c21" value="REDDET" name="cv">
        
	</form>
      </c:if>
      
    </footer>
  </div>
</div>


<!-- pop-up sechakem -->




<div id="id02" class="modal">
  
  <form class="modal-content animate" action="#" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
    <br>
    </div>

    <div class="container">

   
      
      <div style="overflow-x:auto;">
  <table>
  	<thead>
    <tr>
      
      <th>ADI</th>
      <th>SOYADI</th>
      <th>EMAIL</th>
   
    </tr>
    	</thead>
    
    <tbody>
  
    <c:forEach var="shak" items="${SecHakemList}">
    <tr>
      <td><c:out value="${shak.name}" /></td>
      <td><c:out value="${shak.lastname}" /></td>
      <td><c:out value="${shak.email}" /></td>
      
    </tr>
    
    </c:forEach>
	</tbody>

  </table>
	</div>
      
    </div>
 

    <div class="container" style="background-color:#f1f1f1">

    </div>
  </form>
</div>





  
<!-- pop-up sechakem end -->

<!-- pop-up -->

<div id="id01" class="modal">
  
  <form class="modal-content animate" action="<%=request.getContextPath()%>/MakHakemSec?id=${mak.ID}" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
    <!--    <img src="images/businessman.png" alt="Avatar" class="avatar">-->
    <br>
    </div>

    <div class="container">    
      <div style="overflow-x:auto;">
  <table>
  	<thead>
    <tr>
      <th>SEC</th>
      <th>ADI</th>
      <th>SOYADI</th>
      <th>EMAIL</th>
      <th>KATEGORI</th>
    </tr>
    	</thead>
    
    <tbody>
  
    <c:forEach var="hak" items="${HakemList}">
    <c:if test="${SecHakemList.size() <3 && SecHakemList.size() >0 }">
	<%Boolean ctrl= false; %>
	<c:forEach var="shak" items="${SecHakemList}">
	
	<c:if test="${shak.email ==hak.email}">
	<%ctrl=true; %>
	</c:if>
	</c:forEach>
	<c:if test="<%=ctrl == false%>">
  <tr>
    <td><input type="checkbox"   onchange="CheckedCountSecHak(${SecHakemList.size()})" id="${hak.id}"></td>
      <td><c:out value="${hak.name}" /></td>
      <td><c:out value="${hak.lastname}" /></td>
      <td><c:out value="${hak.email}" /></td>
      <td><c:out value="${hak.kategori.name}" /></td>
    </tr>
	</c:if>
    </c:if>
   <c:if test="${SecHakemList.size() ==0 }">
   <tr>
    <td><input type="checkbox"   onchange="CheckedCount()" id="${hak.id}"></td>
      <td><c:out value="${hak.name}" /></td>
      <td><c:out value="${hak.lastname}" /></td>
      <td><c:out value="${hak.email}" /></td>
      <td><c:out value="${hak.kategori.name}" /></td>
    </tr> 
    </c:if>
    </c:forEach>
	</tbody>

  </table>
	</div>
      
    </div>
  
    <input type="hidden"  id="hakid1" name="hakid1" value="-1" />
    <input type="hidden" id="hakid2" name="hakid2" value="-1" />
    <input type="hidden" id="hakid3" name="hakid3" value="-1" />

    <div class="container" style="background-color:#f1f1f1">
    
    <input type="submit" value="Onayla" class="cancelbtn" id="btn001" style="display:none;">
    </div>
  </form>
</div>
	<script>
function  CheckedCount() {
	
	var count = document.querySelectorAll('input[type="checkbox"]:checked');
	

	if(count.length == 3)
		{
		document.getElementById('btn001').style.display='block';
	
		const temp = document.querySelectorAll('input[type="checkbox"]:not(:checked)');
		
		temp.forEach((item) => {
		   item.disabled = true;
		});
		insertValue();
		}
	else if(count.length <3)
		{
		const temp = document.querySelectorAll('input[type="checkbox"]:not(:checked)');
		
		document.getElementById('btn001').style.display='none';
		
		temp.forEach((item) => {
		   item.disabled = false;
		});
	
		}
	
}


function  CheckedCountSecHak(event) {
	
	var count = document.querySelectorAll('input[type="checkbox"]:checked');
	

	if(count.length+event == 3)
		{
		document.getElementById('btn001').style.display='block';
	
		const temp = document.querySelectorAll('input[type="checkbox"]:not(:checked)');
		
		temp.forEach((item) => {
		   item.disabled = true;
		});
		insertValue();
		}
	else if(count.length <3)
		{
		const temp = document.querySelectorAll('input[type="checkbox"]:not(:checked)');
		
		document.getElementById('btn001').style.display='none';
		
		temp.forEach((item) => {
		   item.disabled = false;
		});
	
		}
	
}


</script>
	
<script>




function  insertValue() {
	

		document.getElementById('hakid1').value = document.querySelectorAll('input[type="checkbox"]:checked')[0].id;

		document.getElementById('hakid2').value = document.querySelectorAll('input[type="checkbox"]:checked')[1].id;
		
		document.getElementById('hakid3').value = document.querySelectorAll('input[type="checkbox"]:checked')[2].id;
		
		
	
	
	
}
</script>	

<!--pop-up end  -->




</body>
</html>