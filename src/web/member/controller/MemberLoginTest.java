package web.member.controller;

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
import javax.servlet.http.HttpSession;

import web.member.service.impl.MemberService;
import web.member.vo.MemberVO;

@MultipartConfig
@WebServlet("/MemberLoginTest")
public class MemberLoginTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
        //對Post中文參數進行解碼
        req.setCharacterEncoding("UTF-8");
        res.addHeader("Access-Control-Allow-Origin", "*");
        
        List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		
		MemberService memsvc = new	MemberService();
		MemberVO memberVO = new MemberVO();
		
		memberVO = memsvc.findByPrimaryKey(email);
		String action = req.getParameter("action");

		
	  
	    
		
		//登入會員
		if("login".equals(action)) {
			//錯誤問題
			try {
				if(email.equals(null)||email.equals("")) {
					errorMsgs.add("請輸入信箱");
				}else if(!memberVO.getEmail().equals(email)) {
					errorMsgs.add("請確認信箱");
				}
				
				if(password.equals(null)||password.equals("")) {
					errorMsgs.add("請輸入密碼");			
				}else if(!memberVO.getPassword().equals(password)) {
					errorMsgs.add("密碼錯誤");
				}
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/loginTest1.jsp");
					failureView.forward(req,res);
					return;
				}
			

					HttpSession session = req.getSession();
					req.getSession().setAttribute("memberVO", memberVO);
					try {                                                        
				         String location = (String) session.getAttribute("location");
				         if (location != null) {
				           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
				           res.sendRedirect(location);            
				           return;
				         }
				       }catch (Exception ignored) { }
					
				      res.sendRedirect(req.getContextPath()+"/member/MemberList.jsp");
				
				
//			HttpSession session = req.getSession();
//			req.getSession().setAttribute("memberVO",memberVO);
//				RequestDispatcher successView = req.getRequestDispatcher("/Member/MemberList");
//				successView.forward(req, res);	
				
				
			}catch (Exception e){
				errorMsgs.add("帳號密碼錯誤");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/loginTest1.jsp");
				failureView.forward(req,res);
				return;
			}
		}
		
		
		
		
//		 if("updateList".equals(update)) {
//		    	
//		    	req.setAttribute("memberVO", memberVO);
//				RequestDispatcher successView = req.getRequestDispatcher("/Member/MemberUpdate.jsp");
//				successView.forward(req, res);
//		    	
//		    }
		
	}

}
