package web.nearby.service.impl;

import java.util.List;

import web.nearby.vo.NEARBYVO;

public interface NEARBYDAO_interface {
	
    public void insert(NEARBYVO NEARBYVO); //�s�W
    public void update(NEARBYVO NEARBYVO); //�ק��s
    public void delete(Integer venueid); //�R��
    public NEARBYVO findByPrimaryKey(Integer venueid);//Pk
    public List<NEARBYVO> getThree();
    public List<NEARBYVO> getAll();
}
