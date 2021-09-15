package web.oneday.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import web.oneday.dao.ONEDAYJNDIDAO;
import web.oneday.service.impl.ONEDAY_interface;
import web.oneday.vo.ONEDAYVO;



public class ONEDAYService {
	
	private ONEDAY_interface dao ;
	
	public ONEDAYService(){
		dao = new ONEDAYJNDIDAO();
		
	}
	
	public ONEDAYVO insert(String picturename,String pictureexplanation,byte[] picture ) {
		ONEDAYVO one = new ONEDAYVO();
		one.setPicturename(picturename);
		one.setPictureexplanation(pictureexplanation);
		one.setPicture(picture);
		dao.insert(one);
		return one;	
	}
	
	public ONEDAYVO update(String picturename, String pictureexplanation, byte[] picture,Integer pictureid) {
		ONEDAYVO one = new ONEDAYVO();
		one.setPicturename(picturename);
		one.setPictureexplanation(pictureexplanation);
		one.setPicture(picture);
		one.setPictureid(pictureid);
		dao.update(one);
		return one;
	}
	
	public ONEDAYVO delete(Integer pictureid) {
		ONEDAYVO one = new ONEDAYVO();
		one.setPictureid(pictureid);
		dao.delete(one.getPictureid());
		return one;
	}
	
	public ONEDAYVO findByPrimaryKey(Integer pictureid) {
		return dao.findByPrimaryKey(pictureid);
	}

	public List<ONEDAYVO> getAll() {
		return dao.getAll();
	}
	
	public byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()]; //檔案大小長度
		fis.read(buffer); //將位元讀入BYTE陣列
		fis.close();
		return buffer;
	}
}
