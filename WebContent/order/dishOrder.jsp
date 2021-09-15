<%@page import="java.io.Console"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="web.dish.service.impl.DishServiceImpl"%>
<%@page import="web.dish.service.DishService"%>
<%@ page import="java.util.*"%>
<%@ page import="web.dish.vo.*"%>
<%@ page import="web.order.vo.*"%>
<%@page import="com.google.gson.Gson"%>

<% 
	//�����ޥ[�I���ާ@�A�i�����N�O���s�I��
	DishService dishSvc = new DishServiceImpl();
	List<Dish> list = dishSvc.getAllOnShelves(); //dish_id, dish_name, dish_price
	
	Integer meal_id = (Integer) session.getAttribute("meal_id");
%>


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
        <link href="css/bootstrap.min.css" rel="stylesheet">
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
            th, td {
			    padding: 5px;
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
                                    <a class="nav-link" href="">�|���d��</a>
                                    <a class="nav-link" href="">�ק���</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"></div>
                                �q�Ч@�~
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="roombooking.html">�q��޲z</a>
                                    <a class="nav-link" href="roommap.html">�Ӽh������</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"></div>
                                ���a����
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link" href="sitebooking.html">�q��޲z</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseRestaurant" aria-expanded="false" aria-controls="collapseRestaurant">
                                <div class="sb-nav-link-icon"></div>
                                �\�U�޲z
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseRestaurant" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="dishbooking.html">�q��޲z</a>
                                    <a class="nav-link" href="#">�\�U������</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseSetting" aria-expanded="false" aria-controls="collapseSetting">
                                <div class="sb-nav-link-icon"></div>
                                �����]�w
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseSetting" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link" href="near.html">�P�䴺�I</a>
                                    <a class="nav-link" href="oneday.html">�@�鴺�I</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLogout" aria-expanded="false" aria-controls="collapseLogout">
                                <div class="sb-nav-link-icon"></div>
                                �n�X
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLogout" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link" href="error.html">�����ϥΪ�</a>
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
                <h1 class="dish">�I�\����</h1>
               	<form action=""></form>
                <div class="container">
	                <table id="cartTable" class="table table-hover container">
	                    <thead class="">
	                        <tr class="">
	                        	<th class="col-1" scope="col">�s��</th>
	                            <th class="col-4" scope="col">���a�W��</th>
	                            <th class="col-2" scope="col">���a���</th>
	                            <th class="col-3" scope="col">����</th>
	                            <th class="col-2" scope="col">�p�p</th>
	                        </tr>
	                    </thead>
	                    <tbody class="dishes_list">
