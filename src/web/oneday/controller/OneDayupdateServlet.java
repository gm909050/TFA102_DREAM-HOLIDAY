package web.oneday.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.oneday.service.ONEDAYService;
import web.oneday.vo.ONEDAYVO;


@WebServlet("/OneDayServlet")

public class OneDayupdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
			String picture = null;
			String picture_name = null;
			String picture_explanation = null;
			String picture_id = null;
		
			res.setContentType("text/html;charset=UTF-8");	 
	        //對Post中文參數進行解碼
	        req.setCharacterEncoding("UTF-8");
	        res.addHeader("Access-Control-Allow-Origin", "*");
		
	        picture = req.getParameter("picture");
	        picture_name = req.getParameter("picture_name");
	        picture_explanation = req.getParameter("picture_explanation");
	        picture_id = req.getParameter("picture_id");
	              
	        	        
	        System.out.println(picture);
	        System.out.println(picture_name);
	        System.out.println(picture_explanation);
	        System.out.println(picture_id);
	        
	        ONEDAYVO one =  new ONEDAYVO();
	       
	        ONEDAYService oneser = new ONEDAYService();
	        
//	        String s = picture.substring(24);
//	        System.out.println(s);

	        byte[] pic = Base64.getMimeDecoder().decode(picture.substring(23));
//	        
	        oneser.update(picture_name,picture_explanation,pic,Integer.valueOf(picture_id));
////	        
	        
	        
//	        
////			System.out.println("Test JDBC");
//			ONEDAYVO onevo = new ONEDAYVO();
//			ONEDAYJNDIDAO onejdbc = new ONEDAYJNDIDAO();
//			List<ONEDAYVO> l = null;
//			
////			onevo.setPictureid(2);
//			onevo.setPicturename("一日景點");
//			onevo.setPictureexplanation("此景點非常好33333");
//			System.out.println(req.getContextPath());
//			byte[] pic = onejdbc.getPictureByteArray(getServletContext().getRealPath("img/房屋.png"));
//			onevo.setPicture(pic);
////			System.out.println(getServletContext().getRealPath("img/房屋.png"));
////			
//////			System.out.println();
//			onejdbc.insert(onevo);
//////			onejdbc.delete(onevo.getPictureid());
////			onejdbc.update(onevo);
		}











	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doGet(req, res);
	}

}
