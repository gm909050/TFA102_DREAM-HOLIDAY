package web.nearby.service;

import java.util.ArrayList;
import java.util.List;

import web.nearby.dao.NEARBYJNDIDAO;
import web.nearby.service.impl.NEARBYDAO_interface;
import web.nearby.vo.NEARBYVO;

public class NEARBYService {
	
	private NEARBYDAO_interface dao;
	
	public NEARBYService() {
		dao = new NEARBYJNDIDAO();
	};
	
	public NEARBYVO insert(String nearbyname,String nearbyexplanation,byte[] nearby ) {
		NEARBYVO one = new NEARBYVO();
		one.setNearbyname(nearbyname);
		one.setNearbyexplanation(nearbyexplanation);
		one.setNearby(nearby);
		dao.insert(one);
		return one;	
	}
	
	public NEARBYVO update(String nearbyname,String nearbyexplanation,byte[] nearby ,Integer nearbyid) {
		NEARBYVO one = new NEARBYVO();
		one.setNearbyname(nearbyname);
		one.setNearbyexplanation(nearbyexplanation);
		one.setNearby(nearby);
		one.setNearbyid(nearbyid);
		dao.update(one);
		return one;	
	}
	
	public NEARBYVO delete(Integer nearbyid) {
		NEARBYVO one = new NEARBYVO();
		one.setNearbyid(nearbyid);
		dao.delete(nearbyid);
		return one;	
	}
	
	public List<NEARBYVO> getAll() {
		return dao.getAll();
	}
	
	public List<NEARBYVO> getThree() {
		List<NEARBYVO> list = new ArrayList<NEARBYVO>();
		list = dao.getThree();
		return list;
	}
}
