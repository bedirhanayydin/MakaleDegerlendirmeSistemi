    <%@ page language="java" contentType="text/html; charset=iso-8859-9"
	pageEncoding="UTF-8"%>
<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html style >
<head>
<style>
html, body{
	font-family: 'Roboto', sans-serif;
    font-size: 100%;
    overflow-x: hidden;
    background: url(images/bg.jpg) no-repeat 0px 0px;
	background-size:cover;
}
</style>
<title>Yazar Giriş Yap </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Visitors Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap-css -->
<link rel="stylesheet" href="css/cssLogin/bootstrap.min.css" >
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="css/cssLogin/style.css" rel='stylesheet' type='text/css' />
<link href="css/cssLogin/style-responsive.css" rel="stylesheet"/>
<!-- font CSS -->
<link href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
<!-- font-awesome icons -->
<link rel="stylesheet" href="css/cssLogin/font.css" type="text/css"/>
<link href="css/cssLogin/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<script src="js/jsLogin/jquery2.0.3.min.js"></script>
</head>
<body style>
<div class="log-w3">
<div class="w3layouts-main">
	<h2>Giriş Yap</h2>
		<form action="<%=request.getContextPath()%>/YazarLogin" method="post">
			<input type="email" class="ggg" name="Email" placeholder="E-MAIL" required="">
			<input type="password" class="ggg" name="Password" placeholder="ŞİFRE" required="">
			<span></span>
				<div class="clearfix"></div>
				<input type="submit" value="Giriş Yap" name="login">
		</form>
		<p>Hesabınız Yok mu?<a href="YazarRegistration">Hesap Oluştur</a></p>
</div>
</div>
<script src="js/jsLogin/bootstrap.js"></script>
<script src="js/jsLogin/jquery.dcjqaccordion.2.7.js"></script>
<script src="js/jsLogin/scripts.js"></script>
<script src="js/jsLogin/jquery.slimscroll.js"></script>
<script src="js/jsLogin/jquery.nicescroll.js"></script>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="js/flot-chart/excanvas.min.js"></script><![endif]-->
<script src="js/jsLogin/jquery.scrollTo.js"></script>
</body>
</html>
