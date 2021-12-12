<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="zxx"><head>
    <title>Hakem Giriş Sayfası</title>
    <!-- Meta tag Keywords -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <meta name="keywords" content="Report Login Form Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design">
    <!-- //Meta tag Keywords -->
    <link href="//fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400;500;700;900&amp;display=swap" rel="stylesheet">
    <!--/Style-CSS -->
    <link rel="stylesheet" href="css/cssAlanEditör/style.css" type="text/css" media="all">
    <!--//Style-CSS -->

    <link rel="stylesheet" href="css/cssAlanEditör/font-awesome.min.css" type="text/css" media="all">

</head>

<body>

    <!-- form section start -->
    <section class="w3l-hotair-form">
        <h1>Hakem Giriş Sayfası</h1>
        <div class="container">
            <!-- /form -->
            <div class="workinghny-form-grid">
                <div class="main-hotair">
                    <div class="content-wthree">
                        <h2>Giriş Yap</h2>
                        <form action="<%=request.getContextPath()%>/HakemLogin" method="post">
                            <input type="text" class="text" name="email" placeholder="E-Mail Giriniz" required="" autofocus="">
                            <input type="password" class="password" name="password" placeholder="Şifreyi Giriniz" required="" autofocus="">
                            <button class="btn" type="submit">Giriş Yap</button>
                        </form>
                    </div>
                    <div class="w3l_form align-self">
                        <div class="left_grid_info">
                            <img src="images/1.png" alt="" class="img-fluid">
                        </div>
                    </div>
                </div>
            </div>
            <!-- //form -->
        </div>
        <!-- copyright-->
        
        <!-- //copyright-->
    </section>
    <!-- //form section start -->
    </body>
    </html>