<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!-- TEST -->  

    
<!DOCTYPE html>
<html lang="UTF-8">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="Chrome" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>夢想假期首頁 - DreamHoliday Homepage</title>
        <link href="<%=request.getContextPath()%>/hpbasic/css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <style>
        *{
            margin: 0;
            padding: 0;
        }
     	header {
            background: linear-gradient(#2B3078, #0AA6B7);
            height: 170px;
        }
        .back1 {
            margin-left: 90%;
        }
        button#sidebarToggle{
            position: absolute;
            top: 50px;
            right: 10px;
            color: black;
        }
        button#sidebarToggle svg path{
            height: 80px;
        }
        a.nav-link{
            background-color: black;
            opacity: 90%;
            color: white;
            left: -20px;
            width: 100%;
        }        
        div.collapse nav a{
            width: 200px;
        }
        div.head{            
            position: absolute;
            width: 100%;
            z-index: 4;
        }
        div div.topmenu ul{
            margin-top: -24px;
            color: white;
            list-style: none;    
        }
        div div.topmenu ul li{
            display: none;
        }
        div div.topmenu ul li a{
            color: white;
            text-decoration:none;
        }
        ul li{
            vertical-align:auto
        }
        ul:hover li{
            background-color: #0AA6B7;
        }
        ul li:hover{
            background-color: gray;
        }
        
        h1{
            padding: 20px;            
            width: 100%;
        }
        h3{
            margin: 10px;           
            width: 100%;
            color: gray;
            font-style: italic;
        }
        footer{
            background:linear-gradient(#0AA6B7, #2B3078);    
            position: absolute;    
            width: 100%;
            clear:both;
            vertical-align: bottom;
            text-align: center;
        }
        footer #logo2{
            width: 100vh;
            display: inline-block;
        }
        footer div p{
            display: inline-block;
            font-size: 20px;
            color: white;
            margin: 10px 10px;
            text-align: center;
        }
        footer div{
            max-width: 1000px;
            margin: 10px auto;
            text-align: center;    
            position: relative;        
        } 
        footer div#sns{
            display: inline-block;      
            position: relative;     
        } 
        footer img{
            display: inline-block;
            margin: 10px 10px;
            vertical-align: bottom;
        }
        footer img.sns{
            width: 50px;
            height: 50px;
        }
        footer img.twitter{
            width: 55px;
            height: 50px;
        }
        footer img.fb{
            width: 50px;
            height: 50px;
        }
        footer img.yt{
            width: 80px;
            height: 50px;
        }
        div.main.row{
            margin: 30px;
            width: 100%;
        }
        div.main div{
            margin: 20px;
        }
        div.main2 div{
            margin: 20px;
        }
        div div img.card{
            max-width: 500px;
        }
        div.main2{
            display: none;
        }
        
        @media (min-width: 768px){
            button#sidebarToggle,#layoutSidenav{
                display: none;
            }
        }
        @media (max-width: 767.98px){
            div.head{
                display: none;
            }
        }
        @media (max-width: 1139.98px){
            div.main{
                display: none;
            }
        }
        @media (max-width: 1140px){
            div.main2{
                display:block;                
            }
        }
        </style>
        <script>
            $(document).ready(function(){

                $("ul#menu-1").mouseenter(function(){
                    $("#menu-1 li").toggle();
                })
                $("ul#menu-1").mouseleave(function(){
                    $("#menu-1 li").toggle();
                })

                $("ul#menu-2").mouseenter(function(){
                    $("#menu-2 li").toggle();
                })
                $("ul#menu-2").mouseleave(function(){
                    $("#menu-2 li").toggle();
                })

                $("ul#menu-3").mouseenter(function(){
                    $("#menu-3 li").toggle();
                })
                $("ul#menu-3").mouseleave(function(){
                    $("#menu-3 li").toggle();
                })

                $("ul#menu-4").mouseenter(function(){
                    $("#menu-4 li").toggle();
                })
                $("ul#menu-4").mouseleave(function(){
                    $("#menu-4 li").toggle();
                })

                $("ul#menu-5").mouseenter(function(){
                    $("#menu-5 li").toggle();
                })
                $("ul#menu-5").mouseleave(function(){
                    $("#menu-5 li").toggle();
                })

                $("ul#menu-6").mouseenter(function(){
                    $("#menu-6 li").toggle();
                })
                $("ul#menu-6").mouseleave(function(){
                    $("#menu-6 li").toggle();
                })            
            })       
        </script>
        
        

    </head>
    <body>
       <header class="container-fluid">
	        <div class="back1"><button type="button" class="btn btn-outline-secondary box1">管理員系統</button></div>
		        <div class="row justify-content-center">
		            <a class="col-auto" href="">
		                <img class="col-auto" src="<%=request.getContextPath()%>/images/logo1.png" alt="">
		            </a>
	       		 </div>
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>            
            <div id="layoutSidenav">
                <div id="layoutSidenav_nav">
                    <nav class="sb-sidenav accordion sb-sidenav-menu" id="sidenavAccordion">
                        <div class="sb-sidenav-menu">
                            <div class="nav">
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                    <div class="sb-nav-link-icon"></div>
                                    會員專區
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="memberlogin.html">會員登入</a>
                                        <a class="nav-link" href="membercreataccount.html">註冊帳號</a>
                                        <!-- <a class="nav-link" href="memberpassword.html">忘記密碼</a> -->
                                    </nav>
                                </div>
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                    <div class="sb-nav-link-icon"></div>
                                    場地租用
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                        <a class="nav-link" href="sitebookingmain.html">選擇場地</a>
                                    </nav>
                                </div>
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseRestaurant" aria-expanded="false" aria-controls="collapseRestaurant">
                                    <div class="sb-nav-link-icon"></div>
                                    線上訂房
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="collapseRestaurant" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="roombookingmain.html">選取日期</a>
                                        <a class="nav-link" href="showroom.html">房型介紹</a>
                                    </nav>
                                </div>
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseSetting" aria-expanded="false" aria-controls="collapseSetting">
                                    <div class="sb-nav-link-icon"></div>
                                    線上訂餐
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="collapseSetting" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                        <a class="nav-link" href="dishMain.jsp">線上預約</a>
                                        <a class="nav-link" href="dishmenu-1.jsp">菜色樣式</a>
                                    </nav>
                                </div>
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseMap" aria-expanded="false" aria-controls="collapseMap">
                                    <div class="sb-nav-link-icon"></div>
                                    周邊導覽
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="collapseMap" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                        <a class="nav-link" href="map.html">交通指南</a>
                                        <a class="nav-link" href="fontoneday.html">周邊景點</a>
                                        <a class="nav-link" href="fontoneday.html">一日景點</a>
                                    </nav>
                                </div>
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLogout" aria-expanded="false" aria-controls="collapseLogout">
                                    <div class="sb-nav-link-icon"></div>
                                    客戶服務
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="collapseLogout" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                        <a class="nav-link" href="error.html">聯絡我們</a>
                                        <a class="nav-link" href="faq.html" data-bs-toggle="modal" data-bs-target="#logout">FAQ</a>
                                    </nav>
                                </div>                           
                            </div>
                        </div>
                    </nav>
                </div>
            <div class="divforul col"></div>
        </header>
            <div class="head row justify-content-center">
                <div class="topmenu col-auto">                          
                    <ul id="menu-1">
                        <span class="member">會員專區</span> 
                      		 <li><a href="<%=request.getContextPath()%>/member/loginTest1.jsp">會員登入</a></li>
               				 <li><a href="<%=request.getContextPath()%>/member/MemberCreateAccount.jsp">註冊會員</a></li>
                         	 <li><a href="<%=request.getContextPath()%>/member/MemberList.jsp">會員資料</a></li>
                        <!-- <li><a href="memberpassword.html">忘記密碼</a></li> -->
                    </ul>       
                </div>       
                <div class="topmenu col-auto">     
                    <ul id="menu-2">
                        <span class="site">場地租用</span> 
                        <li><a href="sitebookingmain.html">選擇場地</a></li>
                    </ul>
                </div>      
                <div class="topmenu  col-auto">
                    <ul id="menu-3">
                        <span class="booking">線上訂房</span>
                        <li><a href="roombookingmain.html">選取日期</a></li>
                        <li><a href="showroom.html">房型介紹</a></li>
                    </ul>
                </div>
