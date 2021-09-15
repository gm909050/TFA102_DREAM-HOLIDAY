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
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		res.setContentType("text/html;charset=UTF-8");
        //��Post����Ѽƶi��ѽX
        req.setCharacterEncoding("UTF-8");
        res.addHeader("Access-Control-Allow-Origin", "*");
        
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		MemberVO memberVO = new MemberVO();
		MemberService memSvc = new MemberService();
		
		String name = req.getParameter("name");
		String idNumber = req.getParameter("idNumber");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirm_Password");
		
		String action = req.getParameter("action");
		
		
		memberVO = memSvc.findByPrimaryKey(email);
		//�s�W�|��
		if("insert".equals(action)) {
			//���~���D
//			try {
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(name == null||name.trim().length()==0){
					errorMsgs.add("�m�W:�ФŪť�");
				} else if(!name.trim().matches(nameReg)){
					errorMsgs.add("�m�W:�u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				
				
				
				String idNumberReg = "[(a-zA-Z0-9_)]{10}$";
				if(idNumber == null||idNumber.trim().length()==0) {
					errorMsgs.add("������:�ФŪť�");
				}else if(!idNumber.trim().matches(idNumberReg)){
					errorMsgs.add("������:�u��O�Ʀr�M�^��.�B���ץ����O10��");
				}
				
				String phoneReg = "^09[0-9]{8}$";
				if(phone == null||phone.trim().length()==0){
					errorMsgs.add("�q��:�ФŪť�");
				}else if(!phone.trim().matches(phoneReg)) {
					errorMsgs.add("�q��:�u��Ʀr,�B���ץ����O10");
				}
				
	if(memberVO!=null)	{		
			
					 if(memberVO.getEmail().equals(email)) {
					errorMsgs.add("�l��:���H�ϥιL");
				}
	}	else {
		memberVO = new MemberVO();
	}	if(email == null ||email.trim().length()==0) {
		errorMsgs.add("�l��:�ФŪť�");
	}	
	
				String passwordReg = "[(a-zA-Z0-9_)]{5,19}$";
				if(password ==null ||password.trim().length()==0) {
					errorMsgs.add("�K�X:�ФŪť�");
				}else if(!password.trim().matches(passwordReg)) {
					errorMsgs.add("�K�X:�u��Ʀr�M�^��,�B���ץ����O6��20");
				}
				
				if(confirmPassword ==null||confirmPassword.trim().length()==0) {
					errorMsgs.add("�T�{�K�X:����ť�");
				}else if(!confirmPassword.matches(password)) {
					errorMsgs.add("�T�{�K�X:�K�X���ۨ�");
				}
        
        

	
				
				memberVO.setName(name);
				memberVO.setIdNumber(idNumber);
				memberVO.setPhone(phone);
				memberVO.setEmail(email);
				memberVO.setPassword(password);
				
				//�榡���~��^����
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/MemberCreateAccount.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
			
				memSvc.addMember(name, idNumber, phone, email, password);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/		
				RequestDispatcher successView = req.getRequestDispatcher("/member/loginTest1.jsp"); 
				successView.forward(req, res);			
	
			
			} 
//		catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/member/MemberCreateAccount.jsp");
//				failureView.forward(req,res);
//				return;
//			}
//		}		
			
	} 
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		
//	}
	
}

