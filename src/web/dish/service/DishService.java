package web.dish.service;

import java.util.List;

import web.dish.vo.Dish;

public interface DishService {
	boolean addDish(Dish dish);
	boolean updateDish(Dish dish);
	boolean deleteDish(Integer id);
	Dish getDishByKey(Integer id);
	List<Dish> getAll();
	List<Dish> getAllOnShelves();
	
	byte[] getPictureByteArray(String path) throws Exception;
}
