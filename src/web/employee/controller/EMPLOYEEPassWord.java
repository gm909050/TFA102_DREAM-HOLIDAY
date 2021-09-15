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
        //��Post����Ѽƶi��ѽX
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
        	errorMsgs.add("�п�J�H�c");
        }
        
        if(id_number.equals(null)||id_number.equals("")) {
        	errorMsgs.add("�п�J�����Ҧr��");
        }
        
        
        if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/employee/employeepassword.jsp");
			failureView.forward(req, res);
			return;//�{�����_
		};
		
		if(!id_number.matches(id)) {
			errorMsgs.add("�нT�{�����Ү榡");
		}
        if (!errorMsgs.isEmpty()) {
        	req.setAttribute("empVO", empVO);
			RequestDispatcher failureView = req
					.getRequestDispatcher("/employee/employeepassword.jsp");
			failureView.forward(req, res);
			return;//�{�����_
		};
		
        if(empVO == null) {
        	
        	errorMsgs.add("�нT�{�H�c�O�_���T");
        }
        
        if (!errorMsgs.isEmpty()) {
        	req.setAttribute("empVO", empVO);
			RequestDispatcher failureView = req
					.getRequestDispatcher("/employee/employeepassword.jsp");
			failureView.forward(req, res);
			return;//�{�����_
		};
      
		
        if(!empVO.getId_number().equals(id_number)) {
        	errorMsgs.add("�нT�{�����ҬO�_���T");
        };
        
         
        if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/employee/employeepassword.jsp");
			failureView.forward(req, res);
			return;//�{�����_
		};
		
		emp_id = empVO.getEmp_id();
        emp_name = empVO.getEmp_name();
        emp_phone = empVO.getEmp_phone();
        
        
        Verification ver = new Verification();
		StringBuffer strsb = ver.genAuthCode(array);
		password =strsb.toString();
		
		System.out.println(password.trim());
	//	
			String subject = "�����s�K�X";
			String messageText = "�޲z��:" + emp_name +"�z�n"+"\n�z���s�K�X��"+password.trim()+"�ШϥΦ��K�X�n�J��ק�K�X";
					
			java.sql.Date hiredate = empVO.getHiredate();
//			
//			
			empsvce.updateEmp(emp_name, id_number, emp_phone, emp_mail, password.trim(), hiredate, emp_id);
			
			EMPLOYEEMailService tomail = new EMPLOYEEMailService();
			tomail.sendMail(emp_mail, subject, messageText);
			
			//�Ыئ��\ �����ɤJ�n�J�e��
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee/login.jsp");
					failureView.forward(req, res);
			
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
