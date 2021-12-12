  <%@page import="Model.Kategori"%>
<%@page import="Model.Alaneditor"%>
<%@page import="Model.makale"%>
<%@ page language="java" contentType="text/html; charset=iso-8859-9"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alan Editörü</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css%22%3E">
<link rel="shortcut icon" type="images/png" href="/icon2.png"/>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway%22%3E">
<meta name="keywords" content="Visitors Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap-css -->
<link rel="stylesheet" href="./YazarHome/bootstrap.min.css">
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="./YazarHome/style.css" rel="stylesheet" type="text/css">
<link href="./YazarHome/style-responsive.css" rel="stylesheet">
<!-- font CSS -->
<link href="file://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic" rel="stylesheet" type="text/css">
<!-- font-awesome icons -->
<link rel="stylesheet" href="./YazarHome/font.css" type="text/css">
<link href="./YazarHome/font-awesome.css" rel="stylesheet"> 
<link rel="stylesheet" href="./YazarHome/morris.css" type="text/css">
<!-- calendar -->
<link rel="stylesheet" href="./YazarHome/monthly.css">
<!-- //calendar -->
<!-- //font-awesome icons -->
<script src="./YazarHome/jquery2.0.3.min.js.indir"></script>
<script src="./YazarHome/raphael-min.js.indir"></script>
<script src="./YazarHome/morris.js.indir"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}
</style>
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


.container {
  padding: 16px;
  width:100%;
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
  width: 40%; /* Could be more or less, depending on screen size */
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
<%

Alaneditor alaneditor = new Alaneditor();
alaneditor.setId(-1);
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	switch(cookie.getName())
		{
	case "AlanEditorNAME":
		alaneditor.setName(cookie.getValue());
		break;
	case "AlanEditorLASTNAME":
		alaneditor.setLastname(cookie.getValue());
		break;
	case "AlanEditorEMAIL":
		alaneditor.setEmail(cookie.getValue());
		break;
	case "AlanEditorID":
		alaneditor.setId(Integer.parseInt(cookie.getValue()));
		break;
	case "AlanEditorKID":
		alaneditor.setKategori(new Kategori(Integer.parseInt(cookie.getValue()),null));
		break;
		}
	}
}
if(alaneditor.getId() == -1) response.sendRedirect("AlanEditorLogin");
%>
<body style="">
<section id="container">
<!--header start-->
<header class="header fixed-top clearfix">
<!--logo start-->
<style>
.brand {
    background: #8b5c7e;
    float: left;
    width: 280px;
    height: 94px;
    position: relative;
}
</style>
<div class="brand">
    <a class="logo">
        ALAN EDİTÖRÜ
    </a>
    <div class="sidebar-toggle-box">
        <div class="fa fa-bars"></div>
    </div>
</div>
<!--logo end-->

<div class="top-nav clearfix">
    <!--search & user info start-->
    <ul class="nav pull-right top-menu">
        <li>
        			<form action="<%=request.getContextPath()%>/AlanEditor_Logout" method="post">
        			<button class="w3-bar-item w3-right w3-red" style="background-color:Red; margin-top:0px">Çıkış Yap           <i class="fa fa-sign-out"></i></button>
					  </form>
       </li>
    </ul>
    <!--search & user info end-->
</div>
</header>
<!--header end-->
<!--sidebar start-->
<aside>
<style>
#sidebar {
    width: 280px;
    height: 100%;
    position: fixed;
    background: rgba(52, 48, 48, 0.78);
    -webkit-transition: all .3s ease-in-out;
    -moz-transition: all .3s ease-in-out;
    -o-transition: all .3s ease-in-out;
    transition: all .3s ease-in-out;
}</style>
    <div id="sidebar" class="nav-collapse">
        <!-- sidebar menu start-->
        <div class="leftside-navigation" tabindex="5000" style="overflow: hidden; outline: none;">
            <ul class="sidebar-menu" id="nav-accordion">
            <li>
                    <a class="active">
                        <i class="fa fa-user fa-2x"></i>
                        <span><%="Hoşgeldiniz "+alaneditor.getName()+" "+alaneditor.getLastname() %> </span>
                    </a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/AlanEditor_MakaleDurum?id=-1">
                        <i class="fa fa-cogs"></i>
                        <span>İşlenen Makaleler</span>
                    </a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/AlanEditor_MakaleDurum?id=0">
                        <i class="fa fa-cogs"></i>
                        <span>Onaylanan Makaleler</span>
                    </a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/HakemList">
                        <i class="fa fa-cogs"></i>
                        <span>Hakem İşlemleri</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa fa-dashboard"></i>
                        <span>Anasayfa</span>
                    </a>
                </li>
                
            </ul>    
         </div>
        <!-- sidebar menu end-->
    <div id="ascrail2000" class="nicescroll-rails" style="width: 3px; z-index: auto; cursor: default; position: absolute; top: 0px; left: 237px; height: 947px; display: none; opacity: 0;"><div style="position: relative; top: 0px; float: right; width: 3px; height: 0px; background-color: rgb(139, 92, 126); border: 0px solid rgb(255, 255, 255); background-clip: padding-box; border-radius: 0px;"></div></div><div id="ascrail2000-hr" class="nicescroll-rails" style="height: 3px; z-index: auto; top: 944px; left: 0px; position: absolute; cursor: default; display: none; opacity: 0;"><div style="position: relative; top: 0px; height: 3px; width: 0px; background-color: rgb(139, 92, 126); border: 0px solid rgb(255, 255, 255); background-clip: padding-box; border-radius: 0px; left: 0px;"></div></div><div id="ascrail2001" class="nicescroll-rails" style="width: 3px; z-index: auto; cursor: default; position: absolute; top: 0px; left: 237px; height: 947px; display: none; opacity: 0;"><div style="position: relative; top: 0px; float: right; width: 3px; height: 0px; background-color: rgb(139, 92, 126); border: 0px solid rgb(255, 255, 255); background-clip: padding-box; border-radius: 0px;"></div></div><div id="ascrail2001-hr" class="nicescroll-rails" style="height: 3px; z-index: auto; top: 944px; left: 0px; position: absolute; cursor: default; display: none; opacity: 0;"><div style="position: relative; top: 0px; height: 3px; width: 0px; background-color: rgb(139, 92, 126); border: 0px solid rgb(255, 255, 255); background-clip: padding-box; border-radius: 0px; left: 0px;"></div></div></div>
