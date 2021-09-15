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
										<a href="<%=request.getContextPath()%>/home/hpbasic.jsp">
                                       		 <img class="logo" src="<%=request.getContextPath()%>/images/logo1.png" alt="">
                                        </a>
                                <h3 class="text-center font-weight-light my-4">管理員重設密碼</h3>
                            </div>
                            <div class="card-body">
                                <div class="small mb-3 text-muted-mail">
                                    輸入您的電子郵件信箱<br>
                                    我們將向您發送重置密碼的連結
                             </div>
                             <FORM METHOD="post"  enctype="multipart/form-data" ACTION="<%=request.getContextPath()%>/employee/EMPLOYEEPassWord" name="form5">
                                <form>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="inputEmail" name="emp_mail" type="email"
                                            placeholder="name@example.com"  "/>
                                        <label for="inputEmail">電子郵件信箱</label>
                                    </div>
                                    
                                   <div class="form-floating mb-3">
                                        <input class="form-control" id="inputEmail" name="id_number"
                                        placeholder="name@example.com" "/>
                             
                                        <label for="inputEmail">身分證字號</label>
                                    </div>
                                    	<c:if test="${not empty errorMsgs}">
												<font style="color:red">請修正以下錯誤:</font>
												<ul>
												    <c:forEach var="message" items="${errorMsgs}">
														<li style="color:red">${message}</li>
													</c:forEach>
												</ul>
											</c:if>
                                    <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                        <a class="small"
                                            href="<%=request.getContextPath()%>/employee/login.jsp">返回登入頁面</a>
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                            data-bs-target="#exampleModal">
                                            內部員工重設密碼
                                        </button>
                                        <div class="modal fade" id="exampleModal" tabindex="-1"
                                            aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">系統訊息</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                      		  系統已發送連結網址至您的信箱,請勿關閉此視窗,傳送完畢會自動跳轉
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">關閉</button>
                                                        <button type="button" class="btn btn-primary">              									
                                                                <input id="btn_yes" class="btn btn-primary backlogin" type="submit" value="確定送出"></FORM>
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