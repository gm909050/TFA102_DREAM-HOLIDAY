package web.nearby.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import web.nearby.service.NEARBYService;
import web.nearby.vo.NEARBYVO;



@MultipartConfig
//@WebServlet("/NearbyupdateServlet")

public class NearbyupdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
				Part nearby = null;
				String nearby_name = null;
				String nearby_explanation = null;
				Integer nearby_id = null;
				byte[] nearbybyte = null;
						
				res.setContentType("text/html;charset=UTF-8");	 
		        //對Post中文參數進行解碼
		        req.setCharacterEncoding("UTF-8");
		        res.addHeader("Access-Control-Allow-Origin", "*");
			
	    
		        NEARBYVO nearbyvo = new NEARBYVO();
		        NEARBYService neaser = new NEARBYService();
		        
		        nearby_id = Integer.parseInt(req.getParameter("nearbyid"));
		        nearby_name = req.getParameter("nearbyname");
		        nearby_explanation = req.getParameter("nearbyexplanation");	        
		        
		        System.out.println(nearby_id);
		        
		        System.out.println(nearby_name);
		        
		        System.out.println(nearby_explanation);
		        
		        nearby = req.getPart("nearby");
		        
		        String filename = nearby.getSubmittedFileName();
		        
		        InputStream in = nearby.getInputStream();
		        nearbybyte = new byte[in.available()];
				in.read(nearbybyte);
				in.close();        
	     	        
				neaser.update(nearby_name, nearby_explanation, nearbybyte, nearby_id);
	        	 
	            RequestDispatcher forwarded = req.getRequestDispatcher("/nearby/nearbymanage.jsp");
	            forwarded.forward(req, res);
			}
    
	        
//	        
//	        nearbyvo.setNearbyname("TEST");
//	        nearbyvo.setNearbyexplanation("test");
//			byte[] pic = nearbyvo.getPictureByteArray(getServletContext().getRealPath("img/a.jpg"));
//			nearbyvo.setNearby(pic);
//			
//			nearby_name = nearbyvo.getNearbyname();
//			nearby_explanation = nearbyvo.getNearbyexplanation();
//			nearby = nearbyvo.getNearby();
//			
//			neaser.insert(nearby_name,nearby_explanation,nearby);
	        
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doGet(req, res);
	}

}
