package web.member.vo;

public class MemberVO {
	private Integer memberId;		//會員編號
	private String name;			//姓名
	private String idNumber;		//身分證號碼
	private String phone;			//聯絡電話
	private String email;			//電子信箱
	private String password;		//密碼
	
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId +
				", name=" + name +
				", idNumber=" + idNumber +
				", phone=" + phone+
				", email=" + email +
				", password=" + password + "]";
	}
	
	
}
