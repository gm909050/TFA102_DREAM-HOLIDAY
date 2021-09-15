<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="web.nearby.*"%> 
<%@ page import="web.nearby.service.*"%> 
<%@ page import="web.nearby.vo.*"%> 
  
  
    
<%
	NEARBYService neaSvc = new NEARBYService();
	List<NEARBYVO> list = neaSvc.getAll();
	pageContext.setAttribute("list",list);
%> 

<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>





<!DOCTYPE html>
<html lang="UTF-8">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="Chrome" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>夢想假期首頁 - DreamHoliday Homepage</title>
        <link href="<%=request.getContextPath()%>/nearby/css/styles.css" rel="stylesheet" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <style>
        *{
            margin: 0;
            padding: 0;
        }
        body{
            background-color: #b4d8df;
           
        }
        header{
            background:linear-gradient(#2B3078, #0AA6B7);
            height: 125px;
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
        div div div.testdiv{
            height: 500px;
            border: 1px solid black;
            /* width: 80%; */
            margin: 20px auto;

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
        
        div.main2{
            display: none;
        }



        /* 網頁內容區塊 */
        .item a{
            font-size:20px;
            font-family: 'Allison', cursive;
        }
        .item p{
            font-size:25px;
            font-family: 'Allison', cursive;
        }
        .item h1{
            font-size:40px;
        }
        .item a:hover{
            color:red;
        }
        .container_1{
            margin-top:30px;
        }
        
        .testimg{
            width:550px;
            height:300px;

        }
        .testimg>img{
            width:100%;
            height:100%;

        }
        .footer_1{
                margin-top:5%;
        }  
        .h1_text1,.h1_text2,.h1_text3{
		  display: inline-block;
          font-family: 'Allison', cursive;
		}
        .oneday_logo{
            font-size:50px;
            font-family: 'Allison', cursive;
            
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
                
            var myCarousel = document.querySelector('.carousel')
            var carousel = new bootstrap.Carousel(myCarousel, {
                interval: 4000,
                wrap: false
            })

            var myCarousel = document.querySelector('#carouselExampleIndicators_3')
            var carousel = new bootstrap.Carousel(myCarousel, {
                interval: 4000,
                wrap: false
            })

            var myCarousel = document.querySelector('#carouselExampleIndicators_2')
            var carousel = new bootstrap.Carousel(myCarousel, {
                interval: 4000,
                
            })
         })       
        </script>
    </head>
    <body>
        <header class="container-fluid">
            <div class="row justify-content-center">
                <a class="col-auto"href="<%=request.getContextPath()%>/hpbasic/hpbasic.jsp">
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
                                        <a class="nav-link" href="<%=request.getContextPath()%>/DH_backsystem/login.jsp">會員登入</a>
                                        <a class="nav-link" href="<%=request.getContextPath()%>/DH_backsystem/register.jsp">註冊帳號</a>
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
                                        <a class="nav-link" href="dishmain.html">線上預約</a>
                                        <a class="nav-link" href="dishmenu-1.html">菜色樣式</a>
                                    </nav>
                                </div>
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseSetting" aria-expanded="false" aria-controls="collapseSetting">
                                    <div class="sb-nav-link-icon"></div>
                                    周邊導覽
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="collapseSetting" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                        <a class="nav-link" href="#">交通指南</a>
                                        <a class="nav-link" href="#">周邊景點</a>
                                        <a class="nav-link" href="#">一日景點</a>
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
                                        <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#logout">FAQ</a>
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
                <div class="topmenu  col-auto">
                    <ul id="menu-4">
                        <span class="restaurant">線上訂餐</span>
                        <li><a href="dishmain.html">線上預約</a></li>
                        <li><a href="dishmenu-1.html">菜色樣式</a></li>
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
            

             <!-- 網頁內容區塊 -->

             <div class="container"> 
                <div class="row"> 
                    <div class="col-12"> 
                        <div class="oneday_logo d-flex justify-content-center ">附 近 景 點</div> 
                    </div> 
                </div> 
            </div> 

            

            <c:forEach var="nearbyVO" items="${list}" begin="<%=0%>" end="<%=0%>">   
    
            <div class="container container_1">
                <div class="row">
                    <div class="col-xl-6 col-12 col-sm-6"> 
                        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-indicators">
                                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                            </div>
                            <div class="carousel-inner">
                            <div class="carousel-item active testimg">
                                <img src="/Hotel_Team5_TFA102/NEARBYReader?id=1" class="d-block w-100"  alt="...">
                            </div>
                            <div class="carousel-item testimg">
                                <img src="/Hotel_Team5_TFA102/NEARBYReader?id=2" class="d-block w-100" alt="...">
                            </div>
                            <div class="carousel-item testimg">
                                <img src="/Hotel_Team5_TFA102/NEARBYReader?id=3" class="d-block w-100" alt="...">
                            </div>
                            </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                    </button>
                                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                        </div>
                   </div>
        
                   <div class="col-xl-6 col-12 col-sm-6 item" >
                        <div><h1 class="h1_text1">${nearbyVO.nearbyname}</h1><a href="https://goo.gl/maps/kWamehUttBXsrAG58">地圖連結</a></div>
                        	<p class="p_text1">${nearbyVO.nearbyexplanation}
                        </p>  
                   </div>
                </div>   
            </div>
        </c:forEach>
           
        
          
         <c:forEach var="nearbyVO" items="${list}" begin="<%=3%>" end="<%=3%>"> 
            <div class="container">
                <div class="row">
                    <div class="col-xl-6 col-12 col-sm-6 item"> 
                        <div><h1 class="h1_text2">${nearbyVO.nearbyname}</h1><a href="https://goo.gl/maps/94hX9ys6bVadymgV6">地圖連結</a></div>
                        <p class="p_text2">${nearbyVO.nearbyexplanation}
                            
                        </p>  
                   </div>
        
                   <div class="col-xl-6 col-12 col-sm-6">
                    <div id="carouselExampleIndicators_3" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-indicators">
                            <button type="button" data-bs-target="#carouselExampleIndicators_3" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                            <button type="button" data-bs-target="#carouselExampleIndicators_3" data-bs-slide-to="1" aria-label="Slide 2"></button>
                            <button type="button" data-bs-target="#carouselExampleIndicators_3" data-bs-slide-to="2" aria-label="Slide 3"></button>
                        </div>
                        <div class="carousel-inner">
                        <div class="carousel-item active testimg">
                            <img src="/Hotel_Team5_TFA102/NEARBYReader?id=4" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item testimg">
                            <img src="/Hotel_Team5_TFA102/NEARBYReader?id=5" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item testimg">
                            <img src="/Hotel_Team5_TFA102/NEARBYReader?id=6" class="d-block w-100" alt="...">
                        </div>
                        </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators_3" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators_3" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                    </div>
                   </div>
                </div>   
            </div>
        </c:forEach> 
        
        
        
         <c:forEach var="nearbyVO" items="${list}" begin="<%=6%>" end="<%=6%>"> 
             <div class="container">
                <div class="row">
                    <div class="col-xl-6 col-12 col-sm-6"> 
                        <div id="carouselExampleIndicators_2" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-indicators">
                                <button type="button" data-bs-target="#carouselExampleIndicators_2" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                                <button type="button" data-bs-target="#carouselExampleIndicators_2" data-bs-slide-to="1" aria-label="Slide 2"></button>
                                <button type="button" data-bs-target="#carouselExampleIndicators_2" data-bs-slide-to="2" aria-label="Slide 3"></button>
                            </div>
                            <div class="carousel-inner">
                            <div class="carousel-item active testimg">
                                <img src="/Hotel_Team5_TFA102/NEARBYReader?id=7" class="d-block w-100" alt="...">
                            </div>
                            <div class="carousel-item testimg">
                                <img src="/Hotel_Team5_TFA102/NEARBYReader?id=8" class="d-block w-100" alt="...">
                            </div>
                            <div class="carousel-item testimg">
                                <img src="/Hotel_Team5_TFA102/NEARBYReader?id=9" class="d-block w-100" alt="...">
                            </div>
                            </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators_2" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                    </button>
                                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators_2" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                        </div>
                   </div>
        
        
                   <div class="col-xl-6 col-12 col-sm-6 item" >
                        <div><h1 class="h1_text3">${nearbyVO.nearbyname}</h1><a href="https://goo.gl/maps/1PfwZ3on3fJcCyPj6">地圖連結</a></div>
                        <p class="p_text3">${nearbyVO.nearbyexplanation}
                            
                        </p>  
                   </div>
                </div>   
            </div>
         </c:forEach>






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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
