import java.io.Serializable;

// 연락처 정보 저장용 클래스
class Contact implements Serializable { // 파일에 저장할 객체의 타입은 Serializable 인터페이스 구현해야 합니다.
	private int no;		 
	private String name;
	private String phone;
	private String email;

	public Contact() {}
	public Contact(int no, String name, String phone, String email) {
		this.no = no;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	@Override
	public String toString() {
		return String.format("[%d][%s][%s][%s]", no, name, phone, email);
	}
}