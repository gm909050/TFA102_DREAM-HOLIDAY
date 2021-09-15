package web.oneday.service.impl;

import java.util.List;

import web.oneday.vo.ONEDAYVO;

public interface ONEDAY_interface {
	
    public void insert(ONEDAYVO ONEDAYVO); //新增
    public void update(ONEDAYVO ONEDAYVO); //修改更新
    public void delete(Integer venueId); //刪除
    public ONEDAYVO findByPrimaryKey(Integer venueId);//Pk
    public List<ONEDAYVO> getAll();
}
