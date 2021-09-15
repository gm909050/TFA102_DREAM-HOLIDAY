<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.dish.vo.*"%>

<%
	response.setHeader("Cache-Control","no-store"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0);

	Object obj =session.getAttribute("empVO");
	if(obj==null){
		request.getSession().setAttribute("location", "/Hotel_Team5_TFA102/dish/listAllDish.jsp");
		response.sendRedirect(request.getContextPath()+"/employee/login.jsp");
		return;
	}

	Dish dishVO = (Dish) request.getAttribute("dishVO");
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>��x�\�U�޲z���� - ���a�ק� - DreamHoliday Admin</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<style>

table#table-1 {
	background-image: linear-gradient(#0AA6B7, #2B3078);
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: white;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

table {
	width: 100%;
	color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    color: black;
    text-align: center;
  }

nav button#sidebarToggle {
	position: absolute;
	right: 10px;
}

.wrapper {
	min-height: 100%;
	margin-bottom: -68.8px;
}

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

</style>
</head>

<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-3" href="#"><i class="fas fa-user fa-fw"></i>�޲z�H������</a>
		<!-- Sidebar Toggle-->
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
			id="sidebarToggle" href="#!">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar Search-->
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
                            data-bs-target="#collapseLayouts2" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"></div>
                            ���u�޲z
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayouts2" aria-labelledby="headingOne"
                            data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                            	<a class="nav-link" href="<%=request.getContextPath()%>/employee/employee_data.jsp">�ӤH���</a>
                                <a class="nav-link" href="<%=request.getContextPath()%>/employee/employee_changedata.jsp">�ӤH��ƭק�</a>
                                <a class="nav-link" href="<%=request.getContextPath()%>/employee/employee_management.jsp">���u�޲z</a>
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
									class="nav-link" href="resbooking_management.jsp">�ȩк޲z</a>
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
								<a class="nav-link" href="res_submit.jsp">���a�޲z</a> 
								<a class="nav-link" href="resbooking_management.jsp">�y��޲z</a>
								<a class="nav-link" href="resbooking_management.jsp">�\�U������</a>
									
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
							</button>
								</form>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container">
				<div class="row">
					<table id="table-1">
						<tr>
							<td>
								 <h4>���a��ƭק� - update_dish.jsp</h4>
								 <h4><a href="listAllDish.jsp">�^����</a></h4>
							</td>
						</tr>
					</table>
				
					<h3>��ƭק�:</h3>
	
					<%-- ���~��C --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">�Эץ��H�U���~:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<FORM METHOD="post" ACTION="DishServlet" name="form1" enctype="multipart/form-data">
						<table class="table table-striped">
							<tr>
								<td>���a�s��:<font color=red><b>*</b></font></td>
								<td><%=dishVO.getDishId() %></td>
							</tr>
							
							<jsp:useBean id="mapSvc" scope="page" class="web.model.MappingService" />
							<tr>
								<td>���a�W��:</td>
								<td><input type="text" name="dishName" style="width: 400px;" value="<%=dishVO.getDishName() %>" /></td>
							</tr>
							<tr>
								<td>���a����:</td>
								<td><input type="text" name="dishPrice" style="width: 400px;" value="<%=dishVO.getDishPrice() %>" /></td>
							</tr>
							<tr>
								<td>���a����:</td>
								<td>
									<textarea  name="dishIntro" rows="5" cols="10" style="width:400px; text-align: left"><%=dishVO.getDishIntro() %></textarea>
								</td>
							</tr>
							<tr>
								<td>���a���O:</td>
								<td>
									<select size="1" name="dishType" style="width: 400px;">
										<c:forEach var="dishTypeVO" items="${mapSvc.getDishTypeAll()}">
											<option value="${dishTypeVO.dishType}" ${(dishVO.dishType==dishTypeVO.dishType)?'selected':'' } >${dishTypeVO.dishTypeName}
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td>���a���A:</td>
								<td>
									<select size="1" name="dishStatus" style="width: 400px;">
										<c:forEach var="dishStatusVO" items="${mapSvc.getDishStatusAll()}">
											<option value="${dishStatusVO.dishStatus}" ${(dishVO.dishStatus==dishStatusVO.dishStatus)?'selected':'' } >${dishStatusVO.dishStatusName}
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td>���a�Ϥ�:</td>
								<td><img id="showPicture" src="DBPicReader?dishId=<%=dishVO.getDishId() %>" style="width: 200px; height: 200px"></td>
								
							</tr>
							<tr>
								<td><input accept="image/*" type="file" name="uploadFile" id="upload"></td>
							</tr>
						</table>
						<br>
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="dishid" value="<%=dishVO.getDishId() %>">
						<input type="submit" value="�e�X�ק�">
					</FORM>
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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		window.addEventListener('load', function(){
			let uploadBtn = document.getElementById('upload');
			let imagePreview = document.getElementById('showPicture');
			uploadBtn.addEventListener('change', function(e){
				imagePreview.src = URL.createObjectURL(e.target.files[0]);
			});
		});
	</script>
</body>
</html>