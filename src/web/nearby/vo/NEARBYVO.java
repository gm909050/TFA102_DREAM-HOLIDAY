package web.nearby.vo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;



public class NEARBYVO {
	
	private Integer nearbyid;
	private String nearbyname;
	private String nearbyexplanation;
	private byte[] nearby;
	private String nearby64;

	
	public Integer getNearbyid() {
		return nearbyid;
	}

	public void setNearbyid(Integer nearbyid) {
		this.nearbyid = nearbyid;
	}

	public String getNearbyname() {
		return nearbyname;
	}

	public void setNearbyname(String nearbyname) {
		this.nearbyname = nearbyname;
	}

	public String getNearbyexplanation() {
		return nearbyexplanation;
	}

	public void setNearbyexplanation(String nearbyexplanation) {
		this.nearbyexplanation = nearbyexplanation;
	}

	public byte[] getNearby() {
		return nearby;
	}

	public void setNearby(byte[] nearby) {
		this.nearby = nearby;
	}

	public byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()]; //檔案大小長度
		fis.read(buffer); //將位元讀入BYTE陣列
		fis.close();
		return buffer;
	}
	

	
}

