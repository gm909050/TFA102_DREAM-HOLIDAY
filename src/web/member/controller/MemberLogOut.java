package web.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@MultipartConfig
@WebServlet("/MemberLogOut")
public class MemberLogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		res.setContentType("text/html;charset=UTF-8");	 
        //對Post中文參數進行解碼
        req.setCharacterEncoding("UTF-8");
        res.addHeader("Access-Control-Allow-Origin", "*");
        
        HttpSession session = req.getSession();    
        
    	session.invalidate();
    	
    	String url ="http://localhost:8081/Hotel_Team5_TFA102/hpbasic/hpbasic.jsp";    
    	res.sendRedirect(url);
    	
	}

}
