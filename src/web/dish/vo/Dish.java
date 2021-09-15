package web.dish.vo;

import java.io.Serializable;
import java.util.Arrays;

public class Dish implements Serializable {
	private Integer dishId;			//µæÀa½s¸¹
	private String dishName;		//µæÀa¦WºÙ
	private Integer dishPrice;		//µæÀa»ù®æ
	private String dishIntro;		//µæÀa¤¶²Ð
	private Integer dishType;		//µæÀaÃþ§O
	private Integer dishStatus;		//µæÀaª¬ºA
	private byte[] dishPicture;		//µæÀa¹Ï¤ù
	
	public Integer getDishId() {
		return dishId;
	}
	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public Integer getDishPrice() {
		return dishPrice;
	}
	public void setDishPrice(Integer dishPrice) {
		this.dishPrice = dishPrice;
	}
	public String getDishIntro() {
		return dishIntro;
	}
	public void setDishIntro(String dishIntro) {
		this.dishIntro = dishIntro;
	}
	public Integer getDishType() {
		return dishType;
	}
	public void setDishType(Integer dishType) {
		this.dishType = dishType;
	}
	public Integer getDishStatus() {
		return dishStatus;
	}
	public void setDishStatus(Integer dishStatus) {
		this.dishStatus = dishStatus;
	}
	public byte[] getDishPicture() {
		return dishPicture;
	}
	public void setDishPicture(byte[] dishPicture) {
		this.dishPicture = dishPicture;
	}
	@Override
	public String toString() {
		return "Dish [dishId=" + dishId +
				", dishName=" + dishName +
				", dishPrice=" + dishPrice +
				", dishIntro=" + dishIntro +
				", dishType=" + dishType +
				", dishStatus=" + dishStatus +
				", dishPicture=" + Arrays.toString(dishPicture) + "]";
	}
}
