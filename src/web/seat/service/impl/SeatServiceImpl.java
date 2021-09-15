package web.seat.service.impl;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import web.seat.dao.SeatDao;
import web.seat.dao.impl.SeatDaoImpl;
import web.seat.service.SeatService;
import web.seat.vo.Seat;

public class SeatServiceImpl implements SeatService {
	
	private SeatDao dao;
	
	public SeatServiceImpl() {
		dao = new SeatDaoImpl();
	}

	@Override
	public boolean addSeat(Seat seat) {
		return dao.insert(seat) > 0;
	}

	@Override
	public boolean updateSeat(Seat seat) {
		return dao.updateByKey(seat) > 0;
	}

	@Override
	public boolean deleteSeat(String seatCode) {
		return dao.deleteByKey(seatCode) > 0;
	}

	@Override
	public Seat getSeatByKey(String seatCode) {
		return dao.selectByKey(seatCode);
	}

	@Override
	public List<Seat> getAll() {
		return dao.selectAll();
	}

	@Override
	public List<Seat> getSeatAllFromRedis() {
		Jedis jedis = new Jedis("localhost", 6379);
		
		List<Seat> seatList = getAll();
		for(Seat seatVO : seatList) {
			String seatType = jedis.get(seatVO.getSeatCode()+":1");
			String seatStatus = jedis.get(seatVO.getSeatCode()+":2");
			
			if(seatType == null || seatStatus == null) {
				break;
			} else {
				seatVO.setSeatType(Integer.valueOf(seatType));
				seatVO.setSeatStatus(Integer.valueOf(seatStatus));
			}
		}
		return seatList;
	}
}
