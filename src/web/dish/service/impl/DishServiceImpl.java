package web.dish.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import web.dish.dao.DishDao;
import web.dish.dao.impl.DishDaoImpl;
import web.dish.service.DishService;
import web.dish.vo.Dish;

public class DishServiceImpl implements DishService {
	
	DishDao dao;
	
	public DishServiceImpl() {
		dao = new DishDaoImpl();
	}

	@Override
	public boolean addDish(Dish dish) {
		return dao.insert(dish) > 0;
	}

	@Override
	public boolean updateDish(Dish dish) {
		return dao.updateByKey(dish) > 0;
	}

	@Override
	public boolean deleteDish(Integer id) {
		return dao.deleteByKey(id) > 0;
	}

	@Override
	public Dish getDishByKey(Integer id) {
		return dao.selectByKey(id);
	}

	@Override
	public List<Dish> getAll() {
		return dao.selectAll();
	}
	
	@Override
	public List<Dish> getAllOnShelves() {
		return dao.getOrderMenuOnShelf();
	}

	@Override
	public byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	
}
