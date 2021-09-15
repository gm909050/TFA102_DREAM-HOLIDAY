/*!
    * Start Bootstrap - SB Admin v7.0.2 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2021 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});
            
                    var imgFile="";
                        function imgChange(img) {
                            // 生成一个文件读取的对象
                            const reader = new FileReader();
                            reader.onload = function (ev) {
                                // base64码
                                imgFile =ev.target.result;//或e.target都是一样的
                                document.querySelector("img").src= ev.target.result;
                                console.log(imgFile);

                              }
                                        // 
                                reader.readAsDataURL(img.files[0]);
                                // });  
                        }

                     $("#btn_yes").on("click",function(){
                    	 
//                    	 console.log("11111");
//                    	 	
                    	 let input_text = $(".input_text").val();
                    	
                           if(input_text === "請選擇更換網頁第幾張圖"){
                               alert("請選擇圖片序號");
                               return;
                           }else if(imgFile === ""||imgFile === null){
                        	   alert("請選擇圖片");
                        	   return;
                           }else if($(".form-control_2").val()===""){
                        	   alert("請輸入景點名稱");
                        	   return;
                           }else if($(".form-control_3").val()===""){
                        	   alert("請輸入景點介紹");
                        	   return;
                           }else{
                        	   
                           
                           
                           
                            alert("傳送成功");
//                            let test={
//                              
//                                "picture" :imgFile,
//                                "picture_name" :$(".form-control_2").val(),
//                                "picture_explanation" :$(".form-control_3").val(),
//                                "picture_id" :$(".input_text").val()
//                            };
//                            console.log(test);
//                             $.ajax({
//                                url: "http://localhost:8081/Hotel/OneDayServlet",           // 資料請求的網址
//                                type: "POST",                  // GET | POST | PUT | DELETE | PATCH
//                                data: test,               // 傳送資料到指定的 url
//                                dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
//                                success: function(data){      // request 成功取得回應後執行
//                                	
//                                }
//                            });
                          }
                           
                       });
                  
                
    
            
        
