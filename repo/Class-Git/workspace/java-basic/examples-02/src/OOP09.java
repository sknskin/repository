

// 클래스 : 구현 대상의 문법적 표현, 설계도, 사용자 정의 자료형
// 클래스를 만드는 것으로는 어떤 실행도 발생하지 않습니다.
class Person9 {	

	// static member : static keyword 사용, 한 번만 만들고 모든 인스턴스가 공유
	private static int nextNo = 1;
	public static int getNextNo() {
		// System.out.println(no); // 오류 : static method 영역에서 instance member 사용 불가능
		return nextNo;
	}
	public static void setNextNo(int nextNo) {
		Person9.nextNo = nextNo;
	}
	
	// instance member : default, new 할 때마다 매번 새로 만들고 인스턴스별로 각각 사용
	private int no; // 고유 식별값으로 사용 ( 자동 증가 번호 ) 	 
	private String name;
	private String phone;
	private String email;
	
	
	public Person9() {
		no = nextNo;
		nextNo++;
		System.out.println("1. 전달인자 없는 생성자 메서드 호출");
	}	
	public Person9(int no, String name, String phone, String email) {
		this(); 
		this.no = no;
		this.name = name;
		this.phone = phone;
		this.email = email;
		System.out.println("2. 전달인자 있는 생성자 메서드 호출");
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
	
	public String info() {
		String info2 = String.format("[%d][%s][%s][%s]", no, name, phone, email);
		return info2;  
	}	
}
public class OOP09 {	
	public static void main(String[] args) {		
		
//		Person9 p1 = new Person9();
//		p1.setNextNo(1);	// 공유 nextNo 사용 ( 다른 인스턴스에 영향을 미칩니다. )
//		p1.setNo(1);		// 개별 no 사용 ( 다른 인스턴스에 영향을 미치지 않습니다. )
//		
//		Person9 p2 = new Person9();
//		p2.setNextNo(2);	// 모든 인스턴스의 nextNo 변경
//		p2.setNo(2);		// p2 인스턴스의 no 변경
//		
//		System.out.println(p1.getNextNo() + " / " + p2.getNextNo());
//		System.out.println(p1.getNo() + " / " + p2.getNo());
//		
//		Person9.setNextNo(100); // Static member는 클래스로 접근
//		System.out.println(p1.getNextNo() + " / " + p2.getNextNo());
		
		/////////////////////
		// 자동 증가 번호 테스트
		Person9 p3 = new Person9();
		Person9 p4 = new Person9();
		Person9 p5 = new Person9();
		System.out.println(p3.info());
		System.out.println(p4.info());
		System.out.println(p5.info());

	}

}










