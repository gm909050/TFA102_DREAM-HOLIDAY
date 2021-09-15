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
import web.employee.vo.EmployeeVO;

@MultipartConfig
@WebServlet("/employee/EmployeeServlet2")
public class EmployeeServlet2 extends HttpServlet {
	private static final long serialVersionUID = 77777L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		// ��Post����Ѽƶi��ѽX
		req.setCharacterEncoding("UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");



		String action = req.getParameter("action");
		
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				EmployeeVO empVO = new EmployeeVO();
				EmployeeService empsve = new EmployeeService();

				String emp_mail = req.getParameter("emp_mail");
				String password = req.getParameter("password");
				empVO = empsve.findByEMPMAIL(emp_mail);
				if (emp_mail.equals("") || emp_mail.equals(null)) {
					errorMsgs.add("�п�J�H�c");
				}

				if (password.equals("") || password.equals(null)) {
					errorMsgs.add("�п�J�K�X");
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee/login.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				};
				if (empVO == null) {
					errorMsgs.add("�нT�{�H�c���T");
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee/login.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				};
				if(!empVO.getPassword().equals(password)) {
					errorMsgs.add("�нT�{�K�X���T");
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee/login.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				};
				req.getSession().setAttribute("empVO", empVO);
				String locationPath = (String) req.getSession().getAttribute("location");
				
				if (locationPath != null) {
					locationPath = (locationPath.length() == 0) ? req.getContextPath() : locationPath;
					
					res.sendRedirect(res.encodeRedirectURL(locationPath));
					System.out.println("locationPath"+locationPath);
					return;
				} else {
					String url = "/employee/select_page.jsp";
					res.sendRedirect(req.getContextPath()+url);
				}
//				String url = "/employee/select_page.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}

			
			
		}
//		doGet(req, res);
	}

}
