package web.booking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.booking.service.BookingInfoService;
import web.booking.service.impl.BookingInfoServiceImpl;
import web.booking.vo.BookingInfo;
import web.tools.ajax.ResponseFormat;

@WebServlet("/booking/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		res.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = res.getWriter();
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			Integer memberId = null;
			try {
				memberId = new Integer(req.getParameter("memberId").trim()); //會員編號
			} catch(NumberFormatException e) {
				System.out.println("111111");
				errorMsgs.add("請填數字.");
			}
			
			
			Integer bookTime = null;
			try {
				bookTime = new Integer(req.getParameter("whichTime")); //場次
			} catch (NumberFormatException e) {
				System.out.println("222222");
				errorMsgs.add("請填數字.");
			}
			
			
			Integer peopleNum = null;
			try {
				peopleNum = new Integer(req.getParameter("peopleNum")); //座位人數
			} catch (NumberFormatException e) {
				System.out.println("333333");
				errorMsgs.add("請填數字.");
			}
			
			java.sql.Date whichDate = null;
			try {
				whichDate = java.sql.Date.valueOf(req.getParameter("whichDate").trim()); //日期
			} catch (IllegalArgumentException e) {
				System.out.println("444444");
				errorMsgs.add("請輸入日期!");
			}

			Gson gson = new Gson();
			ResponseFormat rf = null;
			String jsonStr = null;
			
			if (!errorMsgs.isEmpty()) {
				out.print("error");
				out.flush();
			} else {

				BookingInfo bInfoVO = new BookingInfo();
				bInfoVO.setSeatCode("0"); //default，未分配座位
				bInfoVO.setMemberId(memberId); //會員
				bInfoVO.setMealDate(whichDate); //日期
				bInfoVO.setMealTime(bookTime); //場次，1:早 2:午 3:晚
				bInfoVO.setBookingStatus(0); //default，0:已訂位 1:取消訂位 2:用餐中 3:未到 4:已結帳
				bInfoVO.setPayBill(0); //default，未點餐
				bInfoVO.setBookingSeattype(peopleNum); //座位，2,4,6人
				
				BookingInfoService bookingSvc = new BookingInfoServiceImpl();
				boolean result = bookingSvc.bookingSeat(bInfoVO);
				System.out.println("[controller]新增成功? " +result);
				
				if(result) {
					rf = new ResponseFormat("success", "200");
					jsonStr = gson.toJson(rf);
					out.print(jsonStr);
					out.close();
				}
			}
			
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			out.print("error");
			out.flush();
		}
	}
	
	private java.sql.Date transferCalendarToSqlDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return new Date(calendar.getTimeInMillis());
	}

}
