package web.order.dao;

import java.util.List;

import web.basic.dao.BasicDao;
import web.order.vo.OrderDetail;

public interface OrderDetailDao extends BasicDao<OrderDetail, Integer> {
	List<OrderDetail> getDetailByMealId(Integer mealId);
	boolean insertBatchOrdersToDB(List<OrderDetail> list, Integer totalPrice, Integer mealId);
}
