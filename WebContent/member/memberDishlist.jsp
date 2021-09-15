<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.employee.vo.*"%>
<%@ page import="web.employee.service.*"%>
<%@ page import="web.order.*"%>
<%@ page import="web.order.service.*"%>
<%@ page import="web.order.service.impl.*"%>
<%@ page import="web.booking.service.*"%>
<%@ page import="web.booking.service.impl.*"%>
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
	
	BookingInfoService boksvc = new BookingInfoServiceImpl();  


	OrderDetailService ordSvc = new OrderDetailServiceImpl();
	

	ordSvc.getAllByMealId(empVO.getEmp_id());

%>
<%
	EmployeeService empSvc = new EmployeeService();
	List<EmployeeVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
    
    
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>後台系統 - 訂房頁面 - DreamHoliday Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/employee/css/styles.css"
	rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
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

nav button#sidebarToggle {
	position: absolute;
	right: 10px;
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
	/*display: inline對齊效果*/
}

.ccle div {
	display: inline-block;
	vertical-align: left;
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
		<a class="navbar-brand ps-3" href="#"><i class="fas fa-user fa-fw"></i>管理人員介面</a>
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
                            data-bs-target="#collapseLayouts2" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"></div>
                            員工管理
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayouts2" aria-labelledby="headingOne"
                            data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="employee_data.jsp">個人資料</a>
                                <a class="nav-link" href="employee_changedata.jsp">個人資料修改</a>
                                <a class="nav-link" href="#">員工管理</a>
                            </nav>
                        </div>
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseLayouts" aria-expanded="false"
							aria-controls="collapseLayouts">
							<div class="sb-nav-link-icon"></div> 客房管理
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLayouts"
							aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="room_submit.jsp">客房上架</a> <a
									class="nav-link" href="roombooking_management.jsp">客房管理</a>
							</nav>
						</div>
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapsePages" aria-expanded="false"
							aria-controls="collapsePages">
							<div class="sb-nav-link-icon"></div> 場地管理
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapsePages"
							aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav accordion"
								id="sidenavAccordionPages">
								<a class="nav-link" href="placebooking_management.jsp">場地上架</a> <a
									class="nav-link" href="#">場地管理</a>
							</nav>
						</div>
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseRestaurant" aria-expanded="false"
							aria-controls="collapseRestaurant">
							<div class="sb-nav-link-icon"></div> 餐廳管理
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseRestaurant"
							aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="<%=request.getContextPath()%>/dish/listAllDish.jsp">菜餚管理</a>
<a class="nav-link" href="<%=request.getContextPath()%>/seat/listAllSeat.jsp">座位管理</a>
<a class="nav-link" href="<%=request.getContextPath()%>/seat/seatmap.jsp">餐廳平面圖</a>
									
							</nav>
						</div>
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseView" aria-expanded="false"
							aria-controls="collapseRestaurant">
							<div class="sb-nav-link-icon"></div> 相關設定
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseView"
							aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="<%=request.getContextPath()%>/nearby/nearbymanage.jsp">周邊景點</a>
                                    <a class="nav-link" href="<%=request.getContextPath()%>/oneday/onedaymanage.html">一日景點</a>
							</nav>
						</div>
						
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseLogout" aria-expanded="false"
							aria-controls="collapseLogout">
							<div class="sb-nav-link-icon"></div> 登出
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLogout"
							aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav accordion"
								id="sidenavAccordionPages">
								<a class="nav-link" href="login.jsp" data-bs-toggle="modal" data-bs-target="#logout">登出</a>
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
							<h5 class="modal-title" id="logoutLabel">系統訊息</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">確定登出嗎?</div>
						<div class="back modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">返回</button>
								<form method="post" action="<%=request.getContextPath()%>/employee/EmployeeLogout">
							<input type="submit" class="btn btn-primary" value="登出">
							</button>
								</form>
						</div>
					</div>
				</div>
			</div>
			<div class="wrapper">
				<div class='content'>
					<div class='ccle'>
						<div data-v-a2e60686="" class="tips-img-wrap">
							<img data-v-a2e60686=""
								src="data:image/jpeg;base64,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"
								alt="">
						</div>
						<div class='dm'>夢想假期員工管理系統</div>
					</div>
					
							<div class='box1'>
								<table class="table table-striped table-hover">
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>身分證字號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${empVO.emp_id}</td>
			<td>${empVO.emp_name}</td>
			<td>${empVO.id_number}</td>
			<td>
			  <FORM METHOD="post" ACTION="employee.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="emp_id"  value="${empVO.emp_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="employee.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="emp_id"  value="${empVO.emp_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
								
							</div>


						</div>
					</div>
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
	<script src="<%=request.getContextPath()%>/employee/js/scripts.js"></script>
	<script src="<%=request.getContextPath()%>/employee/js/jquery-3.6.0.min.js"></script>
	
</body>
</html>