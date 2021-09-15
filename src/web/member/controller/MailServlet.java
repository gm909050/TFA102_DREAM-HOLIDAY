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
        //��Post����Ѽƶi��ѽX
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
        	errorMsgs.add("�п�J�H�c");
        }
        
        if(idNumber.equals(null)||idNumber.equals("")) {
        	errorMsgs.add("�п�J�����Ҧr��");
        }
        
        
        if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/member/ForgetPassword.jsp");
			failureView.forward(req, res);
			return;//�{�����_
		};
		
		if(!idNumber.matches(id)) {
			errorMsgs.add("�нT�{�����Ү榡");
		}
        if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/member/ForgetPassword.jsp");
			failureView.forward(req, res);
			return;//�{�����_
		};
		
        if(memberVO == null) {
        	errorMsgs.add("�нT�{�H�c���T");
        }
        
        if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/member/ForgetPassword.jsp");
			failureView.forward(req, res);
			return;//�{�����_
		};
      
		System.out.println(memberVO.getIdNumber());
        if(!memberVO.getIdNumber().equals(idNumber)) {
        	errorMsgs.add("�нT�{�����ҬO�_���T");
        };
        
         
        if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/member/ForgetPassword.jsp");
			failureView.forward(req, res);
			return;//�{�����_
		};
	
		 	memberId = memberVO.getMemberId();
	        name = memberVO.getName();
	        phone = memberVO.getPhone();
		
		
		
		Verification ver = new Verification();
		StringBuffer strsb = ver.genAuthCode(array);
		password =strsb.toString();
		
	

		System.out.println(password.trim());
	
		String subject = "�����s�K�X";
		String messageText = "�Ȥ�:" + name +"�z�n"+"\n�z���s�K�X��"+password+"�ШϥΦ��K�X�n�J��ק�K�X";
				

		
		
		memSvc.updateMember(memberId, name, idNumber, phone, email, password);
		
		MailService tomail = new MailService();
		tomail.sendMail(email, subject, messageText);
		
		//�Ыئ��\ �����ɤJ�n�J�e��
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/loginTest1.jsp");
				failureView.forward(req, res);
		
	}
		
        
	

}
