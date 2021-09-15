package web.seat.service;

import java.util.List;

import web.seat.vo.Seat;

public interface SeatService {
	boolean addSeat(Seat seat);
	boolean updateSeat(Seat seat);
	boolean deleteSeat(String seatCode);
	Seat getSeatByKey(String seatCode);
	List<Seat> getAll();
	
	List<Seat> getSeatAllFromRedis();
}
