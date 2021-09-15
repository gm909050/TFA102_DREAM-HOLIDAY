<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.member.vo.*"%>
<%

		response.setHeader("Cache-Control","no-store"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0);
		
		Object obj =session.getAttribute("memberVO");
		if(obj==null){
			request.getSession().setAttribute("location", request.getRequestURI());
			response.sendRedirect(request.getContextPath()+"/member/loginTest1.jsp");
			return;
		}
		
		MemberVO memberVO = (MemberVO) obj;
%>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="Chrome" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>�ڷQ�����\�U�q�\�D���� - DreamHoliday dish page</title>
<link href="css/styles.css" rel="stylesheet" />

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<style>
* {
	margin: 0;
	padding: 0;
}

header {
	background: linear-gradient(#2B3078, #0AA6B7);
	height: 125px;
}

div.photos {
	background-color: rgba(0, 0, 0, 0.904);
}

button#sidebarToggle {
	position: absolute;
	top: 50px;
	right: 10px;
	color: black;
}

button#sidebarToggle svg path {
	height: 80px;
}

a.nav-link {
	background-color: black;
	opacity: 90%;
	color: white;
	left: -20px;
	width: 100%;
}

div.collapse nav a {
	width: 200px;
}

div.head {
	position: absolute;
	width: 100%;
	z-index: 4;
}

div div.topmenu ul {
	margin-top: -24px;
	color: white;
	list-style: none;
}

div div.topmenu ul li {
	display: none;
}

div div.topmenu ul li a {
	color: white;
	text-decoration: none;
}

ul li {
	vertical-align: auto
}

ul:hover li {
	background-color: #0AA6B7;
}

ul li:hover {
	background-color: gray;
}

div.title {
	width: 95%;
	margin: 20px;
}

img.titlelogo {
	display: inline-block;
	height: 80px;
	width: 140px;
}

h1 {
	padding: 15px;
}

div.form {
	/* min-height: 350px; */
	margin: 15px;
}

div.article {
	width: 80%;
	margin: 10px auto;
}

div.gonextpage {
	text-align: center;
	margin: 10px auto;
}

button.gonext {
	width: 80%;
}

h3 {
	margin: 15px;
	width: 95%;
}

a button.btn {
	margin: 15px;
}

label.btn-outline-primary {
	max-width: 150px;
}

label.btn-outline-warning {
	max-width: 150px;
}

label.btn-outline-info {
	max-width: 150px;
}

footer {
	background: linear-gradient(#0AA6B7, #2B3078);
	position: absolute;
	width: 100%;
	clear: both;
	vertical-align: bottom;
	text-align: center;
}

footer #logo2 {
	width: 100vh;
	display: inline-block;
}

footer div p {
	display: inline-block;
	font-size: 20px;
	color: white;
	margin: 10px 10px;
	text-align: center;
}

footer div {
	max-width: 1000px;
	margin: 10px auto;
	text-align: center;
	position: relative;
}

footer div#sns {
	display: inline-block;
	position: relative;
}

footer img {
	display: inline-block;
	margin: 10px 10px;
	vertical-align: bottom;
}

footer img.sns {
	width: 50px;
	height: 50px;
}

footer img.twitter {
	width: 55px;
	height: 50px;
}

footer img.fb {
	width: 50px;
	height: 50px;
}

footer img.yt {
	width: 80px;
	height: 50px;
}

div.main.row {
	margin: 30px;
	width: 100%;
}

div.main div {
	margin: 20px;
}

div.main2 div {
	margin: 20px;
}

div div img.card {
	max-width: 500px;
}

div.main2 {
	display: none;
}

@media ( min-width : 768px) {
	button#sidebarToggle, #layoutSidenav {
		display: none;
	}
}

@media ( max-width : 767.98px) {
	div.head {
		display: none;
	}
}

@media ( max-width : 1139.98px) {
	div.main {
		display: none;
	}
}

