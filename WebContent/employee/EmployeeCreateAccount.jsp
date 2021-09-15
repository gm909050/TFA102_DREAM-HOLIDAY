<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ page import="web.employee.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("empVO");
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
<title>�ڷQ�����޲z�����U���� - DreamHoliday</title>
<link href="<%=request.getContextPath()%>/employee/css/styles.css"
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
}

div.card-footer {
	margin: 5px;
}

div.text-center {
	margin-top: 10px;
}

p.warn {
	color: red;
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

div.text-muted {
	margin: 0 auto;
	display: block;
	color: white !important;
}
</style>
</head>
<body>
	<div id="layoutAuthentication_content">
		<main>
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-7">
						<div class="card shadow-lg border-0 rounded-lg mt-5">
							<div class="card-header">
								<a href="<%=request.getContextPath()%>/home/hpbasic.jsp">
								<img class="logo"
									src="<%=request.getContextPath()%>/images/logo1.png" alt="">
								</a>
								<h3 class="text-center font-weight-light my-4">�ڷQ������O���U����</h3>
								<%-- ���~��C --%>
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">�Эץ��H�U���~:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
							</div>
							<div class="card-body">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath() %>/employee/empCreateServlet"
									name="form1">
									<div class="row mb-3">
										<div class="col-md-6">
											<div class="form-floating mb-3 mb-md-0">
												<input class="form-control Last_Name" id="inputFirstName"
													value="<%=(empVO == null) ? "" : empVO.getEmp_name()%>"
													type="text" name="emp_name"
													placeholder="Enter your first name" /> <label
													for="inputFirstName">���u�m�W</label>
											</div>
										</div>
									</div>
									<div class="form-floating mb-3">
										<input class="form-control" id="telNo" type="tel"
											value="<%=(empVO == null) ? "" : empVO.getId_number()%>"
											placeholder="0912345678" name="id_number" size="10" /> <label
											for="inputEmail">�����Ҧr��</label>
									</div>
									<div class="form-floating mb-3">
										<input class="form-control" id="telNo" type="tel"
											value="<%=(empVO == null) ? "" : empVO.getEmp_phone()%>"
											placeholder="0912345678" name="emp_phone" size="10" /> <label
											for="inputEmail">���u�q��</label>
									</div>
									<div class="form-floating mb-3">
										<input class="form-control" name="emp_mail" id="inputEmail"
											type="email"
											value="<%=(empVO == null) ? "" : empVO.getEmp_mail()%>"
											placeholder="name@example.com" /> <label for="inputEmail">���u�l��H�c</label>
									</div>
									<div class="row mb-3">
										<div class="col-md-6">
											<div class="form-floating mb-3 mb-md-0">
												<input class="form-control" id="inputPassword"
													name="password" type="password"
													placeholder="Create a password" /> <label
													for="inputPassword">���u�K�X</label>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-floating mb-3 mb-md-0">
												<input class="form-control" id="inputPasswordConfirm"
													name="confirm_password" type="password"
													placeholder="Confirm password" /> <label
													for="inputPasswordConfirm">�T�{�K�X</label>
											</div>
										</div>
									</div>

									<div class="row justify-content-center">
										<!-- Button trigger modal -->
										<input type="hidden" name="action" value="create"> <input
											id="btn_yes" class="btn btn-primary " type="submit"
											value="���U">
								</FORM>
							</div>
							<div class="modal fade" id="exampleModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">�t�ΰT��</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											�t�Τw�o�e�{�ҫH��ܱz���q�l�H�c <br> �нT�{�A���q�l�H�c
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">���}</button>
											<a href="memberlogin.html">
												<button type="button" class="btn btn-primary">
													��^�n�J����</button>
											</a>
										</div>
									</div>
								</div>
							</div>
							<div class="text-center">
								<p class="warn">
									�Ъ`�N DreamHoliday <br> ���|�H����z�ѭn�D�z��b�״� <br> �Y���B�F �H�H���d
								</p>
							</div>
						</div>
						<div class="card-footer text-center py-3">
							<div class="small">
								<a href="<%=request.getContextPath()%>/employee/login.jsp">�w�g�֦��b��?
									�I���o��^�n�J����</a>
							</div>
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
				<div class="d-flex align-items-center justify-content-between small">
					<div class="text-muted">Copyright &copy; DreamHoliday Team5
						Website 2021</div>
				</div>
			</div>
		</footer>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/employee/js/scripts.js"></script>
</body>
</html>