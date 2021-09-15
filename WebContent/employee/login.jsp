<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.employee.vo.*"%>
<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("empVO");
%>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="Chrome" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>後台登入頁面 - DreamHoliday Admin</title>
<link href="<%=request.getContextPath()%>/employee/css/styles.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
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

.btn_yes {
	width: 70px;
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
								<a href="<%=request.getContextPath()%>/hpbasic/hpbasic.jsp">
									<img class="logo"
										src="<%=request.getContextPath()%>/images/logo1.png" alt=""></a>
										
										
									<h3 class="text-center font-weight-light my-4">夢想假期後台登入頁面</h3>
								</div>

								<div class="card-body">
									<c:if test="${not empty errorMsgs}">
										<font style="color: red">請修正以下錯誤:</font>
										<ul>
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color: red">${message}</li>
											</c:forEach>
										</ul>
									</c:if>
									<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/employee/EmployeeServlet2"
										name="form1">
										<div class="form-floating mb-3">
											<input class="form-control" id="inputEmail" type="email"
												name="emp_mail" placeholder="name@example.com" /> <label
												for="inputEmail">請輸入使用者EMAIL</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" id="inputPassword"
												type="password" name="password" placeholder="Password" /> <label
												for="inputPassword">請輸入密碼</label>
										</div>
										<div class="form-check mb-3">
											<input class="form-check-input" id="inputRememberPassword"
												type="checkbox" value="" /> <label class="form-check-label"
												for="inputRememberPassword">記住此帳號</label>
										</div>
										<div class="d-flex align-items-center justify-content-between mt-4 mb-0">
											<a class="small" href="employeepassword.jsp">忘記密碼?</a> 
											<a class="small" href="EmployeeCreateAccount.jsp">新增員工</a>
											<input class="btn btn-primary form-control btn_yes" type="submit"value="登入"> 
											<input type="hidden" name="action" value="login">
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
		<div id="layoutAuthentication_footer">
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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/employee/js/scripts.js"></script>
</body>
</html>