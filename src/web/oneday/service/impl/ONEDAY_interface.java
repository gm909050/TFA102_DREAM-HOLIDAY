package web.oneday.service.impl;

import java.util.List;

import web.oneday.vo.ONEDAYVO;

public interface ONEDAY_interface {
	
    public void insert(ONEDAYVO ONEDAYVO); //�s�W
    public void update(ONEDAYVO ONEDAYVO); //�ק��s
    public void delete(Integer venueId); //�R��
    public ONEDAYVO findByPrimaryKey(Integer venueId);//Pk
    public List<ONEDAYVO> getAll();
}
