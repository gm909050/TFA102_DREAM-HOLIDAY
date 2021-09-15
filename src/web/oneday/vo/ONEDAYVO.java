package web.oneday.vo;

import java.io.FileOutputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;

public class ONEDAYVO {
	
	private Integer pictureid;
	private String picturename;
	private String pictureexplanation;
	private byte[] picture;
	private String picture64;
	
	
	
	public String getPicture64() {
		return picture64;
	}
	public void setPicture64(String picture64) {
		this.picture64 = picture64;
	}
	public Integer getPictureid() {
		return pictureid;
	}
	public void setPictureid(Integer pictureid) {
		this.pictureid = pictureid;
	}
	public String getPicturename() {
		return picturename;
	}
	public void setPicturename(String picturename) {
		this.picturename = picturename;
	}
	public String getPictureexplanation() {
		return pictureexplanation;
	}
	public void setPictureexplanation(String pictureexplanation) {
		this.pictureexplanation = pictureexplanation;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public byte[] getPictureByteArray(String string) {
	
		return null;
	}
	

	
}
