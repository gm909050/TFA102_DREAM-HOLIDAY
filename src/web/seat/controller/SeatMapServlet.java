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
			jedis.flushDB(); //���s�M��Redis���
			return;
		}
		
		String select_seat_code =  req.getParameter("item_id"); //�ู
		Integer select_status = new Integer(req.getParameter("select_status").trim()); //�I�窱�A
		Integer select_meal_id = new Integer(req.getParameter("select_meal_id").trim()); //�����s��
		System.out.println("select_seat_code: "+select_seat_code+", select_status: "+select_status+", select_meal_id:"+select_meal_id);

		String redisSeatStatus = jedis.get(select_seat_code+":2"); //�Ĥ@������(�i�ରnull)�A:1=seatType, :2=seatStatus
		if(null == redisSeatStatus || "".equals(redisSeatStatus)) {
			//redis �S����ơA�q���p��Ʈw���o�A�æs�iredis
			System.out.println("redis�L��ơA��s���");
			resetSeatFromRDBToRedis(jedis);
		}
		
		redisSeatStatus = jedis.get(select_seat_code+":2"); //�ĤG������(����null) :1=seatType, :2=seatStatus
		System.out.println("[redis]"+select_seat_code+"�ثe���y�쪬�A: "+redisSeatStatus);
		System.out.println("�Q�ഫ���ؼЪ��A: "+select_status.toString());
		
		//�qredis ���o�ȡA�^�ǵ�seatmap.jsp
		if(select_status != Integer.parseInt(redisSeatStatus)) { //��������A�Mredis�����A���ۦP��
			System.out.println("��������A�Mredis�ثe�����A���ۦP");

			if(redisSeatStatus.equals(SeatStatusEnum.IDLE.getIdx()) &&
				select_status.toString().equals(SeatStatusEnum.PRE_ORDER.getIdx()) &&
				select_meal_id > 0
			) { //���m�u->���I�\(�a��) �B����ܭq��s��(-1:��ܨS�����)
				System.out.println("���m�u->���I�\");
				BookingInfoService bookSvc = new BookingInfoServiceImpl();
				BookingInfo infoVO = bookSvc.getBookingByKey(select_meal_id); //���X�ӭq��s�����q��
				System.out.println("�q��̹����m�O:"+infoVO.getSeatCode());
				
				if(infoVO.getSeatCode().equals("0")) {
					System.out.println("�Ӧ�|����seatCode �|������");
					infoVO.setSeatCode(select_seat_code); //��mealid��ܪ��ู
					infoVO.setBookingStatus(2); //�q�쪬�A�אּ���\��
					bookSvc.updateBooking(infoVO);
					
					jedis.set(select_seat_code+":2", select_status.toString()); //�N�Ӯy��]���ݥ��\
					jedis.set(select_seat_code+":3", select_meal_id.toString()); //�ู�Pmealid�j�w�s�iredis
					
					Seat seatVO = new Seat(); //�^�ǥΪ�VO
					seatVO.setSeatCode(select_seat_code); //�ู
					seatVO.setSeatStatus(select_status); //���I�\
					
					Gson gson = new Gson();
					String jsonStr = gson.toJson(seatVO);
					out.print(jsonStr);
					out.close();
				} else {
					System.out.println("��|����seatCode �w����");
					out.print("Error1");
					out.flush();
				}
			} else if (redisSeatStatus.equals(SeatStatusEnum.PRE_ORDER.getIdx()) &&
				select_status.toString().equals(SeatStatusEnum.ORDER.getIdx()) &&
				select_meal_id < 0
			) { //���I�\->�w�I�\(�I�\)
				System.out.println("���I�\->(�w)�I�\");
				jedis.set(select_seat_code+":2", select_status.toString()); //��sredis���A�A�ѫ��I�\->�w�I�\
				if(!jedis.get(select_seat_code+":3").equals("none")) {
					Integer meal_id = Integer.valueOf(jedis.get(select_seat_code+":3"));
					
//					OrderDetailService orderSvc = new OrderDetailServiceImpl();
//					List<OrderDetail> orderVO = orderSvc.getAllByMealId(meal_id);
					HttpSession session = req.getSession();
					session.setAttribute("meal_id", meal_id);
					
					//TODO ������ajax�ѼơA�ø��୶��(���^��ajax�A�A���I�\��)
					Seat seatVO = new Seat(); //�^�ǥΪ�VO
					seatVO.setSeatCode(select_seat_code); //�ู
					seatVO.setSeatStatus(select_status); //�w�I�\
					
					Map<String, Object> map = new HashMap();
					map.put("select_status", select_status);
					
					Gson gson = new Gson();
					String jsonStr = gson.toJson(map);
					out.print(jsonStr);
					out.close();
				} else {
					System.out.println("���I�\->(�w)�I�\�A�Ӯण�ӿ���q���");
					out.print("Error3");
					out.flush();
				}
			}  else if( redisSeatStatus.equals(SeatStatusEnum.ORDER.getIdx()) &&
				select_status.toString().equals(SeatStatusEnum.CHECKOUT.getIdx())	
			) { //�w�I�\->���m�u(���b)
				System.out.println("�w�I�\->���m�u(���b)");
				jedis.set(select_seat_code+":2", select_status.toString());
				if(!jedis.get(select_seat_code+":3").equals("none")) {
					Integer meal_id = Integer.valueOf(jedis.get(select_seat_code+":3"));
					
					BookingInfoService bkSvc = new BookingInfoServiceImpl();
					BookingInfo info = bkSvc.getBookingByKey(meal_id);
					
					Map<String, Object> map = new HashMap();
					map.put("select_status", select_status);
					map.put("totalPrice", info.getPayBill());
					map.put("seat_code", select_seat_code);
					
					jedis.set(select_seat_code+":2", "2"); //�N�y��]�w�����m�u�A�]�����b��Ӧ�m�w�ťX
					jedis.set(select_seat_code+":3", "none");
					
					Gson gson = new Gson();
					String jsonStr = gson.toJson(map);
					out.print(jsonStr);
					out.close();
				} else {
					System.out.println("�w�I�\->���m�u(���b)�A�Ӯण�ӿ���q���");
					out.print("Error5");
					out.flush();
				}
			} else {
				System.out.println("[���̷Ӷ���]: �ثe�y�쪬�A "+redisSeatStatus+", �ؼЪ��A"+select_status.toString());
			}

		} else {
			System.out.println("��������A�Mredis�ثe�����A�ۦP�A���������");
			out.print("Error2");
			out.flush();
		}
	}
	
	private void resetSeatFromRDBToRedis(Jedis jedis) { //�qRDB���ȭ��]Redis
		SeatService seatSvc = new SeatServiceImpl();
		List<Seat> seatList = seatSvc.getAll();
		
		for(Seat seatVO : seatList) {
			jedis.set(seatVO.getSeatCode()+":1", seatVO.getSeatType().toString());
			jedis.set(seatVO.getSeatCode()+":2", seatVO.getSeatStatus().toString());
			jedis.set(seatVO.getSeatCode()+":3", "none");
		}
	}
}