@media ( max-width : 1140px) {
	div.main2 {
		display: block;
	}
}
</style>
</head>
<body>
	<header class="container-fluid">
		<div class="row justify-content-center">
			<a class="col-auto"
				href="<%=request.getContextPath()%>/hpbasic/hpbasic.jsp"> <img
				class="col-auto"
				src="<%=request.getContextPath()%>/images/logo1.png" alt="">
			</a>
		</div>
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
			id="sidebarToggle" href="#!">
			<i class="fas fa-bars"></i>
		</button>
		<div id="layoutSidenav">
			<div id="layoutSidenav_nav">
				<nav class="sb-sidenav accordion sb-sidenav-menu"
					id="sidenavAccordion">
					<div class="sb-sidenav-menu">
						<div class="nav">
							<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
								data-bs-target="#collapseLayouts" aria-expanded="false"
								aria-controls="collapseLayouts">
								<div class="sb-nav-link-icon"></div> �|���M��
								<div class="sb-sidenav-collapse-arrow">
									<i class="fas fa-angle-down"></i>
								</div>
							</a>
							<div class="collapse" id="collapseLayouts"
								aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
								<nav class="sb-sidenav-menu-nested nav">
									<a class="nav-link"
										href="<%=request.getContextPath()%>/DH_backsystem/login.jsp">�|���n�J</a>
									<a class="nav-link"
										href="<%=request.getContextPath()%>/DH_backsystem/register.jsp">���U�b��</a>
									<!-- <a class="nav-link" href="memberpassword.html">�ѰO�K�X</a> -->
								</nav>
							</div>
							<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
								data-bs-target="#collapsePages" aria-expanded="false"
								aria-controls="collapsePages">
								<div class="sb-nav-link-icon"></div> ���a����
								<div class="sb-sidenav-collapse-arrow">
									<i class="fas fa-angle-down"></i>
								</div>
							</a>
							<div class="collapse" id="collapsePages"
								aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
								<nav class="sb-sidenav-menu-nested nav accordion"
									id="sidenavAccordionPages">
									<a class="nav-link" href="sitebookingmain.html">��ܳ��a</a>
								</nav>
							</div>
							<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
								data-bs-target="#collapseRestaurant" aria-expanded="false"
								aria-controls="collapseRestaurant">
								<div class="sb-nav-link-icon"></div> �u�W�q��
								<div class="sb-sidenav-collapse-arrow">
									<i class="fas fa-angle-down"></i>
								</div>
							</a>
							<div class="collapse" id="collapseRestaurant"
								aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
								<nav class="sb-sidenav-menu-nested nav">
									<a class="nav-link" href="roombookingmain.html">������</a> <a
										class="nav-link" href="showroom.html">�Ы�����</a>
								</nav>
							</div>
							<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
								data-bs-target="#collapseSetting" aria-expanded="false"
								aria-controls="collapseSetting">
								<div class="sb-nav-link-icon"></div> �u�W�q�\
								<div class="sb-sidenav-collapse-arrow">
									<i class="fas fa-angle-down"></i>
								</div>
							</a>
							<div class="collapse" id="collapseSetting"
								aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
								<nav class="sb-sidenav-menu-nested nav accordion"
									id="sidenavAccordionPages">
									<a class="nav-link" href="dishMain.jsp">�u�W�w��</a> <a
										class="nav-link" href="dishmenu-1.jsp">���˦�</a>
								</nav>
							</div>
							<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
								data-bs-target="#collapseSetting" aria-expanded="false"
								aria-controls="collapseSetting">
								<div class="sb-nav-link-icon"></div> �P�����
								<div class="sb-sidenav-collapse-arrow">
									<i class="fas fa-angle-down"></i>
								</div>
							</a>
							<div class="collapse" id="collapseSetting"
								aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
								<nav class="sb-sidenav-menu-nested nav accordion"
									id="sidenavAccordionPages">
									<a class="nav-link" href="#">��q���n</a> <a class="nav-link"
										href="#">�P�䴺�I</a> <a class="nav-link" href="#">�@�鴺�I</a>
								</nav>
							</div>
							<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
								data-bs-target="#collapseLogout" aria-expanded="false"
								aria-controls="collapseLogout">
								<div class="sb-nav-link-icon"></div> �Ȥ�A��
								<div class="sb-sidenav-collapse-arrow">
									<i class="fas fa-angle-down"></i>
								</div>
							</a>
							<div class="collapse" id="collapseLogout"
								aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
								<nav class="sb-sidenav-menu-nested nav accordion"
									id="sidenavAccordionPages">
									<a class="nav-link" href="error.html">�p���ڭ�</a> <a
										class="nav-link" href="#" data-bs-toggle="modal"
										data-bs-target="#logout">FAQ</a>
								</nav>
							</div>
						</div>
					</div>
				</nav>
			</div>
			<div class="divforul col"></div>
	</header>
	<div class="head row justify-content-center">
		<div class="topmenu col-auto">
			<ul id="menu-1">
				<span class="member">�|���M��</span>
				<li><a href="<%=request.getContextPath()%>/member/loginTest1.jsp">�|���n�J</a></li>
             	<li><a href="<%=request.getContextPath()%>/member/MemberCreateAccount.jsp">���U�|��</a></li>
                <li><a href="<%=request.getContextPath()%>/member/MemberList.jsp">�|�����</a></li>
				<!-- <li><a href="memberpassword.html">�ѰO�K�X</a></li> -->
			</ul>
		</div>
		<div class="topmenu col-auto">
			<ul id="menu-2">
				<span class="site">���a����</span>
				<li><a href="">��ܳ��a</a></li>
			</ul>
		</div>
		<div class="topmenu  col-auto">
			<ul id="menu-3">
				<span class="booking">�u�W�q��</span>
				<li><a href="">������</a></li>
				<li><a href="">�Ы�����</a></li>
			</ul>
		</div>
		<div class="topmenu  col-auto">
			<ul id="menu-4">
				<span class="restaurant">�u�W�q�\</span>
				<li><a href="<%=request.getContextPath()%>/dish/dishMain.jsp">�u�W�w��</a></li>
				<li><a href="<%=request.getContextPath()%>/dish/dishmenu-1.jsp">���˦�</a></li>
			</ul>
		</div>
		<div class="topmenu  col-auto">
			<ul id="menu-5">
				<span class="map">�P�����</span>
				<li><a href="<%=request.getContextPath()%>/map/map.html">��q���n</a></li>
                <li><a href="<%=request.getContextPath()%>/nearby/nearby.jsp">�P�䴺�I</a></li>
                <li><a href="<%=request.getContextPath()%>/oneday/Oneday.html">�@�鴺�I</a></li>
			</ul>
		</div>
		<div class="topmenu  col-auto">
			<ul id="menu-6">
				<span class="faq">�Ȥ�A��</span>
				<li><a href="<%=request.getContextPath()%>/mail/mail.html">�p���ڭ�</a></li>
                <li><a href="<%=request.getContextPath()%>/faq/FAQ.html">FAQ</a></li>
			</ul>
		</div>
	</div>
	<div class="photos container-fluid">
		<div class="row justify-content-center">
			<div class="col-10">
				<div id="carouselExampleFade" class="carousel slide carousel-fade"
					data-bs-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="../images/dish017.jpg" class="w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="../images/dish020.jpg" class="w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="../images/dish002.jpg" class="w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="../images/dish003.jpg" class="w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="../images/dish004.jpg" class="w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="../images/dish012.jpg" class="w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="../images/dish018.jpg" class="w-100" alt="...">
						</div>
					</div>
					<!-- 					<button class="carousel-control-prev" type="button" -->
					<!-- 						data-bs-target="#carouselExampleFade" data-bs-slide="prev"> -->
					<!-- 						<span class="carousel-control-prev-icon" aria-hidden="true"></span> -->
					<!-- 						<span class="visually-hidden">Previous</span> -->
					<!-- 					</button> -->
					<!-- 					<button class="carousel-control-next" type="button" -->
					<!-- 						data-bs-target="#carouselExampleFade" data-bs-slide="next"> -->
					<!-- 						<span class="carousel-control-next-icon" aria-hidden="true"></span> -->
					<!-- 						<span class="visually-hidden">Next</span> -->
					<!-- 					</button> -->
				</div>
			</div>
		</div>
	</div>
	<div class="title container-fluid">
		<div class="row justify-content-center">
			<img class="titlelogo" src="<%=request.getContextPath() %>/images/logo3.png"
				alt="">
		</div>
		<h1 class="row justify-content-center">�u�W�q��d��</h1>
	</div>

	<div class="check">
		<div class="form">
			<div class="" id="tables">
				<div class="container">
					<div class="">
						<label for="" class="col-form-label">�п�ܮɬq</label>
					</div>
					<div class="row ">
						<div class="col-4 col-md-3 col-lg-2">
							<input type="radio" class="btn-check" name="options-outlined"
								id="primary-outlined" value="1" autocomplete="off"> <label
								class="btn btn-outline-primary container" for="primary-outlined">���\</label>
						</div>
						<div class="col-4 col-md-3 col-lg-2">
							<input type="radio" class="btn-check" name="options-outlined"
								id="warning-outlined" value="2" autocomplete="off"> <label
								class="btn btn-outline-warning container" for="warning-outlined">���\</label>
						</div>
						<div class="col-4 col-md-3 col-lg-2">
							<input type="radio" class="btn-check" name="options-outlined"
								id="info-outlined" value="3" autocomplete="off"> <label
								class="btn btn-outline-info container" for="info-outlined">���\</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="article">
		<br>
		<hr>
		<br>
	</div>

	<div class="check">
		<div class="form">
			<div class="row container-fluid" id="tables">
				<div class="col-12 col-md-6">
					<div class="col-12">
						<label class="col-form-label">�п�ܤH��</label>
					</div>
					<div class="col-12">
						<select class="question form-select"
							aria-label="Default select example" id="people-selecter">
							<option selected>�п�ܥ��\�H��</option>
							<option value="2">2�H</option>
							<option value="4">4�H</option>
							<option value="6">6�H</option>
						</select>
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="row">
						<div class="col-12">
							<label for="datetimepicker1" class="col-form-label">�w�q���</label>
						</div>
						<div class='col-12'>
							<div class="form-group">
								<div class='input-group date' id='datetimepicker1'>
									<input type='text' class="form-control" id="showPickDate" /> <span
										class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="article">
		<br>
		<hr>
		<br>
	</div>
	<div class="gonextpage container">
		<a href="#">
			<button type="button" class="btn btn-success gonext" id="btnConfirm">�T�{�q��</button>
		</a>
	</div>
	<div class="article">
		<br>
		<hr>
		<br>
	</div>
	<div></div>
	<div class="menu container">
		<h3>�s�����</h3>
		<div class="row">
			<a class="col-6 col-md-3 col-lg-2" href="dishmenu-1.jsp">
				<button type="button" class="btn btn-success gonext">����</button>
			</a> <a class="col-6 col-md-3 col-lg-2" href="dishmenu-2.jsp">
				<button type="button" class="btn btn-danger gonext">�覡</button>
			</a> <a class="col-6 col-md-3 col-lg-2" href="dishmenu-3.jsp">
				<button type="button" class="btn btn-primary gonext">���I</button>
			</a> <a class="col-6 col-md-3 col-lg-2" href="dishmenu-4.jsp">
				<button type="button" class="btn btn-warning gonext">���~</button>
			</a>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
		aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content bg-light text-dark">

				<div class="modal-header">
					<h3 class="modal-title" id="myModalLabel">
						<i class="fas fa-exclamation-circle"></i>���ܰT��
					</h3>
				</div>

				<div class="modal-body">
					<h3 id="pContent"></h3>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="javascript:location.href='<%=request.getContextPath()%>/hpbasic/hpbasic.jsp'">�T�{</button>
				</div>
			</div>
		</div>
	</div>

	<div class="article">
		<hr>
	</div>
	<div class="article">
		<p class="">
			<b> ��~�ɶ� <br> ���\�G8:30am �V 10:30pm <br> ���\�G11:30am �V
				2:30pm <br> ���\�G 5:30pm �V 9:30pm <br>
			</b> <br> *�q��H�ƽЧt�ĵ��H�ơA�H�ŦX���\�ξA�סC<br>
			*���w�ู�B�H�Υ]�[���L�k���ѽu�W�q��A�Ь��\�U�A�ȤH���q��C <br> *�S��`�y����H�Υ]�[�����A�ΦX�@�H�Υd�u�f�C <br>
			<br> 2021�S��`�y����p�U�G <br> �����s��(2021/1/1-1/3)
			�B����A��s�~����(2021/2/10-2/16) �B
			���˸`����(2021/5/8-5/9)�B�ݤȸ`�s��(2021/6/12-6/14)�B
			���˸`����(2021/8/7-8/8)�B����`�s��(2021/9/18-9/21)�B
			��y��s��(2021/10/9-10/11)�B�t�ϸ`����(2021/12/24-12/26)�B ��~����(2021/12/31-
			2022/1/1)�C <br> <b> <br>
				*�۳ưs���A�ȶO�����覡�G����s�C�~NT500�B�P�s�C�~NT1,000�C<br>
				*�P�@������}���P�H�W�u�W�q��A���L�k���Ѩ֮�ݨD�C<br>
			</b>
	</div>
	<div class="article">
		<hr>
	</div>
	<footer>
		<a class="col-auto" href=""> <img class="col-auto"
			src="<%=request.getContextPath()%>/images/logo1.png" alt="">
		</a>
		<div id="sns">
			<a href=""><img class="fb"
				src="<%=request.getContextPath()%>/images/Facebook_icon.svg.png"
				alt="facebook"></a> <a href=""><img class="sns"
				src="<%=request.getContextPath()%>/images/ig.png" alt=""></a> <a
				href=""><img class="twitter"
				src="<%=request.getContextPath()%>/images/Twitter_bird_logo_2012.svg.png"
				alt="twitter"></a> <a href=""><img class="sns"
				src="<%=request.getContextPath()%>/images/line.png" alt="line"></a>
			<a href=""><img class="yt"
				src="<%=request.getContextPath()%>/images/youtube.svg.png"
				alt="youtube"></a>
		</div>
		<div>
			<p>�q��:02:2712-0689</p>
			<p>�ǯu:02:2712-0689</p>
			<p>�H�c:tibame@gmail.com</p>
			<p>�a�}:�x�_�����s�ϫn�ʪF���T�q219��5��</p>
		</div>
		<div class="container-fluid px-4">
			<div class="d-flex align-items-center justify-content-between small">
				<div class="text-light">Copyright &copy; DreamHoliday Team5
					Website 2021</div>
			</div>
		</div>
	</footer>

	<script src="js/scripts.js"></script>
	<script>
	  $(document).ready(function () {
	    $("ul#menu-1").mouseenter(function () {
	      $("#menu-1 li").toggle();
	    });
	    $("ul#menu-1").mouseleave(function () {
	      $("#menu-1 li").toggle();
	    });
	
	    $("ul#menu-2").mouseenter(function () {
	      $("#menu-2 li").toggle();
	    });
	    $("ul#menu-2").mouseleave(function () {
	      $("#menu-2 li").toggle();
	    });
	
	    $("ul#menu-3").mouseenter(function () {
	      $("#menu-3 li").toggle();
	    });
	    $("ul#menu-3").mouseleave(function () {
	      $("#menu-3 li").toggle();
	    });
	
	    $("ul#menu-4").mouseenter(function () {
	      $("#menu-4 li").toggle();
	    });
	    $("ul#menu-4").mouseleave(function () {
	      $("#menu-4 li").toggle();
	    });
	
	    $("ul#menu-5").mouseenter(function () {
	      $("#menu-5 li").toggle();
	    });
	    $("ul#menu-5").mouseleave(function () {
	      $("#menu-5 li").toggle();
	    });
	
	    $("ul#menu-6").mouseenter(function () {
	      $("#menu-6 li").toggle();
	    });
	    $("ul#menu-6").mouseleave(function () {
	      $("#menu-6 li").toggle();
	    });
	
	    $("#btnConfirm").on("click", function () {
	      let sendFlag = true;
	
	      var whichTime = $('input[name="options-outlined"]:checked').val();
	      if (typeof whichTime == "undefined") {
	        sendFlag = false;
	        alert("�п�ܮɬq");
	      } else {
// 	        alert("�ɬq:" + whichTime);
	      }
	
	      var peopleNum = $("#people-selecter").val();
	      if (isNaN(peopleNum)) {
	        sendFlag = false;
	        alert("�п�ܤH��");
	      } else {
// 	        alert("�H��:" + peopleNum);
	      }
	
	      var whichDate = $("#showPickDate").val();
	      if (typeof whichDate == "undefined" || whichDate == "") {
	        sendFlag = false;
	        alert("�п�ܤ��");
	      } else {
// 	        alert("���:" + whichDate);
	      }
	
	      if (sendFlag) {
	        let bookingParam = {
	          memberId: <%=memberVO.getMemberId()%>,
	          whichTime: whichTime,
	          peopleNum: peopleNum,
	          whichDate: whichDate,
	        };
	        $.ajax({
	          url: "${pageContext.request.contextPath}/booking/BookingServlet",
	          type: "POST",
	          data: bookingParam,
	          dataType: "json",
	          success: function (data) {
	            // console.log(data.respText);
	            if (data.respText === "success") {
	              $("#pContent").text("�q�즨�\");
	              $("#basicModal").modal({
	                show: true,
	              });
	            }
	          },
	          error: function (xhr, status, error) {
	            console.log(xhr.responseText);
	            $("#pContent").text("�q�쥢��");
	            $("#basicModal").modal({
	              show: true,
	            });
	          },
	        });
	      }
	    });
	  });
</script>
	<script type="text/javascript">
		$(function() {
			let disabledDate = [ '2021-8-20', '2021-8-23', '2021-8-30' ];
			var max_Date = new Date();
			max_Date.setDate(max_Date.getDate() + 6);
			$("#datetimepicker1").datetimepicker({
				format : "YYYY-MM-DD",
				minDate : new Date(),
				maxDate : max_Date,
				disabledDates : disabledDate
			});
		});
	</script>
</body>
</html>