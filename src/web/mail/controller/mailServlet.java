package web.mail.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.mail.MailService;

@WebServlet("/mailServlet")
public class mailServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
 
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String to =null;
		String subject = null;
		String name = null;
		String phone = null;
		String mail = null;
		String text = null;
		
		res.setContentType("text/html;charset=UTF-8");	 
        //��Post����Ѽƶi��ѽX
        req.setCharacterEncoding("UTF-8");
        res.addHeader("Access-Control-Allow-Origin", "*");
        
    	 

		 subject = "�Ȥ�^���������D";
		
		 name = req.getParameter("name");
		 phone = req.getParameter("phone");
		 mail = req.getParameter("email");
		 text = req.getParameter("message");
		 
		 to = mail;
		
		String messageText = "�m�W:" + name +"\n"+"�Ȥ�s���q��:"
								+phone+"\n"+"E-mail:"+mail+"\n"+"�Ȥ�d�����e:"+text;
		
		System.out.println(messageText);
		MailService tomail = new MailService();
		tomail.sendMail(to, subject, messageText);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
