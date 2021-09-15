package web.order.service;

import java.util.List;

import web.order.vo.OrderDetail;

public interface OrderDetailService {
	boolean addOrder(OrderDetail order);
	boolean updateOrder(OrderDetail order);
	boolean deleteOrder(Integer id);
	OrderDetail getOrderDetailByKey(Integer id);
	List<OrderDetail> getAll();
	
	List<OrderDetail> getAllByMealId(Integer mealId);
	boolean addOrderList(List<OrderDetail> list, Integer totalPrice, Integer mealId);
}
