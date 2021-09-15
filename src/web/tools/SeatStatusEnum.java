package web.tools; 

public enum SeatStatusEnum {
	MAINTAIN("ºû­×¤¤", "1"), IDLE("¶¢¸m®u", "2"), PRE_ORDER("«ÝÂIÀ\", "3"), ORDER("ÂIÀ\", "4"), CHECKOUT("µ²±b", "5");
	
	private String status;
	private String idx;
	private SeatStatusEnum(String status, String idx) {
		this.status = status;
		this.idx = idx;
	}
	
	public static String getName(String index) {
		for(SeatStatusEnum stat : SeatStatusEnum.values()) {
			if(stat.getIdx().equals(index))
				return stat.status;
		}
		return null;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
}
