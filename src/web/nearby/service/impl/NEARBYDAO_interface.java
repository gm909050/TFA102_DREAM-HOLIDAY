package web.nearby.service.impl;

import java.util.List;

import web.nearby.vo.NEARBYVO;

public interface NEARBYDAO_interface {
	
    public void insert(NEARBYVO NEARBYVO); //新增
    public void update(NEARBYVO NEARBYVO); //修改更新
    public void delete(Integer venueid); //刪除
    public NEARBYVO findByPrimaryKey(Integer venueid);//Pk
    public List<NEARBYVO> getThree();
    public List<NEARBYVO> getAll();
}
