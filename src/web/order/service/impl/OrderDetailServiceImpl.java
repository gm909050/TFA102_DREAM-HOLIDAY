package web.order.service.impl;

import java.util.List;

import web.order.dao.OrderDetailDao;
import web.order.dao.impl.OrderDetailDaoImpl;
import web.order.service.OrderDetailService;
import web.order.vo.OrderDetail;

public class OrderDetailServiceImpl implements OrderDetailService {
	OrderDetailDao dao;
	
	public OrderDetailServiceImpl() {
		dao = new OrderDetailDaoImpl();
	}

	@Override
	public boolean addOrder(OrderDetail order) {
		return dao.insert(order) > 0;
	}

	@Override
	public boolean updateOrder(OrderDetail order) {
		return dao.updateByKey(order) > 0;
	}

	@Override
	public boolean deleteOrder(Integer id) {
		return dao.deleteByKey(id) > 0;
	}

	@Override
	public OrderDetail getOrderDetailByKey(Integer id) {
		return dao.selectByKey(id);
	}

	@Override
	public List<OrderDetail> getAll() {
		return dao.selectAll();
	}

	@Override
	public List<OrderDetail> getAllByMealId(Integer mealId) {
		return dao.getDetailByMealId(mealId);
	}

	@Override
	public boolean addOrderList(List<OrderDetail> list, Integer totalPrice, Integer mealId) {
		return dao.insertBatchOrdersToDB(list, totalPrice, mealId);
	}
}
