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
		
		if ("getItem_for_update".equals(action)) { // �Ӧ�listAllSeat.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String seatCode = req.getParameter("seatCode");

				/***************************2.�}�l�d�߸��****************************************/
				SeatService service = new SeatServiceImpl();
				Seat seat = service.getSeatByKey(seatCode);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("seatVO", seat);
				String url = "/seat/update_seat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_seat.jsp
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/seat/listAllSeat.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // �Ӧ�update_seat.jsp���ШD
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
				req.setAttribute("seatVO", seatVO); // �t����J�榡���~��seatVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/seat/update_seat.jsp");
				failureView.forward(req, res);
				return; //�{�����_
			}
			
			/***************************2.�}�l�ק���*****************************************/
			SeatService seatSvc = new SeatServiceImpl();
			boolean result = seatSvc.updateSeat(seatVO);
			if(result) {
				req.setAttribute("seatVO", seatVO);
				String url = "/seat/listAllSeat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // �Ӧ�listAllSeat.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String seatCode = req.getParameter("seatCode");
				
				/***************************2.�}�l�R�����***************************************/
				SeatService seatSvc = new SeatServiceImpl();
				seatSvc.deleteSeat(seatCode);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
				String url = "/seat/listAllSeat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/seat/listAllSeat.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) { // �Ӧ�addSeat.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String seatCode = req.getParameter("seatCode");
				String seatReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (seatCode == null || seatCode.trim().length() == 0) {
					errorMsgs.add("�y��N��: �ФŪť�");
				} else if(!seatCode.trim().matches(seatReg)) {
					errorMsgs.add("�y��N��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
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
				
				/***************************2.�}�l�s�W���***************************************/
				SeatService seatSvc = new SeatServiceImpl();
				boolean result = seatSvc.addSeat(seatVO);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
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
		
		// �s�W
//		Seat seat = new Seat();
//		seat.setSeatCode("B001");
//		seat.setSeatType(4);
//		seat.setSeatStatus(2);
		
		// �ק�
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
