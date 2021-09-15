package web.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.member.Verification;
import web.member.service.impl.MailService;
import web.member.service.impl.MemberService;
import web.member.vo.MemberVO;


@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		res.setContentType("text/html;charset=UTF-8");	 
        //對Post中文參數進行解碼
        req.setCharacterEncoding("UTF-8");
        res.addHeader("Access-Control-Allow-Origin", "*");
        
        List<String> errorMsgs = new LinkedList<String>();
        req.setAttribute("errorMsgs", errorMsgs);
        
        MemberVO memberVO = new MemberVO();
        MemberService memSvc = new MemberService();
        

        
        char array[] = new char[82];
        Integer memberId = null;
        String name = null;
        String idNumber = null;
        String phone = null;
        String email = null;
        String password =null;
        String id = "^[A-Z,a-z][1-2][0-9]{8}$";       
             
        
        email = req.getParameter("email");
        idNumber = req.getParameter("id_number");
        
        System.out.println(email+" "+idNumber);
        memberVO = memSvc.findByPrimaryKey(email);
        
        
        if(email.equals(null)||email.equals("")) {
        	errorMsgs.add("請輸入信箱");
        }
        
        if(idNumber.equals(null)||idNumber.equals("")) {
        	errorMsgs.add("請輸入身分證字號");
        }
        
        
        if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/member/ForgetPassword.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		};
		
		if(!idNumber.matches(id)) {
			errorMsgs.add("請確認身分證格式");
		}
        if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/member/ForgetPassword.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		};
		
        if(memberVO == null) {
        	errorMsgs.add("請確認信箱正確");
        }
        
        if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/member/ForgetPassword.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		};
      
		System.out.println(memberVO.getIdNumber());
        if(!memberVO.getIdNumber().equals(idNumber)) {
        	errorMsgs.add("請確認身分證是否正確");
        };
        
         
        if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/member/ForgetPassword.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		};
	
		 	memberId = memberVO.getMemberId();
	        name = memberVO.getName();
	        phone = memberVO.getPhone();
		
		
		
		Verification ver = new Verification();
		StringBuffer strsb = ver.genAuthCode(array);
		password =strsb.toString();
		
	

		System.out.println(password.trim());
	
		String subject = "此為新密碼";
		String messageText = "客戶:" + name +"您好"+"\n您的新密碼為"+password+"請使用此密碼登入後修改密碼";
				

		
		
		memSvc.updateMember(memberId, name, idNumber, phone, email, password);
		
		MailService tomail = new MailService();
		tomail.sendMail(email, subject, messageText);
		
		//創建成功 網頁導入登入畫面
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/loginTest1.jsp");
				failureView.forward(req, res);
		
	}
		
        
	

}
