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
    <title>��x�t�� - �q�Э��� - DreamHoliday Admin</title>
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
            background: url("../�I��W��.png") no-repeat;
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
            /* �ۮeIE8-9 */
        }

        .preview img:not([src]) {
            opacity: 0;
            filter: Alpha(0);
            /* �ۮeIE8-9 */
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
        
        <a class="navbar-brand ps-3" href="#"><i class="fas fa-user fa-fw"></i>�޲z�H������</a>
        
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
                            ���u�޲z
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayouts2" aria-labelledby="headingOne"
                            data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="employee_data.jsp">�ӤH���</a>
                                <a class="nav-link" href="employee_changedata.jsp">�ӤH��ƭק�</a>
                                <a class="nav-link" href="employee_management.jsp">���u�޲z</a>
                                
                            </nav>
                        </div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                            data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"></div>
                            �ȩк޲z
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
                            data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="room_submit.jsp">�ȩФW�[</a>
                                <a class="nav-link" href="roombooking_management.jsp">�ȩк޲z</a>
                            </nav>
                        </div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages"
                            aria-expanded="false" aria-controls="collapsePages">
                            <div class="sb-nav-link-icon"></div>
                            ���a�޲z
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapsePages" aria-labelledby="headingTwo"
                            data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                <a class="nav-link" href="place_submit.jsp">���a�W�[</a>
                                <a class="nav-link" href="placebooking_management.jsp">���a�޲z</a>
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
                            data-bs-target="#collapseLogout" aria-expanded="false" aria-controls="collapseLogout">
                            <div class="sb-nav-link-icon"></div>
                            �n�X
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLogout" aria-labelledby="headingTwo"
                            data-bs-parent="#sidenavAccordion">
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
                        <div class="back modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">��^</button>
                            <button type="button" class="btn btn-primary">
                                <a class="backlogin" href="login.jsp">�n�X</a>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class='wrapper'>
                <div class='range'>
                    <form>
                        <h1>�s�W���a</h1><br>
                        <h4>�򥻸�T</h4>


                        <div class="input-group mb-3">
                            <!-- <label class="input-group-text" for="inputGroupFile01">�W�ǹϤ�</label> -->
                            <input type="file" class="form-control upload_zone" id="file"
                                accept="image/png, image/jpeg, image/gif, image/jpg" />
                        </div>
                        <div class="preview">
                            <img src="" name="dish_picture"id="look1">
                            <p class="word">�ʭ���</p>
                        </div>
                        <div class="preview">
                            <img src="" id="look2">
                            <p class="word">�Ϥ�1</p>
                        </div>
                        <div class="preview">
                            <img src="" id="look3">
                            <p class="word">�Ϥ�2</p>
                        </div>
                        <div class="preview">
                            <img src="" id="look4">
                            <p class="word">�Ϥ�3</p>
                        </div>
                        



                        <div class='bk'></div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">���a�W��</span>
                            <input type="text" class="form-control" placeholder='�п�J���a�W��..'
                                aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                        </div>
                        
                        <div class="text input-group">
                            <span class="input-group-text ">���a�ԭz</span>
                            <textarea id="form-control_3" name="dish_intro"class="form-control form-control_3" placeholder='�п�J���a�ԭz..'
                                aria-label=""></textarea>
                        </div>
                        <h4>�P���T</h4>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">����</span>
                            <input type="text" class="form-control" placeholder='�п�J����..'
                                aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">���a�ƶq</span>
                            <input type="text" class="form-control" placeholder='�п�J�ƶq..'
                                aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                        </div>
                       
                        <div class="sub">
                            <button type="button" class="btn btn-outline-primary cancel">����</button>
                            <button type="button" class="btn btn-outline-primary save">�x�s�äU�[</button>
                            <button type="button" class="btn btn-outline-primary submit">�x�s�äW�[</button>
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
        var hasUploadedOne = false;// �w�W�ǹL1�i�Ϥ�
        var hasUploadedTwo = false;// �w�W�ǹL2�i�Ϥ�
        var hasUploadedThree = false;// �w�W�ǹL3�i�Ϥ�
        var hasUploadedFour = false;// �w�W�ǹL4�i�Ϥ�
        //�����w����
        var imgObjPreview1 = document.getElementById("look1");
        var imgObjPreview2 = document.getElementById("look2");
        var imgObjPreview3 = document.getElementById("look3");
        var imgObjPreview4 = document.getElementById("look4");

        document.getElementById('file').onchange = function () {
            // �Y�٨S����2�i�Ϥ����W��
            if (!hasUploadedFour) {
                //�����file���ɮ�
                var docObj = this;

                //������ɦW�M���O�]�DIE�A�i�@���W��1�i�Φh�i�^
                if (docObj.files && docObj.files[0]) {
                    // �@���W�ǤF>=2�i�Ϥ��]�u���e��i�|�u���W�ǤW�h�^
                    // if (docObj.files.length >= 4) {
                    //     imgObjPreview1.src = window.URL.createObjectURL(docObj.files[0]);
                    //     imgObjPreview2.src = window.URL.createObjectURL(docObj.files[1]);
                    //     imgObjPreview3.src = window.URL.createObjectURL(docObj.files[2]);
                    //     imgObjPreview4.src = window.URL.createObjectURL(docObj.files[3]);
                    //     hasUploadedFour = true;
                    // }
                    //�@���u�W�ǤF1�i�Ӥ�
                    // else {
                    // �o�O�W�Ǫ��Ĥ@�i�Ӥ�
                    if (hasUploadedOne == false) {
                        imgObjPreview1.src = window.URL.createObjectURL(docObj.files[0]);
                        hasUploadedOne = true;
                    }
                    // �o�O�W�Ǫ��ĤG�i�Ӥ�
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