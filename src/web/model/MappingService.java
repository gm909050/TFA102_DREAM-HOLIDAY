package web.model;

import java.util.List;

public class MappingService {
	SeatTypeDao seattypeDao = null;
	SeatStatusDao seatStatusDao = null;
	DishTypeDao dishTypeDao = null;
	DishStatusDao dishStatusDao = null;
	
	public MappingService() {
		seattypeDao = new SeatTypeDao();
		seatStatusDao = new SeatStatusDao();
		dishTypeDao = new DishTypeDao();
		dishStatusDao = new DishStatusDao();
	}
	
	public MappingSeatType getSeatTypeByKey(Integer seatType) {
		return seattypeDao.selectByKey(seatType);
	}
	
	public List<MappingSeatType> getSeatTypeAll() {
		return seattypeDao.selectAll();
	}
	
	public MappingSeatStatus getSeatStatusByKey(Integer seatStatus) {
		return seatStatusDao.selectByKey(seatStatus);
	}
	
	public List<MappingSeatStatus> getSeatStatusAll() {
		return seatStatusDao.selectAll();
	}
	
	public MappingDishType getDishTypeByKey(Integer dishType) {
		return dishTypeDao.selectByKey(dishType);
	}
	
	public List<MappingDishType> getDishTypeAll() {
		return dishTypeDao.selectAll();
	}
	
	public MappingDishStatus getDishStatusByKey(Integer dishStatus) {
		return dishStatusDao.selectByKey(dishStatus);
	}
	
	public List<MappingDishStatus> getDishStatusAll() {
		return dishStatusDao.selectAll();
	}
}
