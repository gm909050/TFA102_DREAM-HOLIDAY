<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.employee.vo.*"%>
<%

	response.setHeader("Cache-Control","no-store"); //HTTP 1.1
	response.setHeader("Pragma","no-cache");        //HTTP 1.0
	response.setDateHeader ("Expires", 0);
	
	 
	Object obj =session.getAttribute("empVO");
	if(obj==null){
	request.getSession().setAttribute("location", request.getRequestURI());
	 response.sendRedirect(request.getContextPath()+"/employee/login.jsp");
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
<title>��x�t�� - �q�Э��� - DreamHoliday Admin</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/employee/css/styles.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

nav button#sidebarToggle {
	position: absolute;
	right: 10px;
}

/*  .wrapper {  */
/*  	min-height: 100%;  */
/*  	margin-bottom: -68.8px;  */
/*  }  */

 .content { 
 	width: 100%; 
 	position: relative; 
 	padding-bottom: 68.8px; 
 } 
footer {
	height: 68.8px;
}

div.text-muted {
	margin: 0 auto;
	display: block;
	color: white !important;
}

#del_btn {
	position: relative;
	/* right: 10%; */
}

.bk {
	height: 10px;
}

.ccle {
	margin: 0px auto;
	text-align: left;
	/*display: inline����ĪG*/
}

.ccle div {
	display: inline-block;
	vertical-align: left;
	/* width: 130px; */
	/* height: 150px; */
	/* border: 1px solid #FF0000; */
	border-radius: 3px;
	margin: 2px;
}

.dm {
	font-size: 20px;
}

div button a.backlogin {
	text-decoration: none;
	color: white;
}

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

.btn_out {
	height: 50px;
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
		<form
			class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
			<div class="input-group"></div>
		</form>
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
								<a class="nav-link" href="#">�ӤH���</a>
                                <a class="nav-link" href="employee_changedata.jsp">�ӤH��ƭק�</a> 
								<a class="nav-link" href="employee_management.jsp">���u�޲z</a>
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
								<a class="nav-link" href="placebooking_management.jsp">���a�W�[</a>
								<a class="nav-link" href="#">���a�޲z</a>
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
								<a class="nav-link" href="login.jsp" data-bs-toggle="modal"
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
							</button>
								</form>
						</div>
					</div>
				</div>
			</div>

			<div class="wrapper">
				<div class='content'>
					<div class="container-fluid px-4">
						<h1 class="mt-4">�w��ϥιڷQ����������O���u�t��</h1>
						<ol class="breadcrumb mb-4">
							<li class="breadcrumb-item active">DreamHoliday Member</li>
						</ol>
					</div>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/empSelfServlet"
						name="form2">
						<div class="container rounded" id="main">
							<div class="row">
								<div class="mb-3 row">

									<label for="membernumber" class="col-sm-3 col-form-label">���u�s��</label>
									<div class="col-sm-9">
										    <input class="form-control" type="text" id="membernumber" name="emp_id" value="<%=empVO.getEmp_id()%>" aria-label="readonly input example" readonly>
									</div>
								</div>
								<div class="mb-3 row">
									<label for="empname" class="col-sm-3 col-form-label">���u�m�W</label>
									<div class="col-sm-9">
										     <input type="text" class="form-control" id="empname" name="emp_name"  value="<%=empVO.getEmp_name()%>"readonly>
									</div>
								</div>
								<div class="mb-3 row">
									<label for="empid" class="col-sm-3 col-form-label">�����Ҧr��</label>
									<div class="col-sm-9">
										      <input class="form-control" type="text" id="empid" name="id_number" value="<%=empVO.getId_number()%>" aria-label="readonly input example" readonly>
									</div>
								</div>
								<div class="mb-3 row">
									<label for="empphone" class="col-sm-3 col-form-label">���u�q��</label>
									<div class="col-sm-9">
										      <input type="text" class="form-control" id="empphone" name="emp_phone" value="<%=empVO.getEmp_phone()%>" readonly>
									</div>
								</div>
								<div class="mb-3 row">
									<label for="empmail" class="col-sm-3 col-form-label">���u�H�c</label>
									<div class="col-sm-9">
										      <input type="text" class="form-control" id="empmail" name="emp_mail" value="<%=empVO.getEmp_mail()%>"readonly>
									</div>
								</div>
								<div class="mb-3 row">
									<label for="emppassword" class="col-sm-3 col-form-label">�K�X</label>
									<div class="col-sm-9">
										       <input class="form-control" type="text" id="emppassword" name="password" value="<%=empVO.getPassword()%>" aria-label="readonly input example" readonly>
									</div>
								</div>
								<div class="mb-3 row">
									<label for="confirm_password" class="col-sm-3 col-form-label">�T�{�K�X</label>
									<div class="col-sm-9">
										       <input class="form-control" type="text" id="confirm_password" name="confirm_password" value="<%=empVO.getPassword()%>" aria-label="readonly input example" readonly>
									</div>
								</div>
								<div class="mb-3 row">
									<label for="emphiredate" class="col-sm-3 col-form-label">���u��¾��</label>
									<div class="col-sm-9">
										       <input class="form-control" type="text" id="emphiredate" name="hiredate" value="<%=empVO.getHiredate()%>" readonly>
									</div>
								</div>
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">�Эץ��H�U���~:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
								
								
								<div class="modal fade" id="change" tabindex="-1"
									aria-labelledby="changeDate" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="changeDate">�t�ΰT��</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">�T�w�ק��ƶ�?�ק��ƫ�ݭ��s�n�J</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">��^</button>
												<button type="button" class="btn btn-primary">
													<input type="hidden" name="action" value="selfupdate">
													<input type="hidden" name="emp_id" value="<%=empVO.getEmp_id()%>">
													<input class="btn btn-primary form-control backlogin"
														type="submit" value="�e�X">
					</FORM>
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="warning">
		<hr>
		<p>
			�����z�I<br> DreamHoliday�ڷQ�������|�H�q�ܳq�����I�ڤ覡�έn�D��HATM���s��b�C
			�礣�|�e�U�t�ӥH�q�ܳq���ܧ�I�ڤ覡�έn�D����ATM�״ڱb���C
		</p>
		<hr>
	</div>
	</div>
	</div>


	</div>

	</div>
	</div>
	</div>
	<footer class="py-4 bg-dark mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; DreamHoliday Team5 Website 2021</div>                            
                        </div>
                    </div>
                </footer>
	</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/employee/js/scripts.js"></script>
	<script src="<%=request.getContextPath()%>/employee/js/jquery-3.6.0.min.js"></script>
	
</body>
</html>