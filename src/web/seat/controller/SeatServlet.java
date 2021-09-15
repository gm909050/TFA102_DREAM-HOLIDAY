package web.seat.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.seat.service.SeatService;
import web.seat.service.impl.SeatServiceImpl;
import web.seat.vo.Seat;

@WebServlet("/seat/SeatServlet")
public class SeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getItem_for_update".equals(action)) { // 來自listAllSeat.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String seatCode = req.getParameter("seatCode");

				/***************************2.開始查詢資料****************************************/
				SeatService service = new SeatServiceImpl();
				Seat seat = service.getSeatByKey(seatCode);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("seatVO", seat);
				String url = "/seat/update_seat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_seat.jsp
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/seat/listAllSeat.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_seat.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String seatCode = req.getParameter("seatCode").trim();
			
			Integer seatType = new Integer(req.getParameter("seatType").trim());
			Integer seatStatus = new Integer(req.getParameter("seatStatus").trim());
			
			Seat seatVO = new Seat();
			seatVO.setSeatCode(seatCode);
			seatVO.setSeatType(seatType);
			seatVO.setSeatStatus(seatStatus);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("seatVO", seatVO); // 含有輸入格式錯誤的seatVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/seat/update_seat.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			
			/***************************2.開始修改資料*****************************************/
			SeatService seatSvc = new SeatServiceImpl();
			boolean result = seatSvc.updateSeat(seatVO);
			if(result) {
				req.setAttribute("seatVO", seatVO);
				String url = "/seat/listAllSeat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllSeat.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String seatCode = req.getParameter("seatCode");
				
				/***************************2.開始刪除資料***************************************/
				SeatService seatSvc = new SeatServiceImpl();
				seatSvc.deleteSeat(seatCode);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/seat/listAllSeat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/seat/listAllSeat.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) { // 來自addSeat.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String seatCode = req.getParameter("seatCode");
				String seatReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (seatCode == null || seatCode.trim().length() == 0) {
					errorMsgs.add("座位代號: 請勿空白");
				} else if(!seatCode.trim().matches(seatReg)) {
					errorMsgs.add("座位代號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				Integer seatType = new Integer(req.getParameter("seatType").trim());
				Integer seatStatus = new Integer(req.getParameter("seatStatus").trim());
				System.out.println("seatType:"+seatType+",seatStatus:"+seatStatus);
				Seat seatVO = new Seat();
				seatVO.setSeatCode(seatCode);
				seatVO.setSeatType(seatType);
				seatVO.setSeatStatus(seatStatus);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("seatVO", seatVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/seat/addSeat.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				SeatService seatSvc = new SeatServiceImpl();
				boolean result = seatSvc.addSeat(seatVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				if(result) {
					String url = "/seat/listAllSeat.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);		
				}
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/seat/addSeat.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 新增
//		Seat seat = new Seat();
//		seat.setSeatCode("B001");
//		seat.setSeatType(4);
//		seat.setSeatStatus(2);
		
		// 修改
//		Seat seat = new Seat();
//		seat.setSeatCode("B001");
//		seat.setSeatType(6);
//		seat.setSeatStatus(1);

		
//		SeatService service;
//		try {
//			service = new SeatServiceImpl();

//			boolean result = service.addSeat(seat);
//			boolean result = service.updateSeat(seat);
//			boolean result = service.deleteSeat("B001");
//			res.getWriter().append(result+"");
			
			//select one
//			Seat seat = service.getSeatByKey("A009");
//			if(seat != null) {
//				res.getWriter().append(seat.toString());
//			}
			
			//select all
//			StringBuilder sb = new StringBuilder();
//			List<Seat> list = service.getAll();
//			Consumer<Seat> seatConsumer = p -> {
//				sb.append(p.toString());
//				sb.append("<br>");
//			};
//			list.stream().forEach(seatConsumer);
//			res.getWriter().append(sb);
			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
