package web.employee.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.employee.service.EmployeeService;
import web.employee.vo.EmployeeVO;
@WebServlet("/empSelfServlet")
public class empSelfServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");

		req.setCharacterEncoding("UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		String action = req.getParameter("action");
		if ("selfupdate".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer emp_id = new Integer(req.getParameter("emp_id").trim());
				
				String emp_name = req.getParameter("emp_name");
				
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				} else if(!emp_name.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				String id_number = req.getParameter("id_number");
				
				if (id_number == null|| id_number.trim().length() == 0) {
					errorMsgs.add("���u�s��: �ФŪť�");
				}
				String emp_phone = req.getParameter("emp_phone").trim();
				
				if(emp_phone == null|| emp_phone.trim().length() == 0) {
					errorMsgs.add("���u�q��: �ФŪť�");
				}
				String emp_mail = req.getParameter("emp_mail").trim();
				
				if(emp_mail == null|| emp_mail.trim().length() == 0) {
					errorMsgs.add("���u�H�c: �ФŪť�");
				}
				String password = req.getParameter("password").trim();
				
				if(password == null|| password.trim().length() == 0) {
					errorMsgs.add("���u�K�X: �ФŪť�");
				}
				String confirm_password = req.getParameter("confirm_password");
				
				if(!confirm_password.equals(password)) {
					errorMsgs.add("�нT�{�z��J���T�{�K�X�P�K�X�O�_�@�P");
				}
				java.sql.Date hiredate = null;
				try {
					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
				} catch (IllegalArgumentException e) {
					hiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				EmployeeVO empVO = new EmployeeVO();
				empVO.setEmp_name(emp_name);
				empVO.setId_number(id_number);
				empVO.setEmp_phone(emp_phone);
				empVO.setEmp_mail(emp_mail);
				empVO.setPassword(password);
				empVO.setHiredate(hiredate);
				empVO.setEmp_id(emp_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/employee/employee_changedata.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				EmployeeService empSvc = new EmployeeService();
				empVO = empSvc.updateEmp(emp_name, id_number, emp_phone, emp_mail, password, hiredate, emp_id);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/employee/login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���" );
				RequestDispatcher failureView = req.getRequestDispatcher("/employee/employee_changedata.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
