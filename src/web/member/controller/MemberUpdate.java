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

import web.member.service.impl.MemberService;
import web.member.vo.MemberVO;

@MultipartConfig
@WebServlet("/MemberUpdate")
public class MemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
	    
	    req.setCharacterEncoding("UTF-8");
	    res.addHeader("Access-Control-Allow-Origin", "*");
	    
	    List<String> errorMsgs = new LinkedList<String>();
	    req.setAttribute("errorMsgs", errorMsgs);
	    
	
	    MemberVO memberVO = new MemberVO();
	   
	    MemberService memsvc = new MemberService();
	    
	    
	    Integer memberId = new Integer(req.getParameter("memberId"));
	    String name = req.getParameter("name");
	    String idNumber = req.getParameter("idNumber");
	    String phone = req.getParameter("phone");
	    String email = req.getParameter("email");
	    String password = req.getParameter("password");
	    String confirmPassword = req.getParameter("confirmPassword");
	    
	    String action = req.getParameter("action");
	    
	    	
	   if("update".equals(action)) {
		   
		  try {
	    //��s�|��
	    String nameMsg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if(name == null||name.trim().length()==0){
			errorMsgs.add("�m�W:����ť�");
		} else if(!name.trim().matches(nameMsg)){
			errorMsgs.add("�m�W:�u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
		}
		
		
		String phoneReg = "^[0-9]{10}$";
		if(phone == null||phone.trim().length()==0){
			errorMsgs.add("���:����ť�");
		}else if(!phone.trim().matches(phoneReg)) {
			errorMsgs.add("���:�u��O�Ʀr,�B���׬�10");
		}
		
		String passwordReg = "[(a-zA-Z0-9_)]{5,20}$";
		if(password ==null ||email.trim().length()==0) {
			errorMsgs.add("�K�X:����ť�");
		}else if(!password.trim().matches(passwordReg)) {
			errorMsgs.add("�K�X:�u��O�Ʀr�^��,�B���׬�5-20");
		}
		
		if(confirmPassword ==null||confirmPassword.trim().length()==0) {
			errorMsgs.add("�T�{�K�X:����ť�");
		}else if(!confirmPassword.matches(password)) {
			errorMsgs.add("�T�{�K�X:�P�K�X���ۦP");
		}
		
		
		
		
		memberVO.setMemberId(memberId);
		memberVO.setName(name);
		memberVO.setIdNumber(idNumber);
		memberVO.setPhone(phone);
		memberVO.setEmail(email);
		memberVO.setPassword(password);
		
		
		
		if(!errorMsgs.isEmpty()) {
			req.setAttribute("memberVO", memberVO);
			String url = "/member/MemberUpdate.jsp";
			RequestDispatcher failView = req.getRequestDispatcher(url);
			failView.forward(req, res);
			return;
		}
		
		/***************************2.�}�l�ק���*****************************************/
		memberVO = memsvc.updateMember(memberId, name, idNumber, phone, email, password);
		
		req.setAttribute("memberVO", memberVO);
		RequestDispatcher successView = req.getRequestDispatcher("/member/loginTest1.jsp");
		successView.forward(req, res);	
		
		  }	catch (Exception e) {
		errorMsgs.add("�ק��ƥ���:"+e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/member/Update.jsp");
		failureView.forward(req, res);
			return;
		  }
	   } 
	}


}
