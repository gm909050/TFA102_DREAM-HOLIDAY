<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="web.booking.vo.BookingInfo"%>
<%@page import="java.sql.Date"%>
<%@page import="web.booking.service.impl.BookingInfoServiceImpl"%>
<%@page import="web.booking.service.BookingInfoService"%>
<%@page import="com.google.gson.Gson"%>
<%@ page import="web.seat.service.impl.SeatServiceImpl"%>
<%@ page import="web.seat.service.SeatService"%>
<%@ page import="java.util.*"%>
<%@ page import="web.seat.vo.*"%>

<%
	response.setHeader("Cache-Control","no-store"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0);

	Object obj =session.getAttribute("empVO");
	if(obj==null){
		request.getSession().setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath()+"/employee/login.jsp");
		return;
	}

	SeatService seatSvc = new SeatServiceImpl();
	List<Seat> list = seatSvc.getSeatAllFromRedis();

	BookingInfoService bookSvc = new BookingInfoServiceImpl();
	List<BookingInfo> bookMemberList = bookSvc.getBookMember(java.sql.Date.valueOf(java.time.LocalDate.now()));
	System.out.println("xxx"+bookMemberList);
	if(bookMemberList != null){
		pageContext.setAttribute("bookMemberList",bookMemberList);
	} else {
		System.out.println("�D��~�ɶ��ΨS���H�w���A�i�H���L�F");
	}
%>

