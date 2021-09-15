package web.oneday.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import web.oneday.service.ONEDAYService;
import web.oneday.vo.ONEDAYVO;


@WebServlet("/OneDaygetAllServlet")
public class OneDaygetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");	 
        //對Post中文參數進行解碼
        req.setCharacterEncoding("UTF-8");
        res.addHeader("Access-Control-Allow-Origin", "*");
        
        ONEDAYService oneser = new ONEDAYService();
        	
        		HashMap onemap = new HashMap();
        		List<ONEDAYVO> list = oneser.getAll();	
        		
    			onemap.put("list",list);

        		
			        		JSONObject obj = new JSONObject(onemap);
			    			System.out.println(obj);
			    	        PrintWriter out = res.getWriter();
			    	        out.println(obj);

	
	};
	


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
