package web.order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import web.order.service.OrderDetailService;
import web.order.service.impl.OrderDetailServiceImpl;
import web.order.vo.OrderDetail;

@WebServlet("/order/OrderDetailServlet")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = res.getWriter();
		
		String jsonOrderList = req.getParameter("order_list");
		System.out.println("order_list:"+jsonOrderList);
		
		//由於meal_id在dishOrder.jsp已取出，放在json傳過來此Servlet，所以此Session可以清除
//		HttpSession session = req.getSession(); 
//		session.removeAttribute("meal_id");
		
		List<OrderDetail> orderData = new ArrayList<OrderDetail>();     

		Integer priceTotal = null;
		Integer meal_id= null;
		try {
			JSONArray jsonArray = new JSONArray(jsonOrderList);
			if (jsonArray != null) {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject json_obj = jsonArray.getJSONObject(i);
					if(json_obj.has("priceTotal")) {
						priceTotal = json_obj.getInt("priceTotal");
					} else if(json_obj.has("meal_id")) {
						meal_id = json_obj.getInt("meal_id");
					} else {
						OrderDetail order = new OrderDetail();
						order.setDishId(json_obj.getInt("dish_id"));
						order.setDishPrice(json_obj.getInt("dish_price"));
						order.setDishQty(json_obj.getInt("dish_qty"));
						orderData.add(order);
					}
				}
				for(OrderDetail order : orderData) {
					System.out.println(order);
				}
				System.out.println("priceTotal:"+priceTotal);
				System.out.println("meal_id:"+meal_id);
				OrderDetailService orderSvc = new OrderDetailServiceImpl();
				boolean result = orderSvc.addOrderList(orderData, priceTotal, meal_id);
				System.out.println("result:"+result);
				
				Gson gson = new Gson();
				String jsonStr = gson.toJson("orders success");
				out.print(jsonStr);
				out.close();
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
			out.print("Error4");
			out.flush();
		}
		
		
		
		
		// 新增
//		OrderDetail order = new OrderDetail();
//		order.setMealId(17);
//		order.setDishId(8);
//		order.setDishPrice(1200);
//		order.setDishQty(2);
		
		// 修改
//		OrderDetail order = new OrderDetail();
//		order.setOrderId(18);
//		order.setMealId(16);
//		order.setDishId(1);
//		order.setDishPrice(2000);
//		order.setDishQty(1);
		
		OrderDetailService service;
		
		try {
			service = new OrderDetailServiceImpl();
//			boolean result = service.addOrder(order);
//			boolean result = service.updateOrder(order);
//			boolean result = service.deleteOrder(new Integer(18));
//			res.getWriter().append(result+"");
			
			//select one
//			OrderDetail order = service.getOrderDetailByKey(new Integer(13));
//			if(order != null) {
//				res.getWriter().append(order.toString());
//			}
			
			//select all
			StringBuilder sb = new StringBuilder();
			List<OrderDetail> list = service.getAll();
			Consumer<OrderDetail> orderConsumer = p -> {
				sb.append(p.toString());
				sb.append("<br>");
			};
			list.stream().forEach(orderConsumer);
			res.getWriter().append(sb);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
