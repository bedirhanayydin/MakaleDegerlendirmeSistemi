    <%@ page language="java" contentType="text/html; charset=iso-8859-9"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
<title>Yazar Kayıt Ol</title>
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
<link rel="stylesheet" href="css/font.css" type="text/css"/>
<link href="css/cssLogin/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<script src="js/jsLogin/jquery2.0.3.min.js"></script>
</head>
<body>
<div class="reg-w3">
<div class="w3layouts-main">
	<h2>Kayıt Ol</h2>
		<form action="<%=request.getContextPath()%>/YazarRegistration" method="post">
			<input type="text" class="ggg" name="Name" placeholder="AD" required="">
			<input type="text" class="ggg" name="Lastname" placeholder="SOYAD" required="">
			<input type="email" class="ggg" name="Email" placeholder="E-MAIL" required="">
			<input type="password" class="ggg" name="Password" placeholder="ŞİFRE" required="">
			<input type="text" class="ggg" name="Workplace" placeholder="İŞ YERİ" required="">			
				<div class="clearfix"></div>
				<input type="submit" value="Kayıt Ol" name="register">
		</form>
		<p>Hesabım zaten var.<a href="<%=request.getContextPath()%>/YazarLogin">Giriş Yap</a></p>
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