<!--                 <div class="topmenu  col-auto"> -->
<!--                     <ul id="menu-4"> -->
<!--                         <span class="restaurant">線上訂餐</span> -->
<!--                         <li><a href="dishmain.html">線上預約</a></li> -->
<!--                         <li><a href="dishmenu-1.html">菜色樣式</a></li> -->
<!--                     </ul> -->
<!--                 </div> -->
				<div class="topmenu  col-auto">
					<ul id="menu-4">
						<span class="restaurant">線上訂餐</span>
						<li><a href="<%=request.getContextPath()%>/dish/dishMain.jsp">線上預約</a></li>
						<li><a href="<%=request.getContextPath()%>/dish/dishmenu-1.jsp">菜色樣式</a></li>
					</ul>
				</div>
                <div class="topmenu  col-auto">
                    <ul id="menu-5">
                        <span class="map">周邊導覽</span>
                        <li><a href="<%=request.getContextPath()%>/map/map.html">交通指南</a></li>
                        <li><a href="<%=request.getContextPath()%>/nearby/nearby.jsp">周邊景點</a></li>
                        <li><a href="<%=request.getContextPath()%>/oneday/Oneday.html">一日景點</a></li>
                    </ul>       
                </div>         
                <div class="topmenu  col-auto">
                    <ul id="menu-6">
                        <span class="faq">客戶服務</span>
                        <li><a href="<%=request.getContextPath()%>/mail/mail.html">聯絡我們</a></li>
                        <li><a href="<%=request.getContextPath()%>/faq/FAQ.html">FAQ</a></li>
                    </ul>
                </div>
            </div>
            <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="<%=request.getContextPath()%>/images/hotelphoto.png" class="w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="<%=request.getContextPath()%>/images/hotel11.png" class="w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="<%=request.getContextPath()%>/images/hotel14.png" class="w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="<%=request.getContextPath()%>/images/hotel15.png" class="w-100" alt="...">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
            <h1 class="row justify-content-center">最新消息</h1>
            <h3 class="row justify-content-center">最新消息和優惠</h3>
            <div class="main container">
                <div class="row justify-content-center">
                    <img class="card col-10 col-lg-5" src="<%=request.getContextPath()%>/images/travel.jpg" alt="">
                    <div class="card" style="width: 24rem;">
                        <div class="card-body">
                            <h4 class="card-title">加入會員，享有獨家優惠</h4>
                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <a href="<%=request.getContextPath()%>/member/MemberCreateAccount.jsp" class="btn btn-primary">瞭解更多</a>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="card" style="width: 24rem;">
                        <div class="card-body">
                            <h4 class="card-title">出門旅遊，就是現在</h4>
                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <a href="<%=request.getContextPath()%>/oneday/Oneday.html" class="btn btn-primary">瞭解更多</a>
                        </div>
                    </div>
                    <img class="card col-10 col-lg-5" src="<%=request.getContextPath()%>/images/travel2.jpg" alt="">
                </div>
                <div class="row justify-content-center">
                    <img class="card col-10 col-lg-5" src="<%=request.getContextPath()%>/images/room-1.jpg" alt="">
                    <div class="card" style="width: 24rem;">
                        <div class="card-body">
                            <h4 class="card-title">休閒度假，最佳首選</h4>
                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <a href="http://localhost:8081/Photo_Sphere3DViewer/Room_Sphere3DView.html" class="btn btn-primary">瞭解更多</a>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="card" style="width: 24rem;">
                        <div class="card-body">
                            <h4 class="card-title">山珍海味，盡在夢想假期</h4>
                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <a href="<%=request.getContextPath()%>/dish/dishmenu-1.jsp" class="btn btn-primary">瞭解更多</a>
                        </div>
                    </div>
                    <img class="card col-10 col-lg-5" src="<%=request.getContextPath()%>/images/nicefood.jpg" alt="">
                </div>
            </div>
            <div class="main2 container">
                <div class="row justify-content-center">
                    <div class="card col-6" style="width: 24rem;">
                        <img src="img/travel.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">加入會員，享有獨家優惠</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="membercreataccount.html" class="btn btn-primary">瞭解更多</a>
                        </div>
                    </div>
                    <div class="card col-6" style="width: 24rem;">
                        <img src="img/travel2.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">出門旅遊，就是現在</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="roombookingmain.html" class="btn btn-primary">瞭解更多</a>
                        </div>
                    </div>
                    <div class="card col-6" style="width: 24rem;">
                        <img src="img/event.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">休閒度假，最佳首選</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="sitebookingmain.html" class="btn btn-primary">瞭解更多</a>
                        </div>
                    </div>
                    <div class="card col-6" style="width: 24rem;">
                        <img src="img/nicefood.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">山珍海味，盡在夢想假期</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="dishmain.html" class="btn btn-primary">瞭解更多</a>
                        </div>
                    </div>
                </div>
            </div>
            <footer>
                <a class="col-auto"href="">
                    <img class="col-auto" src="<%=request.getContextPath()%>/images/logo1.png" alt="">
                </a>
                <div id="sns">
                    <a href=""><img class="fb" src="<%=request.getContextPath()%>/images/Facebook_icon.svg.png" alt=""></a>
                    <a href=""><img class="sns" src="<%=request.getContextPath()%>/images/ig.png" alt=""></a>
                    <a href=""><img class="twitter" src="<%=request.getContextPath()%>/images/Twitter_bird_logo_2012.svg.png" alt=""></a>
                    <a href=""><img class="sns" src="<%=request.getContextPath()%>/images/line.png" alt=""></a>
                    <a href=""><img class="yt" src="<%=request.getContextPath()%>/images/youtube.svg.png" alt=""></a>
               </div>
                <div>           
                    <p>電話:02:2712-0689</p>        
                    <p>傳真:02:2712-0689</p>        
                    <p>信箱:tibame@gmail.com</p>        
                    <p>地址:台北市中山區南京東路三段219號5樓</p>
                </div>  
                <div class="container-fluid px-4">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div class="text-light">Copyright &copy; DreamHoliday Team5 Website 2021</div>                        
                    </div>
                </div>
            </footer>
            
            
     <script>
	    $(".box1").on('click', function () {
	        location.href = "../employee/login.jsp";
	    });
    </script>
            
            
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>



