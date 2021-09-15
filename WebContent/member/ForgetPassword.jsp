<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="Chrome" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>會員頁面 - 忘記密碼</title>
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
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

<body>
	<div id="layoutAuthentication_content">
		<main>
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-5">
						<div class="card shadow-lg border-0 rounded-lg mt-5">
							<div class="card-header">
								<a href="<%=request.getContextPath()%>/hpbasic/hpbasic.jsp">
									<img class="logo" src="<%=request.getContextPath()%>/images/logo1.png" alt="">
									</a>
								<h3 class="text-center font-weight-light my-4">重設密碼</h3>
							</div>
							<div class="card-body">
								<div class="small mb-3 text-muted-mail">
									輸入您的電子郵件信箱<br> 我們將向您發送重置密碼的連結
								</div>
								<%--                              <FORM METHOD="post" ACTION="<%=request.getContextPath()+"/MailServlet"%>" name="form5"> --%>
								<form ACTION="<%=request.getContextPath() + "/MailServlet"%>"
									method="POST">
									<div class="form-floating mb-3">
										<input class="form-control" id="inputEmail" name="email"
											type="email" placeholder="name@example.com" /> <label
											for="inputEmail">電子郵件信箱</label>
									</div>

									<div class="form-floating mb-3">
										<input class="form-control" id="inputEmail" name="id_number"
											placeholder="name@example.com" /> <label for="inputEmail">身分證字號</label>
									</div>
									<c:if test="${not empty errorMsgs}">
										<font style="color: red">請修正以下錯誤:</font>
										<ul>
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color: red">${message}</li>
											</c:forEach>
										</ul>
									</c:if>
									<div
										class="d-flex align-items-center justify-content-between mt-4 mb-0">
										<a class="small"
											href="<%=request.getContextPath()%>/member/loginTest1.jsp">返回登入頁面</a>
										<button type="button" class="btn btn-primary"
											data-bs-toggle="modal" data-bs-target="#exampleModal">
											重設密碼</button>
										<div class="modal fade" id="exampleModal" tabindex="-1"
											aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">系統訊息</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">
														確認資料正確無誤後,請勿關閉此視窗,傳送完畢會自動跳轉</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">關閉</button>
														<button type="button" class="btn btn-primary">
															<input id="btn_yes" class="btn btn-primary backlogin"
																name="send" type="submit" value="確定送出">
								</FORM>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			</form>
	</div>
	</div>
	</div>
	</div>
	</div>
	</main>
	</div>
	<!-- <div id="layoutAuthentication_footer"> -->
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
	<!-- </div> -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>

</body>

</html>