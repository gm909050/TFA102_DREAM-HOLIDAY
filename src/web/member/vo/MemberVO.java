package web.member.vo;

public class MemberVO {
	private Integer memberId;		//�|���s��
	private String name;			//�m�W
	private String idNumber;		//�����Ҹ��X
	private String phone;			//�p���q��
	private String email;			//�q�l�H�c
	private String password;		//�K�X
	
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