</aside>
<!--sidebar end-->
<!--main content start-->
<section id="main-content" class="">
	<section class="wrapper">
		<!-- //market-->
			
			
		
</section>
 <!-- footer -->
		  <div class="col-md-6 w3agile-notifications" style="padding-left: 56px;width: 100%;">
			<div class="notifications">
				<!--notification start-->
				<h5 style="color:Red">*Görülen Makaleler Yeşil Görülmeyenler Kırmızıdır.</h5>
					<header class="panel-heading">
						Gelen Makaleler 
						
					</header>
										<div class="notify-w3ls">
					<c:forEach var="makale" items="${makaleler}">
						<c:if test="${makale.aEditorDate != null}">
							<div class="alert alert-success ">
								<span class="alert-icon"><i class="fa fa-eye"></i></span>
								<div class="notification-info">
									<ul class="clearfix notification-meta">
										<li class="pull-left notification-sender"><c:out value="${makale.makaleBilgi}" /></li>
										<li class="pull-right notification-time"><a  class="w3-right w3-circle w3-margin-right" style="width:35px" href="MakViewerAll?id=<c:out value='${makale.ID}' />">Makaleye Göz At...</a>
</li>
									</ul>
									<p> 
									<c:out value="Yazar Adı Soyadı : ${makale.yazar.name} ${makale.yazar.lastname}" />
									</p>
								</div>
							</div>
							</c:if>
						<c:if test="${makale.aEditorDate == null}">
							<div class="alert alert-danger ">
								<span class="alert-icon"><i class="fa fa-eye-slash"></i></span>
								<div class="notification-info">
									<ul class="clearfix notification-meta">
										<li class="pull-left notification-sender"><c:out value="${makale.makaleBilgi}" /></li>
										<li class="pull-right notification-time"><a   style="width:35px" href="MakViewerAll?id=<c:out value='${makale.ID}' />">Makaleye Göz At...</a>
</li>
									</ul>
									<p>
										<c:out value="Yazar Adı Soyadı : ${makale.yazar.name} ${makale.yazar.lastname}" />
									</p>
								</div>
							</div>
							</c:if>
						</c:forEach>
					</div>
				<!--notification end-->
				</div>
			</div>
  <!-- / footer -->
</section>
<!--main content end-->
</section>
<script src="./YazarHome/bootstrap.js.indir"></script>
<script src="./YazarHome/jquery.dcjqaccordion.2.7.js.indir"></script>
<script src="./YazarHome/scripts.js.indir"></script>
<script src="./YazarHome/jquery.slimscroll.js.indir"></script>
<script src="./YazarHome/jquery.nicescroll.js.indir"></script>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="js/flot-chart/excanvas.min.js"></script><![endif]-->
<script src="./YazarHome/jquery.scrollTo.js.indir"></script>
<!-- morris JavaScript -->	
<script>
	$(document).ready(function() {
		//BOX BUTTON SHOW AND CLOSE
	   jQuery('.small-graph-box').hover(function() {
		  jQuery(this).find('.box-button').fadeIn('fast');
	   }, function() {
		  jQuery(this).find('.box-button').fadeOut('fast');
	   });
	   jQuery('.small-graph-box .box-close').click(function() {
		  jQuery(this).closest('.small-graph-box').fadeOut(200);
		  return false;
	   });
	   
	    //CHARTS
	    function gd(year, day, month) {
			return new Date(year, month - 1, day).getTime();
		}
		
		graphArea2 = Morris.Area({
			element: 'hero-area',
			padding: 10,
        behaveLikeLine: true,
        gridEnabled: false,
        gridLineColor: '#dddddd',
        axes: true,
        resize: true,
        smooth:true,
        pointSize: 0,
        lineWidth: 0,
        fillOpacity:0.85,
			data: [
				{period: '2015 Q1', iphone: 2668, ipad: null, itouch: 2649},
				{period: '2015 Q2', iphone: 15780, ipad: 13799, itouch: 12051},
				{period: '2015 Q3', iphone: 12920, ipad: 10975, itouch: 9910},
				{period: '2015 Q4', iphone: 8770, ipad: 6600, itouch: 6695},
				{period: '2016 Q1', iphone: 10820, ipad: 10924, itouch: 12300},
				{period: '2016 Q2', iphone: 9680, ipad: 9010, itouch: 7891},
				{period: '2016 Q3', iphone: 4830, ipad: 3805, itouch: 1598},
				{period: '2016 Q4', iphone: 15083, ipad: 8977, itouch: 5185},
				{period: '2017 Q1', iphone: 10697, ipad: 4470, itouch: 2038},
			
			],
			lineColors:['#eb6f6f','#926383','#eb6f6f'],
			xkey: 'period',
            redraw: true,
            ykeys: ['iphone', 'ipad', 'itouch'],
            labels: ['All Visitors', 'Returning Visitors', 'Unique Visitors'],
			pointSize: 2,
			hideHover: 'auto',
			resize: true
		});
		
	   
	});
	</script>
<!-- calendar -->
<script>
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
<!-- File Upload End -->

</body></html>