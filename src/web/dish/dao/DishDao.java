package web.dish.dao;

import java.util.List;

import web.basic.dao.BasicDao;
import web.dish.vo.Dish;

public interface DishDao extends BasicDao<Dish, Integer> {
	List<Dish> getOrderMenuOnShelf();
}
