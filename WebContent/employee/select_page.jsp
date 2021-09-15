<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.employee.vo.*"%>
<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);

	Object obj = session.getAttribute("empVO");
	if (obj == null) {

		response.sendRedirect(request.getContextPath() + "/employee/login.jsp");
		return;

	}
	EmployeeVO empVO = (EmployeeVO) obj;
	session.setAttribute("empVO", empVO);
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>��x�t�ΥD�e�� - DreamHoliday Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/employee/css/styles.css"
	rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
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

div.logopic img {
	max-width: 100%;
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

div button a.backlogin {
	text-decoration: none;
	color: white;
}
</style>
</head>

<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		
		<a class="navbar-brand ps-3" href="#"><i class="fas fa-user fa-fw"></i>�޲z�H������</a>
		
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
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseLayouts2" aria-expanded="false"
							aria-controls="collapseLayouts">
							<div class="sb-nav-link-icon"></div> ���u�޲z
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLayouts2"
							aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="employee_data.jsp">�ӤH���</a> <a
									class="nav-link" href="employee_changedata.jsp">�ӤH��ƭק�</a> <a
									class="nav-link" href="employee_management.jsp">���u�޲z</a>
							</nav>
						</div>
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseLayouts" aria-expanded="false"
							aria-controls="collapseLayouts">
							<div class="sb-nav-link-icon"></div> �ȩк޲z
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLayouts"
							aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<!--                                 <a class="nav-link" href="roomsubmit.html">�ȩФW�[</a> -->
								<a class="nav-link" href="room_submit.jsp">�ȩФW�[</a> <a
									class="nav-link" href="roombooking_management.jsp">�ȩк޲z</a>
							</nav>
						</div>
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapsePages" aria-expanded="false"
							aria-controls="collapsePages">
							<div class="sb-nav-link-icon"></div> ���a�޲z
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapsePages"
							aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav accordion"
								id="sidenavAccordionPages">
								<a class="nav-link" href="place_submit.jsp">���a�W�[</a> <a
									class="nav-link" href="placebooking_management.jsp">���a�޲z</a>
							</nav>
						</div>
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseRestaurant" aria-expanded="false"
							aria-controls="collapseRestaurant">
							<div class="sb-nav-link-icon"></div> �\�U�޲z
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseRestaurant"
							aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="<%=request.getContextPath()%>/dish/listAllDish.jsp">���a�޲z</a>
<a class="nav-link" href="<%=request.getContextPath()%>/seat/listAllSeat.jsp">�y��޲z</a>
<a class="nav-link" href="<%=request.getContextPath()%>/seat/seatmap.jsp">�\�U������</a>
									
							</nav>
						</div>
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseView" aria-expanded="false"
							aria-controls="collapseRestaurant">
							<div class="sb-nav-link-icon"></div> �����]�w
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseView"
							aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="<%=request.getContextPath()%>/nearby/nearbymanage.jsp">�P�䴺�I</a>
                                    <a class="nav-link" href="<%=request.getContextPath()%>/oneday/onedaymanage.html">�@�鴺�I</a>
							</nav>
						</div>
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseLogout" aria-expanded="false"
							aria-controls="collapseLogout">
							<div class="sb-nav-link-icon"></div> �n�X
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLogout"
							aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav accordion"
								id="sidenavAccordionPages">
								<a class="nav-link" href="#" data-bs-toggle="modal"
									data-bs-target="#logout">�n�X</a>
							</nav>
						</div>
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
					<h1 class="mt-4">�w��ϥιڷQ����������x�޲z�t��</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item active">DreamHoliday Admin</li>
					</ol>
				</div>
			</main>


			<!-- Modal -->
			<div class="modal fade" id="logout" tabindex="-1"
				aria-labelledby="logoutLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="logoutLabel">�t�ΰT��</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>

						<div class="modal-body">�T�w�n�X��?</div>
						<div class="back modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">��^</button>
								<form method="post" action="<%=request.getContextPath()%>/employee/EmployeeLogout">
							<input type="submit" class="btn btn-primary" value="�n�X">
<!-- 								<a class="backlogin" href="login.jsp">�n�X</a> -->
							</button>
								</form>
						</div>
					</div>
				</div>
			</div>


			<div class="logopic">
				<img src="<%=request.getContextPath()%>/images/logo.png" alt="">
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
	<script src="<%=request.getContextPath()%>/employee/js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/employee/assets/demo/chart-area-demo.js"></script>
	<script
		src="<%=request.getContextPath()%>/employee/assets/demo/chart-bar-demo.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/employee/js/datatables-simple-demo.js"></script>
</body>
</html>