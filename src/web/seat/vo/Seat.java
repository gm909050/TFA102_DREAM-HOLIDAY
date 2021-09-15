package web.seat.vo;

import java.io.Serializable;

public class Seat implements Serializable{
	private String seatCode;		//�y��N��
	private Integer seatType;		//�y��H��
	private Integer seatStatus;		//�y�쪬�A
	
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
