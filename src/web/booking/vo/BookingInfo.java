package web.booking.vo;

import java.sql.Date;

public class BookingInfo {
	private Integer mealId;			//�����s��
	private String seatCode; //FK	//�y��N��
	private Integer memberId; //FK	//�|���s��
	private Date mealDate;			//���\���
	private Integer mealTime;		//���\�ɬq
	private Integer bookingStatus;	//�q�쪬�A
	private Integer payBill;		//�����`�B
	private Integer bookingSeattype;//�w���y�H��
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
