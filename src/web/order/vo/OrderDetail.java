package web.order.vo;

public class OrderDetail {
	private Integer orderId;
	private Integer mealId;
	private Integer dishId;
	private Integer dishPrice;
	private Integer dishQty;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getMealId() {
		return mealId;
	}
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}
	public Integer getDishId() {
		return dishId;
	}
	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}
	public Integer getDishPrice() {
		return dishPrice;
	}
	public void setDishPrice(Integer dishPrice) {
		this.dishPrice = dishPrice;
	}
	public Integer getDishQty() {
		return dishQty;
	}
	public void setDishQty(Integer dishQty) {
		this.dishQty = dishQty;
	}
	@Override
	public String toString() {
		return "OrderDetail [orderId=" + orderId +
							", mealId=" + mealId +
							", dishId=" + dishId +
							", dishPrice=" + dishPrice +
							", dishQty=" + dishQty + "]";
	}
	
	
}