<!-- 	                        <tr> -->
<!-- 	                            <th scope="row">1</th> -->
<!-- 	                            <td>XO�檣��</td> -->
<!-- 	                            <td class="selling-price number small-bold-red" -->
<!--               						style="padding-top: 1.1rem" -->
<!--               						data-bind="75"> -->
<!-- 	                            75 -->
<!-- 	                            </td> -->
<!-- 	                            <td> -->
<!-- 	                            	<div class="input-group input-group-sm"> -->
<!-- 						                <span class="input-group-addon minus">-</span> -->
<!-- 						                <input type="text" onfocus=this.blur() style="text-align:center; padding:3px;" class="number form-control input-sm" value="0"/> -->
<!-- 						                <span class="input-group-addon plus">+</span> -->
<!-- 						            </div> -->
<!-- 	                            </td> -->
<!-- 	                            <td class="subtotal number small-bold-red" -->
<!-- 					              style="padding-top: 1.1rem" -->
<!-- 					            ></td> -->
<!-- 	                        </tr> -->
<!-- 	                        <tr> -->
<!-- 	                            <th scope="row">2</th> -->
<!-- 	                            <td>�k���Ϫӱ�</td> -->
<!-- 	                            <td class="selling-price number small-bold-red" -->
<!--               						style="padding-top: 1.1rem" -->
<!--               						data-bind="200"> -->
<!-- 	                            200 -->
<!-- 	                            </td> -->
<!-- 	                            <td> -->
<!-- 									<div class="input-group input-group-sm"> -->
<!-- 						                <span class="input-group-addon minus">-</span> -->
<!-- 						                <input type="text" onfocus=this.blur() style="text-align:center; padding:3px;" class="number form-control input-sm" value="0"/> -->
<!-- 						                <span class="input-group-addon plus">+</span> -->
<!-- 						            </div> -->
<!-- 								</td> -->
<!-- 	                            <td class="subtotal number small-bold-red" -->
<!-- 					              style="padding-top: 1.1rem" -->
<!-- 					            ></td> -->
<!-- 	                        </tr> -->
<!-- 	                        <tr> -->
<!-- 	                            <th scope="row">3</th> -->
<!-- 	                            <td>�A�����M��</td> -->
<!-- 	                            <td class="selling-price number small-bold-red" -->
<!--               						style="padding-top: 1.1rem" -->
<!--               						data-bind="150"> -->
<!-- 	                            150 -->
<!-- 	                            </td> -->
<!-- 	                            <td> -->
<!-- 	                            	<div class="input-group input-group-sm"> -->
<!-- 						                <span class="input-group-addon minus">-</span> -->
<!-- 						                <input type="text" onfocus=this.blur() style="text-align:center; padding:3px;" class="number form-control input-sm" value="0"/> -->
<!-- 						                <span class="input-group-addon plus">+</span> -->
<!-- 						            </div> -->
<!-- 								</td> -->
<!-- 	                            <td class="subtotal number small-bold-red" -->
<!--               						style="padding-top: 1.1rem"> -->
<!-- 	                            </td> -->
<!-- 	                        </tr> -->
<!-- 	                        <tr> -->
<!-- 	                            <th scope="row">4</th> -->
<!-- 	                            <td>�ɶ����G</td> -->
<!-- 	                            <td class="selling-price number small-bold-red" -->
<!--               						style="padding-top: 1.1rem" -->
<!--               						data-bind="160"> -->
<!-- 	                            160 -->
<!-- 	                            </td> -->
<!-- 	                            <td> -->
<!-- 	                            	<div class="input-group input-group-sm"> -->
<!-- 						                <span class="input-group-addon minus">-</span> -->
<!-- 						                <input type="text" onfocus=this.blur() style="text-align:center; padding:3px;" class="number form-control input-sm" value="0"/> -->
<!-- 						                <span class="input-group-addon plus">+</span> -->
<!-- 						            </div> -->
<!-- 	                            </td> -->
<!-- 	                            <td class="subtotal number small-bold-red" -->
<!-- 					              style="padding-top: 1.1rem" -->
<!-- 					            ></td> -->
<!-- 	                        </tr> -->
<!-- 	                        <tr> -->
<!-- 	                            <th scope="row">5</th> -->
<!-- 	                            <td>�Q������n</td> -->
<!-- 	                            <td class="selling-price number small-bold-red" -->
<!--               						style="padding-top: 1.1rem" -->
<!--               						data-bind="250"> -->
<!-- 	                            250 -->
<!-- 	                            </td> -->
<!-- 	                            <td> -->
<!-- 	                            	<div class="input-group input-group-sm"> -->
<!-- 						                <span class="input-group-addon minus">-</span> -->
<!-- 						                <input type="text" onfocus=this.blur() style="text-align:center; padding:3px;" class="number form-control input-sm" value="0"/> -->
<!-- 						                <span class="input-group-addon plus">+</span> -->
<!-- 						            </div> -->
<!-- 	                            </td> -->
<!-- 	                            <td class="subtotal number small-bold-red" -->
<!-- 					              style="padding-top: 1.1rem" -->
<!-- 					            ></td> -->
<!-- 	                        </tr> -->
<!-- 	                        <tr> -->
<!-- 	                            <th scope="row">6</th> -->
<!-- 	                            <td>���Y������</td> -->
<!-- 	                            <td class="selling-price number small-bold-red" -->
<!--               						style="padding-top: 1.1rem" -->
<!--               						data-bind="200"> -->
<!-- 	                            200 -->
<!-- 	                            </td> -->
<!-- 	                            <td> -->
<!-- 	                            	<div class="input-group input-group-sm"> -->
<!-- 						                <span class="input-group-addon minus">-</span> -->
<!-- 						                <input type="text" onfocus=this.blur() style="text-align:center; padding:3px;" class="number form-control input-sm" value="0"/> -->
<!-- 						                <span class="input-group-addon plus">+</span> -->
<!-- 						            </div> -->
<!-- 	                            </td> -->
<!-- 	                            <td class="subtotal number small-bold-red" -->
<!-- 					              style="padding-top: 1.1rem" -->
<!-- 					            ></td> -->
<!-- 	                        </tr> -->
<!-- 	                        <tr> -->
<!-- 	                            <th scope="row">7</th> -->
<!-- 	                            <td>��u���G</td> -->
<!-- 	                            <td class="selling-price number small-bold-red" -->
<!--               						style="padding-top: 1.1rem" -->
<!--               						data-bind="40"> -->
<!-- 	                            40 -->
<!-- 	                            </td> -->
<!-- 	                            <td> -->
<!-- 	                            	<div class="input-group input-group-sm"> -->
<!-- 						                <span class="input-group-addon minus">-</span> -->
<!-- 						                <input type="text" onfocus=this.blur() style="text-align:center; padding:3px;" class="number form-control input-sm" value="0"/> -->
<!-- 						                <span class="input-group-addon plus">+</span> -->
<!-- 						            </div> -->
<!-- 	                            </td> -->
<!-- 	                            <td class="subtotal number small-bold-red" -->
<!-- 					              style="padding-top: 1.1rem" -->
<!-- 					            ></td> -->
<!-- 	                        </tr> -->
	                    </tbody>
	                </table>
					<div class="row">
				        <div class="col-md-12 col-lg-12 col-sm-12">
				          	<div class="cart-summary">
				            	<div style="margin-left: 1rem; margin-top: 0.4rem" class="pull-right total">
					              <label>���B�X�p:<span
					                  id="priceTotal"
					                  class="price-total large-bold-red"
					                  >0</span></label>
					            </div>
				          	</div>
				        </div>
				    </div>
	            </div>
	            <div class="buttons" style="text-align:center;">
	                <button type="button" id="btnConfirm" style="justify-content: center;" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">�T�{�e�X</button>
	             </div>   
	                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	                    <div class="modal-dialog">
	                        <div class="modal-content">
	                            <div class="modal-header">
	                                <h5 class="modal-title" id="exampleModalLabel">�T��</h5>
	                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                            </div>
	                                <div class="modal-body">
					                                    ���B�@ XXX����
	                                </div>
	                            <div class="modal-footer">
	                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">����</button>
	                                <a href="img/null2.jpg">
	                                    <button type="button" class="btn btn-primary">�T�w���b</button>
	                                </a>
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
        

        <script type="text/javascript">
        	$(document).ready(function() {
        		let buy_array = []; //������@�Ϊ���ư}�C
        		
        		
//        		 ==================== ���غc(��) ====================
   		    	var objArray = <%=new Gson().toJson(list) %>;
				$(objArray).each(function(index, item){
  					console.log(item.dishId+":"+item.dishName+":"+item.dishPrice);
  					$('.dishes_list').append(insertAppendData(item.dishId, item.dishName, item.dishPrice));
  				});
        		
        		var cartTable = $("#cartTable");
        		
//         		==================== �p�p(��) ====================
        		function getSubTotal(row) {
        	        var price = parseFloat($(row).find(".selling-price").data("bind"));
        	        var qty = parseInt($(row).find(":text").val());
        	        var result = price * qty;
        	        $(row).find(".subtotal").text(result).data("bind", result);
        	    };
        	    
//         	    ==================== �`�B ====================
        	    function getTotal() {
        	        var qtyTotal = 0;
        	        var priceTotal = 0;
        	        $(cartTable).find("tr:gt(0)").each(function() {
        	            getSubTotal(this);

       	                qtyTotal += parseInt($(this).find(":text").val());
       	                priceTotal += parseFloat($(this).find(".subtotal").data("bind"));

        	        });
        	        $("#priceTotal").text(priceTotal);
        	    };
        	    
        	    getTotal();
        		
//         	    ==================== �ƶq�p�� ====================
        		 $(cartTable).find("tr:gt(0)").each(function() {
       		        var input = $(this).find(":text");
       		        
       		        //���ƶq��J�زK�[�ƥ�A�p����B�p�p�A�ç�s�`�p
       		        $(input).keyup(function() {
       		            var val = parseInt($(this).val());
       		            if (isNaN(val) || (val < 0)) { $(this).val("0"); }
       		            getSubTotal($(this).parent().parent()); //tr element
       		            getTotal();
       		        });

       		        //���ƶq�վ���s�B�R���K�[�����ƥ�A�p����B�p�p�A�ç�s�`�p
       		        $(this).click(function() {
       		            var val = parseInt($(input).val());
       		            if (isNaN(val) || (val < 0)) { val = 0; }

       		         	let dish_id = $(this).closest("tr").find("#dish_id").text();
	             		console.log("dish_id:"+dish_id);
	             		let dish_price = $(this).closest("tr").find(".selling-price").data("bind");
	             		console.log("dish_price:"+dish_price);
       		            
       		            if ($(window.event.srcElement).hasClass("minus")) {
       		                if (val > 0) val--;
       		                input.val(val);
       		                
       		            	let dish_qty = $(this).closest("tr").find(":text").val();
		             		console.log("dish_qty:"+dish_qty); //���o��e���ƶq
       		                
// 		             		=============== �ʺA�ո˰}�C���� ===============
       		                if(val == 0){ //�ƶq���0�A�����Ӥ���
       		                	let findIndex = buy_array.findIndex((item => item.dish_id == dish_id));
       		                	if(findIndex >= 0){
       		                	 	buy_array.splice(findIndex, 1);
    		                	}
    		                	console.log("buy_array:"+JSON.stringify(buy_array));
       		                } else {
       		                	let findIndex = buy_array.findIndex((item => item.dish_id == dish_id))
    		                	if(findIndex >= 0){
    		                		buy_array[findIndex].dish_qty = dish_qty; //���Ӥ����ç�s�ƶq
    		                	}
    		                	console.log("buy_array:"+JSON.stringify(buy_array));
       		                }
// 		             		==============================
		             		
       		                getSubTotal(this);
       		            }
       		            else if ($(window.event.srcElement).hasClass("plus")) {
       		                if (val < 99) val++;
       		                input.val(val);
       		                
		             		let dish_qty = $(this).closest("tr").find(":text").val();
		             		console.log("dish_qty:"+dish_qty); //���o��e���ƶq
		             		
// 		             		=============== �ʺA�ո˰}�C���� ===============
       		             	if(val == 1){
    		                	let el_obj = {
    		                		"dish_id":dish_id,
    		                		"dish_price":dish_price,
    		                		"dish_qty":dish_qty
    		                	}
    		                	buy_array.push(el_obj);
    		                	console.log("buy_array:"+JSON.stringify(buy_array));
    		                } else { //val > 1
    		                	let findIndex = buy_array.findIndex((item => item.dish_id == dish_id));
    		                	if(findIndex >= 0){
    		                		buy_array[findIndex].dish_qty = dish_qty; //���Ӥ����ç�s�ƶq
    		                	}
    		                	console.log("buy_array:"+JSON.stringify(buy_array));
    		                }
//        		            ==============================
		             		
       		                getSubTotal(this);
       		            }
       		            getTotal();
       		        });
       		    });
       		    
				$("#btnConfirm").on("click", function(){
					let priceTotal = $("#priceTotal").text();
					let el_total = {
						"priceTotal": priceTotal
					}
					let el_mealId = {
						"meal_id": <%=meal_id%>
					}
					buy_array.push(el_total);
					buy_array.push(el_mealId);
					console.log("meal_id : " + el_mealId);
	                console.log("buy_array:"+JSON.stringify(buy_array));
	                
	                let sendObj = {
	                	"order_list":JSON.stringify(buy_array)
	                }
	                
	                $.ajax({
			            url: "${pageContext.request.contextPath}/order/OrderDetailServlet",
			            type: "POST",
			            data: sendObj,
			            dataType: "json",
			            success: function(data){
							console.log("OK");
							window.location.href="<%=request.getContextPath()%>/seat/seatmap.jsp";
			            },
			            error: function (xhr) {
			            	console.log("Err");
			            	window.location.href="<%=request.getContextPath()%>/seat/seatmap.jsp";
			            }
			        });
				});
        	});
        	
        	
        	function insertAppendData(dish_id, dish_name, dish_price){

    	        let li_html = `
    	        	<tr>
	                    <th scope="row" id="dish_id">\${dish_id}</th>
	                    <td>\${dish_name}</td>
	                    <td class="selling-price number small-bold-red"
	  						style="padding-top: 1.1rem"
	  						data-bind="\${dish_price}">
	  					\${dish_price}
	                    </td>
	                    <td>
	                    	<div class="input-group input-group-sm">
				                <span class="input-group-addon minus">-</span>
				                <input type="text" onfocus=this.blur() style="text-align:center; padding:3px;" class="number form-control input-sm" value="0"/>
				                <span class="input-group-addon plus">+</span>
				            </div>
	                    </td>
	                    <td class="subtotal number small-bold-red"
			              style="padding-top: 1.1rem"
			            ></td>
	                </tr>
    	        `;
    	        return li_html;
    	    }
        </script>
    </body>
</html>