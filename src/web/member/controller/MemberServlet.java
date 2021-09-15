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
        //對Post中文參數進行解碼
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
		//新增會員
		if("insert".equals(action)) {
			//錯誤問題
//			try {
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(name == null||name.trim().length()==0){
					errorMsgs.add("姓名:請勿空白");
				} else if(!name.trim().matches(nameReg)){
					errorMsgs.add("姓名:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				
				
				
				String idNumberReg = "[(a-zA-Z0-9_)]{10}$";
				if(idNumber == null||idNumber.trim().length()==0) {
					errorMsgs.add("身分證:請勿空白");
				}else if(!idNumber.trim().matches(idNumberReg)){
					errorMsgs.add("身分證:只能是數字和英文.且長度必須是10位");
				}
				
				String phoneReg = "^09[0-9]{8}$";
				if(phone == null||phone.trim().length()==0){
					errorMsgs.add("電話:請勿空白");
				}else if(!phone.trim().matches(phoneReg)) {
					errorMsgs.add("電話:只能數字,且長度必須是10");
				}
				
	if(memberVO!=null)	{		
			
					 if(memberVO.getEmail().equals(email)) {
					errorMsgs.add("郵件:有人使用過");
				}
	}	else {
		memberVO = new MemberVO();
	}	if(email == null ||email.trim().length()==0) {
		errorMsgs.add("郵件:請勿空白");
	}	
	
				String passwordReg = "[(a-zA-Z0-9_)]{5,19}$";
				if(password ==null ||password.trim().length()==0) {
					errorMsgs.add("密碼:請勿空白");
				}else if(!password.trim().matches(passwordReg)) {
					errorMsgs.add("密碼:只能數字和英文,且長度必須是6到20");
				}
				
				if(confirmPassword ==null||confirmPassword.trim().length()==0) {
					errorMsgs.add("確認密碼:不能空白");
				}else if(!confirmPassword.matches(password)) {
					errorMsgs.add("確認密碼:密碼不相依");
				}
        
        

	
				
				memberVO.setName(name);
				memberVO.setIdNumber(idNumber);
				memberVO.setPhone(phone);
				memberVO.setEmail(email);
				memberVO.setPassword(password);
				
				//格式錯誤返回頁面
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/MemberCreateAccount.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
			
				memSvc.addMember(name, idNumber, phone, email, password);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/		
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

