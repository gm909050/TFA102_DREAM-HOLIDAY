package web.dish.vo;

import java.io.Serializable;
import java.util.Arrays;

public class Dish implements Serializable {
	private Integer dishId;			//���a�s��
	private String dishName;		//���a�W��
	private Integer dishPrice;		//���a����
	private String dishIntro;		//���a����
	private Integer dishType;		//���a���O
	private Integer dishStatus;		//���a���A
	private byte[] dishPicture;		//���a�Ϥ�
	
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
