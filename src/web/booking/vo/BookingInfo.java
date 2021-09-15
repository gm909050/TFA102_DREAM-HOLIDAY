package web.booking.vo;

import java.sql.Date;

public class BookingInfo {
	private Integer mealId;			//場次編號
	private String seatCode; //FK	//座位代號
	private Integer memberId; //FK	//會員編號
	private Date mealDate;			//用餐日期
	private Integer mealTime;		//用餐時段
	private Integer bookingStatus;	//訂位狀態
	private Integer payBill;		//價格總額
	private Integer bookingSeattype;//預約座人數
	public Integer getMealId() {
		return mealId;
	}
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}
	public String getSeatCode() {
		return seatCode;
	}
	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Date getMealDate() {
		return mealDate;
	}
	public void setMealDate(Date mealDate) {
		this.mealDate = mealDate;
	}
	public Integer getMealTime() {
		return mealTime;
	}
	public void setMealTime(Integer mealTime) {
		this.mealTime = mealTime;
	}
	public Integer getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(Integer bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Integer getPayBill() {
		return payBill;
	}
	public void setPayBill(Integer payBill) {
		this.payBill = payBill;
	}
	public Integer getBookingSeattype() {
		return bookingSeattype;
	}
	public void setBookingSeattype(Integer bookingSeattype) {
		this.bookingSeattype = bookingSeattype;
	}
	@Override
	public String toString() {
		return "BookingInfo [mealId=" + mealId +
				", seatCode=" + seatCode +
				", memberId=" + memberId +
				", mealDate=" + mealDate +
				", mealTime=" + mealTime +
				", bookingStatus=" + bookingStatus +
				", payBill=" + payBill +
				", bookingSeattype=" + bookingSeattype + "]";
	}
}
