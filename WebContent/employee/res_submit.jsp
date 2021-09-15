<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>後台系統 - 訂房頁面 - DreamHoliday Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/employee/css/styles.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
        crossorigin="anonymous"></script>
    <style>
        nav button#sidebarToggle {
            position: absolute;
            right: 10px;
        }


        .wrapper {
            min-height: 100%;
            margin-bottom: -68.8px;
        }

        .range {
            width: 90%;
            position: relative;
            left: 5%;
            padding-bottom: 68.8px;
        }

        textarea {
            resize: none;
            height: 200px;
        }

        .sub {
            position: relative;
            left: x;
            bottom: 0;
        }

        div.text-muted {
            margin: 0 auto;
            display: block;
            color: white !important;
        }

        .obj {
            width: 200px;
            height: 200px;
        }

        .bk {
            height: 10px;
        }

        .uploadImage {
            display: inline-block;
            vertical-align: top;
            position: relative;
            width: 90px;
            height: 90px;
            background: url("../點選上傳.png") no-repeat;
            background-size: cover;
            text-align: center;
            cursor: pointer;
        }

        .uploadImage p {
            position: absolute;
            left: 0;
            right: 0;
            bottom: 10px;
            font-size: 14px;
            color: #999999;
        }

        .uploadImage input#file {
            width: 100%;
            height: 100%;
            opacity: 0;
            cursor: pointer;
        }

        .preview {
            position: relative;
            display: inline-block;
            vertical-align: top;
            margin-left: 10px;
            width: 90px;
            height: 90px;
            background: #E1E6ED;
            text-align: center;
        }

        .preview img {
            position: relative;
            z-index: 1;
            width: 100%;
            height: 100%;
        }

        .preview img[src=""] {
            opacity: 0;
            filter: Alpha(0);
            /* 相容IE8-9 */
        }

        .preview img:not([src]) {
            opacity: 0;
            filter: Alpha(0);
            /* 相容IE8-9 */
        }

        .preview .word {
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            line-height: 90px;
            font-size: 14px;
            color: #999999;
            z-index: 0;
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
        
        <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i
                class="fas fa-bars"></i></button>
        
        <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
            <div class="input-group">
            </div>
        </form>
    </nav>
    <div id="layoutSidenav">
        <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
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
                                <a class="nav-link" href="employee_management.jsp">員工管理</a>
                                
                            </nav>
                        </div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                            data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"></div>
                            客房管理
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
                            data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="room_submit.jsp">客房上架</a>
                                <a class="nav-link" href="roombooking_management.jsp">客房管理</a>
                            </nav>
                        </div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages"
                            aria-expanded="false" aria-controls="collapsePages">
                            <div class="sb-nav-link-icon"></div>
                            場地管理
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapsePages" aria-labelledby="headingTwo"
                            data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                <a class="nav-link" href="place_submit.jsp">場地上架</a>
                                <a class="nav-link" href="placebooking_management.jsp">場地管理</a>
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
                            data-bs-target="#collapseLogout" aria-expanded="false" aria-controls="collapseLogout">
                            <div class="sb-nav-link-icon"></div>
                            登出
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLogout" aria-labelledby="headingTwo"
                            data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#logout">登出</a>
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
            <div class="modal fade" id="logout" tabindex="-1" aria-labelledby="logoutLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="logoutLabel">系統訊息</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            確定登出嗎?
                        </div>
                        <div class="back modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">返回</button>
                            <button type="button" class="btn btn-primary">
                                <a class="backlogin" href="login.jsp">登出</a>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class='wrapper'>
                <div class='range'>
                    <form>
                        <h1>新增菜餚</h1><br>
                        <h4>基本資訊</h4>


                        <div class="input-group mb-3">
                            <!-- <label class="input-group-text" for="inputGroupFile01">上傳圖片</label> -->
                            <input type="file" class="form-control upload_zone" id="file"
                                accept="image/png, image/jpeg, image/gif, image/jpg" />
                        </div>
                        <div class="preview">
                            <img src="" name="dish_picture"id="look1">
                            <p class="word">封面圖</p>
                        </div>
                        <div class="preview">
                            <img src="" id="look2">
                            <p class="word">圖片1</p>
                        </div>
                        <div class="preview">
                            <img src="" id="look3">
                            <p class="word">圖片2</p>
                        </div>
                        <div class="preview">
                            <img src="" id="look4">
                            <p class="word">圖片3</p>
                        </div>
                        



                        <div class='bk'></div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">菜餚名稱</span>
                            <input type="text" class="form-control" placeholder='請輸入菜餚名稱..'
                                aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                        </div>
                        
                        <div class="text input-group">
                            <span class="input-group-text ">菜餚敘述</span>
                            <textarea id="form-control_3" name="dish_intro"class="form-control form-control_3" placeholder='請輸入菜餚敘述..'
                                aria-label=""></textarea>
                        </div>
                        <h4>銷售資訊</h4>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">價格</span>
                            <input type="text" class="form-control" placeholder='請輸入價格..'
                                aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">菜餚數量</span>
                            <input type="text" class="form-control" placeholder='請輸入數量..'
                                aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                        </div>
                       
                        <div class="sub">
                            <button type="button" class="btn btn-outline-primary cancel">取消</button>
                            <button type="button" class="btn btn-outline-primary save">儲存並下架</button>
                            <button type="button" class="btn btn-outline-primary submit">儲存並上架</button>
                        </div>
                        <div class='bk'></div>
                    </form>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath()%>/employee/js/scripts.js"></script>
    <script src="<%=request.getContextPath()%>/employee/js/jquery-3.6.0.min.js"></script>
    <script>
        var hasUploadedOne = false;// 已上傳過1張圖片
        var hasUploadedTwo = false;// 已上傳過2張圖片
        var hasUploadedThree = false;// 已上傳過3張圖片
        var hasUploadedFour = false;// 已上傳過4張圖片
        //獲取到預覽框
        var imgObjPreview1 = document.getElementById("look1");
        var imgObjPreview2 = document.getElementById("look2");
        var imgObjPreview3 = document.getElementById("look3");
        var imgObjPreview4 = document.getElementById("look4");

        document.getElementById('file').onchange = function () {
            // 若還沒完成2張圖片的上傳
            if (!hasUploadedFour) {
                //獲取到file的檔案
                var docObj = this;

                //獲取到檔名和型別（非IE，可一次上傳1張或多張）
                if (docObj.files && docObj.files[0]) {
                    // 一次上傳了>=2張圖片（只有前兩張會真的上傳上去）
                    // if (docObj.files.length >= 4) {
                    //     imgObjPreview1.src = window.URL.createObjectURL(docObj.files[0]);
                    //     imgObjPreview2.src = window.URL.createObjectURL(docObj.files[1]);
                    //     imgObjPreview3.src = window.URL.createObjectURL(docObj.files[2]);
                    //     imgObjPreview4.src = window.URL.createObjectURL(docObj.files[3]);
                    //     hasUploadedFour = true;
                    // }
                    //一次只上傳了1張照片
                    // else {
                    // 這是上傳的第一張照片
                    if (hasUploadedOne == false) {
                        imgObjPreview1.src = window.URL.createObjectURL(docObj.files[0]);
                        hasUploadedOne = true;
                    }
                    // 這是上傳的第二張照片
                    else if (hasUploadedTwo == false) {
                        imgObjPreview2.src = window.URL.createObjectURL(docObj.files[0]);
                        hasUploadedTwo = true;
                    } else if (hasUploadedThree == false) {
                        imgObjPreview3.src = window.URL.createObjectURL(docObj.files[0]);
                        hasUploadedThree = true;
                    } else if (hasUploadedFour == false) {
                        imgObjPreview4.src = window.URL.createObjectURL(docObj.files[0]);
                        hasUploadedFour = true;
                    }
                   

                }
                
            }
        }


        $(".cancel").on('click', function () {
            location.href = "/index.html";
        });
        $(".save").on('click', function () {
            location.href = "/resbooking_management.html";
        });
        $(".submit").on('click', function () {
            location.href = "/resbooking_management.html";
        });
        


    </script>
</body>
</html>