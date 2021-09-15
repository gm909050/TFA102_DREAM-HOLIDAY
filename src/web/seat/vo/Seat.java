package web.seat.vo;

import java.io.Serializable;

public class Seat implements Serializable{
	private String seatCode;		//座位代號
	private Integer seatType;		//座位人數
	private Integer seatStatus;		//座位狀態
	
	public String getSeatCode() {
		return seatCode;
	}
	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
	public Integer getSeatType() {
		return seatType;
	}
	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
	}
	public Integer getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(Integer seatStatus) {
		this.seatStatus = seatStatus;
	}
	
	@Override
	public String toString() {
		return "Seat [seatCode=" + seatCode +
								", seatType=" + seatType +
								", seatStatus=" + seatStatus + "]";
	}
	
	
}
