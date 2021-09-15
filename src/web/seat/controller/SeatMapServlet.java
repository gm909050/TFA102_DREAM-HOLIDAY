package web.seat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import web.booking.service.BookingInfoService;
import web.booking.service.impl.BookingInfoServiceImpl;
import web.booking.vo.BookingInfo;
import web.order.service.OrderDetailService;
import web.order.service.impl.OrderDetailServiceImpl;
import web.order.vo.OrderDetail;
import web.seat.service.SeatService;
import web.seat.service.impl.SeatServiceImpl;
import web.seat.vo.Seat;
import web.tools.SeatStatusEnum;
import web.tools.ajax.ResponseFormat;

@WebServlet("/seat/SeatMapServlet")
public class SeatMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		res.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = res.getWriter();
		
		Jedis jedis = new Jedis("localhost", 6379);
		
		String resetRedis = req.getParameter("isResetRedis");
		if("true".equals(resetRedis)) {
			jedis.flushDB(); //按鈕清空Redis資料
			return;
		}
		
		String select_seat_code =  req.getParameter("item_id"); //桌號
		Integer select_status = new Integer(req.getParameter("select_status").trim()); //點選狀態
		Integer select_meal_id = new Integer(req.getParameter("select_meal_id").trim()); //場次編號
		System.out.println("select_seat_code: "+select_seat_code+", select_status: "+select_status+", select_meal_id:"+select_meal_id);

		String redisSeatStatus = jedis.get(select_seat_code+":2"); //第一次取值(可能為null)，:1=seatType, :2=seatStatus
		if(null == redisSeatStatus || "".equals(redisSeatStatus)) {
			//redis 沒有資料，從關聯資料庫取得，並存進redis
			System.out.println("redis無資料，刷新資料");
			resetSeatFromRDBToRedis(jedis);
		}
		
		redisSeatStatus = jedis.get(select_seat_code+":2"); //第二次取值(不為null) :1=seatType, :2=seatStatus
		System.out.println("[redis]"+select_seat_code+"目前的座位狀態: "+redisSeatStatus);
		System.out.println("想轉換的目標狀態: "+select_status.toString());
		
		//從redis 取得值，回傳給seatmap.jsp
		if(select_status != Integer.parseInt(redisSeatStatus)) { //選取的狀態和redis的狀態不相同時
			System.out.println("選取的狀態和redis目前的狀態不相同");

			if(redisSeatStatus.equals(SeatStatusEnum.IDLE.getIdx()) &&
				select_status.toString().equals(SeatStatusEnum.PRE_ORDER.getIdx()) &&
				select_meal_id > 0
			) { //閒置席->待點餐(帶位) 且有選擇訂位編號(-1:表示沒有選取)
				System.out.println("閒置席->待點餐");
				BookingInfoService bookSvc = new BookingInfoServiceImpl();
				BookingInfo infoVO = bookSvc.getBookingByKey(select_meal_id); //取出該訂位編號的訂位
				System.out.println("訂位者劃位位置是:"+infoVO.getSeatCode());
				
				if(infoVO.getSeatCode().equals("0")) {
					System.out.println("該位會員的seatCode 尚未劃位");
					infoVO.setSeatCode(select_seat_code); //該mealid選擇的桌號
					infoVO.setBookingStatus(2); //訂位狀態改為用餐中
					bookSvc.updateBooking(infoVO);
					
					jedis.set(select_seat_code+":2", select_status.toString()); //將該座位設為待用餐
					jedis.set(select_seat_code+":3", select_meal_id.toString()); //桌號與mealid綁定存進redis
					
					Seat seatVO = new Seat(); //回傳用的VO
					seatVO.setSeatCode(select_seat_code); //桌號
					seatVO.setSeatStatus(select_status); //待點餐
					
					Gson gson = new Gson();
					String jsonStr = gson.toJson(seatVO);
					out.print(jsonStr);
					out.close();
				} else {
					System.out.println("位會員的seatCode 已劃位");
					out.print("Error1");
					out.flush();
				}
			} else if (redisSeatStatus.equals(SeatStatusEnum.PRE_ORDER.getIdx()) &&
				select_status.toString().equals(SeatStatusEnum.ORDER.getIdx()) &&
				select_meal_id < 0
			) { //待點餐->已點餐(點餐)
				System.out.println("待點餐->(已)點餐");
				jedis.set(select_seat_code+":2", select_status.toString()); //更新redis狀態，由待點餐->已點餐
				if(!jedis.get(select_seat_code+":3").equals("none")) {
					Integer meal_id = Integer.valueOf(jedis.get(select_seat_code+":3"));
					
//					OrderDetailService orderSvc = new OrderDetailServiceImpl();
//					List<OrderDetail> orderVO = orderSvc.getAllByMealId(meal_id);
					HttpSession session = req.getSession();
					session.setAttribute("meal_id", meal_id);
					
					//TODO 接收到ajax參數，並跳轉頁面(先回到ajax，再跳點餐頁)
					Seat seatVO = new Seat(); //回傳用的VO
					seatVO.setSeatCode(select_seat_code); //桌號
					seatVO.setSeatStatus(select_status); //已點餐
					
					Map<String, Object> map = new HashMap();
					map.put("select_status", select_status);
					
					Gson gson = new Gson();
					String jsonStr = gson.toJson(map);
					out.print(jsonStr);
					out.close();
				} else {
					System.out.println("待點餐->(已)點餐，該桌不該選取訂位者");
					out.print("Error3");
					out.flush();
				}
			}  else if( redisSeatStatus.equals(SeatStatusEnum.ORDER.getIdx()) &&
				select_status.toString().equals(SeatStatusEnum.CHECKOUT.getIdx())	
			) { //已點餐->閒置席(結帳)
				System.out.println("已點餐->閒置席(結帳)");
				jedis.set(select_seat_code+":2", select_status.toString());
				if(!jedis.get(select_seat_code+":3").equals("none")) {
					Integer meal_id = Integer.valueOf(jedis.get(select_seat_code+":3"));
					
					BookingInfoService bkSvc = new BookingInfoServiceImpl();
					BookingInfo info = bkSvc.getBookingByKey(meal_id);
					
					Map<String, Object> map = new HashMap();
					map.put("select_status", select_status);
					map.put("totalPrice", info.getPayBill());
					map.put("seat_code", select_seat_code);
					
					jedis.set(select_seat_code+":2", "2"); //將座位設定為閒置席，因為結帳後該位置已空出
					jedis.set(select_seat_code+":3", "none");
					
					Gson gson = new Gson();
					String jsonStr = gson.toJson(map);
					out.print(jsonStr);
					out.close();
				} else {
					System.out.println("已點餐->閒置席(結帳)，該桌不該選取訂位者");
					out.print("Error5");
					out.flush();
				}
			} else {
				System.out.println("[未依照順序]: 目前座位狀態 "+redisSeatStatus+", 目標狀態"+select_status.toString());
			}

		} else {
			System.out.println("選取的狀態和redis目前的狀態相同，不做任何事");
			out.print("Error2");
			out.flush();
		}
	}
	
	private void resetSeatFromRDBToRedis(Jedis jedis) { //從RDB取值重設Redis
		SeatService seatSvc = new SeatServiceImpl();
		List<Seat> seatList = seatSvc.getAll();
		
		for(Seat seatVO : seatList) {
			jedis.set(seatVO.getSeatCode()+":1", seatVO.getSeatType().toString());
			jedis.set(seatVO.getSeatCode()+":2", seatVO.getSeatStatus().toString());
			jedis.set(seatVO.getSeatCode()+":3", "none");
		}
	}
}
