package web.employee.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.employee.service.EmployeeService;
import web.employee.service.Verification;
import web.employee.vo.EmployeeVO;

@MultipartConfig
@WebServlet("/employee/EMPLOYEEPassWord")
public class EMPLOYEEPassWord extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html;charset=UTF-8");	 
        //對Post中文參數進行解碼
        req.setCharacterEncoding("UTF-8");
        res.addHeader("Access-Control-Allow-Origin", "*");
        
        
        List<String> errorMsgs = new LinkedList<String>();
        req.setAttribute("errorMsgs", errorMsgs);
        
        EmployeeVO empVO = new EmployeeVO();
        EmployeeService empsvce = new  EmployeeService();
        
        char array[] = new char[82];
        Integer emp_id = null;
        String emp_name = null;
        String id_number = null;
        String emp_phone = null;
        String emp_mail = null;
        String password =null;
        String id = "^[A-Z,a-z][1-2][0-9]{8}$";  
        
        emp_mail = req.getParameter("emp_mail");
        id_number = req.getParameter("id_number");
        
		
        empVO = empsvce.findByEMPMAIL(emp_mail);
        
        if(emp_mail.equals(null)||emp_mail.equals("")) {
        	errorMsgs.add("請輸入信箱");
        }
        
        if(id_number.equals(null)||id_number.equals("")) {
        	errorMsgs.add("請輸入身分證字號");
        }
        
        
        if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/employee/employeepassword.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		};
		
		if(!id_number.matches(id)) {
			errorMsgs.add("請確認身分證格式");
		}
        if (!errorMsgs.isEmpty()) {
        	req.setAttribute("empVO", empVO);
			RequestDispatcher failureView = req
					.getRequestDispatcher("/employee/employeepassword.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		};
		
        if(empVO == null) {
        	
        	errorMsgs.add("請確認信箱是否正確");
        }
        
        if (!errorMsgs.isEmpty()) {
        	req.setAttribute("empVO", empVO);
			RequestDispatcher failureView = req
					.getRequestDispatcher("/employee/employeepassword.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		};
      
		
        if(!empVO.getId_number().equals(id_number)) {
        	errorMsgs.add("請確認身分證是否正確");
        };
        
         
        if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/employee/employeepassword.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		};
		
		emp_id = empVO.getEmp_id();
        emp_name = empVO.getEmp_name();
        emp_phone = empVO.getEmp_phone();
        
        
        Verification ver = new Verification();
		StringBuffer strsb = ver.genAuthCode(array);
		password =strsb.toString();
		
		System.out.println(password.trim());
	//	
			String subject = "此為新密碼";
			String messageText = "管理員:" + emp_name +"您好"+"\n您的新密碼為"+password.trim()+"請使用此密碼登入後修改密碼";
					
			java.sql.Date hiredate = empVO.getHiredate();
//			
//			
			empsvce.updateEmp(emp_name, id_number, emp_phone, emp_mail, password.trim(), hiredate, emp_id);
			
			EMPLOYEEMailService tomail = new EMPLOYEEMailService();
			tomail.sendMail(emp_mail, subject, messageText);
			
			//創建成功 網頁導入登入畫面
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee/login.jsp");
					failureView.forward(req, res);
			
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
