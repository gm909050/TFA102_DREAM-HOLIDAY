<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="web.member.*"%>
<%@ page import="web.member.vo.*"%>
<%	
	response.setHeader("Cache-Control","no-store"); //HTTP 1.1
	response.setHeader("Pragma","no-cache");        //HTTP 1.0
	response.setDateHeader ("Expires", 0);
	
	Object obj =session.getAttribute("memberVO");
	if(obj==null){
		
		response.sendRedirect(request.getContextPath()+"/member/loginTest1.jsp");
		return;
	}
	MemberVO memberVO = (MemberVO) obj;
	session.setAttribute("memberVO", memberVO);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>夢想假期會員系統主畫面 - DreamHoliday Members</title>
<link
	href="<%=request.getContextPath()%>/https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" />
<script
	src="<%=request.getContextPath()%>/https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<style>
nav button#sidebarToggle {
	position: absolute;
	right: 10px;
}

div.logopic {
	margin: 0 auto;
	display: block;
}

div label {
	text-align: start;
}

div input {
	text-align: center;
}

div#main {
	width: 90%;
	border: 1px solid gray;
	background-color: rgb(252, 244, 244);
	margin-bottom: 20px;
}

div.nav a {
	margin: 5px auto;
	font-size: 20px;
}

div.text-muted {
	margin: 0 auto;
	display: block;
	color: white !important;
}

div a button {
	width: 100%;
	margin-bottom: 15px;
}

div button a.backlogin {
	text-decoration: none;
	color: white;
}

div.warning p {
	color: red;
}
</style>
</head>
<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark"
		id="header">
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-3" href="#"><i class="fas fa-user fa-fw"></i>會員系統頁面</a>
		<!-- Sidebar Toggle-->
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
			id="sidebarToggle" href="#!">
			<i class="fas fa-bars"></i>
		</button>
	</nav>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<a class="nav-link collapsed" href="#"> 會員資料 </a> <a
							class="nav-link collapsed" href="img/null2.jpg"> 消費紀錄 </a> <a
							class="nav-link collapsed" href="img/null2.jpg"> 常見Q&A </a> <a
							class="nav-link collapsed" href="img/null2.jpg"> 修改密碼 </a> <a
							class="nav-link collapsed" href="#" data-bs-toggle="modal"
							data-bs-target="#logout"> 登出系統 </a>
					</div>
				</div>
				<div class="sb-sidenav-footer">
					<div class="small">Logged in as:</div>
					DreamHoliday Team5
				</div>
			</nav>
		</div>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">歡迎使用夢想假期飯店會員系統</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item active">DreamHoliday Member</li>
					</ol>
				</div>
			</main>
			<!-- Modal -->
			<div class="modal fade" id="logout" tabindex="-1"
				aria-labelledby="logoutLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="logoutLabel">系統訊息</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">確定登出嗎?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">返回</button>
							<button type="button" class="btn btn-primary">
							<FORM METHOD="get"  ACTION="<%=request.getContextPath()+"/MemberLogOut"%>" name="form">
								<input class=" btn btn-primary btn-primary" type="submit" value="登出，返回首頁">
								</FORM>
							</button>
						</div>
					</div>
				</div>
			</div>
			<FORM METHOD="post" enctype="multipart/form-data" ACTION="<%=request.getContextPath()+"/MemberLoginTest"%>">
			<div class="container rounded" id="main">
				<div class="row">
					<div class="mb-3 row">
						<label for="membernumber"  class="col-sm-3 col-form-label">會員編號</label>
						<div class="col-sm-9">
							<input type="text" readonly name="memberId"
								class="form-control-plaintext" id="member" value="<%=memberVO.getMemberId() %>">
						</div>
					</div>
					<div class="mb-3 row">
						<label for="membername" class="col-sm-3 col-form-label">會員姓名</label>
						<div class="col-sm-9">
							<input type="text" readonly name="memberName"
								class="form-control-plaintext" id="membername" value="<%=memberVO.getName() %>">
						</div>
					</div>
					<div class="mb-3 row">
						<label for="memberid" class="col-sm-3 col-form-label">身分證字號</label>
						<div class="col-sm-9">
							<input type="text" name="idNumber" readonly
								class="form-control-plaintext" id="memberid" value="<%=memberVO.getIdNumber() %>">
						</div>
					</div>
					<div class="mb-3 row">
						<label for="memberphone" class="col-sm-3 col-form-label">連絡電話</label>
						<div class="col-sm-9">
							<input type="text" name="phone" readonly
								class="form-control-plaintext" id="memberphone"
								value="<%=memberVO.getPhone() %>">
						</div>
					</div>
					<div class="mb-3 row">
						<label for="membermail" class="col-sm-3 col-form-label">電子郵件信箱</label>
						<div class="col-sm-9">
							<input type="text" name="email" readonly
								class="form-control-plaintext" id="membermail"
								value="<%=memberVO.getEmail() %>"/>
						</div>
					</div>
					<div class="mb-3 row">
						<label for="password" class="col-sm-3 col-form-label">密碼</label>
						<div class="col-sm-9">
							<input type="text" name="password"
								class="form-control-plaintext" id="password" value="<%=memberVO.getPassword()%>">
						</div>
					</div>
					<div class="menu container">
						<div class="row ">
							<div class="row justify-content-center">
										</FORM>
							
										<input type="hidden" name="updateList" value="updateList" >
										<a class="col-xl-12 col-md-4 col-lg-3" href="<%=request.getContextPath()+"/member/MemberUpdate.jsp"%>">
                                        <button type="button" class="btn btn-primary">修改資料</button>
                                    </a>

							</div>
						</div>
					</div>
				</div>
				<div class="warning">
					<hr>
					<p>
						提醒您！<br> DreamHoliday夢想假期不會以電話通知更改付款方式或要求改以ATM重新轉帳。
						亦不會委託廠商以電話通知變更付款方式或要求提供ATM匯款帳號。
					</p>
					<hr>
				</div>
			</div>
			<footer class="py-4 bg-dark mt-auto">
				<div class="container-fluid px-4">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; DreamHoliday Team5
							Website 2021</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/assets/demo/chart-area-demo.js"></script>
	<script src="<%=request.getContextPath()%>/js/scripts.js"></script>
	<script src="<%=request.getContextPath()%>/js/scripts2.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/js/datatables-simple-demo.js"></script>
</body>
</html>