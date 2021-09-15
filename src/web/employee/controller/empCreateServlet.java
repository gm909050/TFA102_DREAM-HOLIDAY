package web.employee.controller;

import java.io.IOException;
import java.util.Date;
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
@WebServlet("/employee/empCreateServlet")
public class empCreateServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doGet(req, res);
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		
		EmployeeVO empVO = new EmployeeVO();
		EmployeeService empSvc = new EmployeeService();
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		String emp_mail = req.getParameter("emp_mail");
		empVO = empSvc.findByEMPMAIL(emp_mail);
		
		String action = req.getParameter("action");

		if ("create".equals(action)) {
			

//			try {
				String emp_name = req.getParameter("emp_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!emp_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String idNumberReg = "[(a-zA-Z0-9_)]{10}$";
				String id_number = req.getParameter("id_number");
				if (id_number == null || id_number.trim().length() == 0) {
					errorMsgs.add("身分證字號: 請勿空白");
				}else if(!id_number.trim().matches(idNumberReg)){
					errorMsgs.add("身分證:只能是數字和英文.且長度必須是10位");
				}
				
				String phoneReg = "^09[0-9]{8}$";
				String emp_phone = req.getParameter("emp_phone");
				if (emp_phone == null || emp_phone.trim().length() == 0) {
					errorMsgs.add("員工電話: 請勿空白");
				}else if(!emp_phone.trim().matches(phoneReg)) {
					errorMsgs.add("電話:只能數字,且長度必須是10");
				}
				
				if(empVO!=null) {					
					if(empVO.getEmp_mail().equals(emp_mail)) {
						errorMsgs.add("員工郵件信箱:有人使用過");
					}
				}else {
					empVO = new EmployeeVO();
				}
				if(emp_mail==null|| emp_mail.trim().length() == 0) {
						errorMsgs.add("員工郵件信箱: 請勿空白");
				}
				String password = req.getParameter("password");
				String passwordReg = "[(a-zA-Z0-9_)]{5,19}$";
				if (password == null || password.trim().length() == 0) {
					errorMsgs.add("員工密碼: 請勿空白");
				}else if(!password.trim().matches(passwordReg)) {
					errorMsgs.add("密碼:只能數字和英文,且長度必須是6到20");
				}
				String confirm_password = req.getParameter("confirm_password");
				if(confirm_password==null||confirm_password.trim().length()==0) {
					errorMsgs.add("確認密碼:請勿空白");
				}
				if(!confirm_password.equals(password)) {
					errorMsgs.add("請確認您輸入的確認密碼與密碼是否一致");
				}
				Date utilDate = new Date();
				java.sql.Date hiredate = new java.sql.Date(utilDate.getTime());

				
				empVO.setEmp_name(emp_name);
				empVO.setId_number(id_number);
				empVO.setEmp_phone(emp_phone);
				empVO.setEmp_mail(emp_mail);
				empVO.setPassword(password);
				empVO.setHiredate(hiredate);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee/EmployeeCreateAccount.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				
				empSvc.addEmp(emp_name, id_number, emp_phone, emp_mail, password, hiredate);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/employee/login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交login.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} 
//			catch (Exception e) {
//				errorMsgs.add("請輸入正確資料");
//				RequestDispatcher failureView = req.getRequestDispatcher("/employee/EmployeeCreateAccount.jsp");
//				failureView.forward(req, res);
			}
//		}
//	}
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//	
//		
//	}
}