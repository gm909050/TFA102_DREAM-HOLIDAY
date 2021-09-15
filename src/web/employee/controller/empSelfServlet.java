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
		if ("selfupdate".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer emp_id = new Integer(req.getParameter("emp_id").trim());
				
				String emp_name = req.getParameter("emp_name");
				
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!emp_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String id_number = req.getParameter("id_number");
				
				if (id_number == null|| id_number.trim().length() == 0) {
					errorMsgs.add("員工編號: 請勿空白");
				}
				String emp_phone = req.getParameter("emp_phone").trim();
				
				if(emp_phone == null|| emp_phone.trim().length() == 0) {
					errorMsgs.add("員工電話: 請勿空白");
				}
				String emp_mail = req.getParameter("emp_mail").trim();
				
				if(emp_mail == null|| emp_mail.trim().length() == 0) {
					errorMsgs.add("員工信箱: 請勿空白");
				}
				String password = req.getParameter("password").trim();
				
				if(password == null|| password.trim().length() == 0) {
					errorMsgs.add("員工密碼: 請勿空白");
				}
				String confirm_password = req.getParameter("confirm_password");
				
				if(!confirm_password.equals(password)) {
					errorMsgs.add("請確認您輸入的確認密碼與密碼是否一致");
				}
				java.sql.Date hiredate = null;
				try {
					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
				} catch (IllegalArgumentException e) {
					hiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
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
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/employee/employee_changedata.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				EmployeeService empSvc = new EmployeeService();
				empVO = empSvc.updateEmp(emp_name, id_number, emp_phone, emp_mail, password, hiredate, emp_id);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/employee/login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" );
				RequestDispatcher failureView = req.getRequestDispatcher("/employee/employee_changedata.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