<jsp:useBean id="memberSvc" scope="page" class="web.member.service.impl.MemberServiceImpl" />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>��x�\�U�޲z���� - �\�७���� - DreamHoliday Admin</title>
        
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <style>
            nav button#sidebarToggle{
                position: absolute;
                right: 10px;
            }            
            div.container-fluid{
                width: 90%;                
            }
            h1.dish{
                margin: 20px 70px;
            }
            div.text-muted{
                margin: 0 auto;
                display: block;
                color: white !important;
            }
            div button a.backlogin{
                text-decoration: none;
                color: white ;
            }
            div.dishmap{
                margin-top: 20px;
                padding: 20px;
                width: 90%;
                background-color: rgb(240, 236, 236);
            }
            div.dishtable{
                border: solid 1px black;
                margin: 10px;
                height: 100px;
                position: relative;
            }
            div.dishtable button.btn{
                position: absolute;
                bottom: 0px;
                left: 0px;
            }
            div ul li.dropdown-item{
                text-align: center;
            }
        </style>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="#"><i class="fas fa-user fa-fw"></i>�A�ȤH������</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>            
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseMembers" aria-expanded="false" aria-controls="collapseMembers">
                                <div class="sb-nav-link-icon"></div>
                                �|���t��
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseMembers" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="<%=request.getContextPath()%>/employee/employee_data.jsp">�ӤH���</a>
	                                <a class="nav-link" href="<%=request.getContextPath()%>/employee/employee_changedata.jsp">�ӤH��ƭק�</a>
	                                <a class="nav-link" href="<%=request.getContextPath()%>/employee/employee_management.jsp">���u�޲z</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"></div>
                               �ȩк޲z
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="room_submit.jsp">�ȩФW�[</a> <a
									class="nav-link" href="resbooking_management.jsp">�ȩк޲z</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"></div>
                                ���a�޲z
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link" href="place_submit.jsp">���a�W�[</a> <a
									class="nav-link" href="placebooking_management.jsp">���a�޲z</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseRestaurant" aria-expanded="false" aria-controls="collapseRestaurant">
                                <div class="sb-nav-link-icon"></div>
                                �\�U�޲z
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseRestaurant" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="<%=request.getContextPath()%>/dish/listAllDish.jsp">���a�޲z</a> 
									<a class="nav-link" href="<%=request.getContextPath()%>/seat/listAllSeat.jsp">�y��޲z</a>
									<a class="nav-link" href="<%=request.getContextPath()%>/seat/seatmap.jsp">�\�U������</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseSetting" aria-expanded="false" aria-controls="collapseSetting">
                                <div class="sb-nav-link-icon"></div>
                                �����]�w
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseSetting" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                   <a class="nav-link" href="<%=request.getContextPath()%>/nearby/nearbymanage.jsp">�P�䴺�I</a>
								   <a class="nav-link" href="<%=request.getContextPath()%>/oneday/onedaymanage.html">�@�鴺�I</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLogout" aria-expanded="false" aria-controls="collapseLogout">
                                <div class="sb-nav-link-icon"></div>
                                �n�X
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLogout" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#logout">�n�X</a>
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
                <div id="myModal" class="modal" tabindex="-1">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title">���ܰT��</h5>
				      </div>
				      <div class="modal-body">
				        <h3 id="pContent"></h3>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="javascript:location.href='seatmap.jsp'">�T�{</button>
				      </div>
				    </div>
				  </div>
				</div>
                <!-- Modal -->
                <div class="modal fade" id="logout" tabindex="-1" aria-labelledby="logoutLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="logoutLabel">�t�ΰT��</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                �T�w�n�X��?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">��^</button>
                                <form method="post" action="<%=request.getContextPath()%>/employee/EmployeeLogout">
							<input type="submit" class="btn btn-primary" value="�n�X">
							</button>
								</form>
                            </div>
                        </div>
                    </div>
                </div>
                <h1 class="dish">�\�७����</h1>
                <div>
                	<select name="book_member_list">
					    <option value="-1" selected="selected">�q��|���C��</option>					    
					    <c:forEach var="bookMemberVO" items="${bookMemberList}">
							<c:forEach var="memberVO" items="${memberSvc.all}">
			                    <c:if test="${memberVO.memberId == bookMemberVO.getMemberId()}">
				                    <option value="${bookMemberVO.getMealId()}">${memberVO.name} - ${bookMemberVO.bookingSeattype} �H�y</option>
			                    </c:if>
		                	</c:forEach>
		                </c:forEach>
					</select>
                </div>
                
                <div class="dishmap container">
                   <div class="row seat_list">

                   </div>
                </div>
                <div>
                	<button type="button" id="btnClearRedis" class="btn btn-outline-info">Reset Redis</button>
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

        <script type="text/javascript">
        	$(window).on("DOMContentLoaded", function(){
				
//         		���ูͮ��
				var objArray = <%=new Gson().toJson(list) %>;
				$(objArray).each(function(index, item){
					console.log(item.seatCode+":"+item.seatType);
					if("0" !== item.seatCode){
						let seatTypeStr = "";
						seatTypeStr = mappingType(item.seatType);
						
						let seatStatusStr = "";
						seatStatusStr = mappingStatus(item.seatStatus);
						
						$('.seat_list').append(insertAppendData(item.seatCode, seatTypeStr, seatStatusStr));
					}
				});
				
				$("#btnClearRedis").on("click", function(){
					$.ajax({
						url: "${pageContext.request.contextPath}/seat/SeatMapServlet",
			            type: "POST",
			            data: {"isResetRedis" : "true"},
			            dataType: "json",
			            success: function(data){
							console.log(data);
			            	
			            }
			        });
				});
				
				$('.dropdown-menu li a').on('click', function(){
					let item_id = $(this).closest("div").attr("data-id");
					let select_value = $(this).attr("data-status");
// 					alert("item_id: "+item_id+", select_value: "+select_value);
					
					let select_meal_id = $("select[name='book_member_list']").val();
					$("select[name='book_member_list'] option").prop('selected', function() {
				        return this.defaultSelected;
				    });
					
					console.log($(this).closest("div").find(".table_status").text());
					
					if($(this).closest("div").find(".table_status").text() === "���m�u"){
						if(select_meal_id > 0){
							let status_change_param = {
						            item_id: item_id,
						            select_status: select_value,
						            select_meal_id: select_meal_id
						    }
							change_status_by_ajax(status_change_param, $(this));
						} else {
							alert("�п�ܭq��|��!!");
						}
					} else {
						let status_change_param = {
					            item_id: item_id,
					            select_status: select_value,
					            select_meal_id: select_meal_id
					    }
						change_status_by_ajax(status_change_param, $(this));
					}
				});

				
				function change_status_by_ajax(status_change_param, that){
					$.ajax({
						url: "${pageContext.request.contextPath}/seat/SeatMapServlet",
			            type: "POST",
			            data: status_change_param,
			            dataType: "json",
			            success: function(data){
							console.log(data);
			            	if(data["select_status"] == 4){
								console.log("����w�I�\���^��")
								$(that).closest("div").find(".table_status").text(mappingStatus(data["select_status"])); //�ק�ୱ���A���
								//�ǳƸ��୶��
								window.location.href="<%=request.getContextPath()%>/order/dishOrder.jsp";
							} else if(data["select_status"] == 5){
								console.log("���X���b����")
								$(that).closest("div").find(".table_status").text(mappingStatus(2)); //�ק�ୱ���A���
								console.log("totalPrice:"+data["totalPrice"]);
								$("#pContent").html("�y��: "+data["seat_code"]+"<br>���b���B�@: "+data["totalPrice"]+" ����");
	           	            	$("#myModal").show();
							} else {
								$(that).closest("div").find(".table_status").text(mappingStatus(data["select_status"]));
				            	if(data.seatStatus == 1){ //���פ��A���s���i�I��
				            		$(that).closest("div").find("button").addClass("disabled");
				            	};
				            	location.reload(); //���㭶���A����s���q��|���C��
							}
			            },
			            error: function (xhr) {
			            	if(xhr.responseText == "Error1"){
			            		alert("���q��w���L��");
			            	} else if(xhr.responseText == "Error2"){
			            		alert("�I�窱�A�M�ثe���A�ۦP");
			            	} else if(xhr.responseText == "Error3"){
			            		alert("���I�\->�w�I�\�A�Ӯण�ӿ���s�q���");
			            	} else if(xhr.responseText == "Error5"){
			            		alert("�w�I�\->���m�u(���b)�A�Ӯण�ӿ���s�q���");
			            	}
			            }
			        });
				}
				
				function mappingType(type_val){
					let seatTypeStr = "";
					switch(type_val){
						case 2:
							seatTypeStr = "���H��";
							break;
						case 4:
							seatTypeStr = "�|�H��";
							break;
						case 6:
							seatTypeStr = "���H��";
							break;
					}
					return seatTypeStr;
				}
				
				function mappingStatus(status_val){
					let seatStr = "";
					switch(status_val){
						case 1:
							seatStr = "���פ�";
							break;
						case 2:
							seatStr = "���m�u";
							break;
						case 3:
							seatStr = "���I�\";
							break;
						case 4:
							seatStr = "�I�\";
							break;
						case 5:
							seatStr = "���b";
							break;
					}
					return seatStr;
				}

        		function insertAppendData(item_id, table_type, table_status){
        			let isDisabled = "";
        			if(table_status == "���פ�"){
        				isDisabled = "disabled";
        			}
        			
        	        let li_html = `
        	        	<div data-id="\${item_id}" class="dishtable col-5 col-sm-3 col-md-2">
        	        	\${item_id}
                        <br>
                        	\${table_type}
                            <button class="container-fluid btn btn-secondary btn-sm dropdown-toggle \${isDisabled}" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                             	<span class="table_status">\${table_status}</span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#" data-status="1">���פ�</a></li>
                                <li><a class="dropdown-item" href="#" data-status="2">���m�u</a></li>
                                <li><a class="dropdown-item" href="#" data-status="3">���I�\</a></li>
                                <li><a class="dropdown-item" href="#" data-status="4">�I�\</a></li>
                                <li><a class="dropdown-item" href="#" data-status="5">���b</a></li>
                            </ul>
                    </div>
        	        `;
        	        return li_html;
        	    }
	        });
        </script>
    </body>
</html>