<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.member.*"%>


<!DOCTYPE html>
<html lang="UTF-8">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="Chrome" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>會員登入頁面 - DreamHoliday Admin</title>
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<style>
#layoutAuthentication_content {
	background-color: #fff;
}

main {
	background-color: #fff;
}

div.card-header {
	background: linear-gradient(#2B3078, #0AA6B7);
	color: white;
}

div img.logo {
	width: 40%;
	margin: 0 auto;
	display: block;
}

div.text-muted {
	margin: 0 auto;
	display: block;
	color: white !important;
}

* {
	margin: 0;
	padding: 0;
}

div.card-header {
	background: linear-gradient(#2B3078, #0AA6B7);
	color: white;
}

div img.logo {
	width: 40%;
	margin: 0 auto;
	display: block;
}

main {
	margin: 50px auto;
	margin-bottom: 100px;
}

div.text-muted {
	margin: 0 auto;
	display: block;
	color: white !important;
}

div button a.backlogin {
	text-decoration: none;
	color: white;
}

footer {
	background: linear-gradient(#0AA6B7, #2B3078);
	width: 100%;
	vertical-align: bottom;
	text-align: center;
	/* position: absolute; */
	bottom: 0px;
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
</style>
</head>
<body class="bg-primary">
	<div id="layoutAuthentication">
		<div id="layoutAuthentication_content">
			<main>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-5">
							<div class="card shadow-lg border-0 rounded-lg mt-5">
								<div class="card-header">
									<a href="<%=request.getContextPath()%>/hpbasic/hpbasic.jsp"> <img
										class="logo"
										src="<%=request.getContextPath()%>/images/logo1.png" alt="">
									</a>
									<h3 class="text-center font-weight-light my-4">夢想假期會員登入頁面</h3>
								</div>
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>


								<div class="card-body">
									<FORM METHOD="POST" enctype=" multipart/form-data"
										ACTION="<%=request.getContextPath()%>/MemberLoginTest"
										name="form1">
										<div class="form-floating mb-3">
											<input class="form-control" id="inputEmail" type="email"
												name="email" placeholder="name@example.com" /> <label
												for="inputEmail">請輸入使用者帳號</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" id="inputPassword"
												type="Password" name="password" placeholder="Password" /> <label
												for="inputPassword">請輸入密碼</label>
										</div>
										<div class="form-check mb-3">
											<input class="form-check-input" id="inputRememberPassword"
												type="checkbox" value="" /> <label class="form-check-label"
												for="inputRememberPassword">記住此帳號</label>
										</div>

										<div
											class="d-flex align-items-center justify-content-between mt-4 mb-0">
											<a class="small" style="font-size:"
												5px;" href="<%=request.getContextPath()%>/member/ForgetPassword.jsp">忘記密碼?</a>
											<input type="hidden" name="action" value="login"> <input
												class="btn btn-primary form-control hotelLogin"
												type="submit" value="登入">
									</FORM>

								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
		</div>
		</main>
	</div>
<!-- 	<div id="layoutAuthentication_footer"> -->
		<footer>
			<a class="col-auto" href=""> <img class="col-auto"
				src="<%=request.getContextPath()%>/images/logo1.png" alt="">
			</a>
			<div id="sns">
				<a href=""><img class="fb"
					src="<%=request.getContextPath()%>/images/Facebook_icon.svg.png"
					alt=""></a> <a href=""><img class="sns"
					src="<%=request.getContextPath()%>/images/ig.png" alt=""></a> <a
					href=""><img class="twitter"
					src="<%=request.getContextPath()%>/images/Twitter_bird_logo_2012.svg.png"
					alt=""></a> <a href=""><img class="sns"
					src="<%=request.getContextPath()%>/images/line.png" alt=""></a> <a
					href=""><img class="yt"
					src="<%=request.getContextPath()%>/images/youtube.svg.png" alt=""></a>
			</div>
			<div>
				<p>電話:02:2712-0689</p>
				<p>傳真:02:2712-0689</p>
				<p>信箱:tibame@gmail.com</p>
				<p>地址:台北市中山區南京東路三段219號5樓</p>
			</div>
			<div class="container-fluid px-4">
				<div class="d-flex align-items-center justify-content-between small">
					<div class="text-light">Copyright &copy; DreamHoliday Team5
						Website 2021</div>
				</div>
			</div>
		</footer>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
</body>
</html>
