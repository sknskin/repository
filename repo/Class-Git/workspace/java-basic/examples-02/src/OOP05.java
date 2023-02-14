

// 클래스 : 구현 대상의 문법적 표현, 설계도, 사용자 정의 자료형
// 클래스를 만드는 것으로는 어떤 실행도 발생하지 않습니다.
class Person5 {	
	// 1. 특성
	private int no = 100; // 필드 초기화 ( 생성자 보다 먼저 실행되는 초기화 구문 )		 
	private String name;
	private String phone;
	private String email;
	
	// 생성자 메서드 (Constructor)
	// 1. 클래스이름과 같아야합니다 + 결과형은 없음 + 오버로딩가능 (여러 개 생성 가능)
	// 2. 인스턴스 만들 때 (new 할 때, 객체 만들 때) 자동으로 호출
	public Person5() {
		System.out.println("1. 전달인자 없는 생성자 메서드 호출");
	}	
	public Person5(int no, String name, String phone, String email) {// 생성자 메서드의 전달인자는 초기화하려는 값으로 구성
		this(); // 같은 클래스의 전달인자 없는 생성자 메서드 호출 
		this.no = no;
		this.name = name;
		this.phone = phone;
		this.email = email;
		System.out.println("2. 전달인자 있는 생성자 메서드 호출");
	}
	// 메서드를 사용해서 데이터 접근 : 잘못된 데이터의 유입을 막을 수 있습니다.
	public int getNo() {	// 변수이름 -> get or set + 첫번째문자(대문자) + 나머지변수이름
		return no;
	}
	public void setNo(int no) {
		this.no = no; // this.no는 객체(인스턴스)의 멤버변수 no를 의미하는 표현
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
	
	// 2. 기능
	public String info() { // 연락처 정보 반환
		String info2 = String.format("[%d][%s][%s][%s]", no, name, phone, email);
		return info2;  
	}	
}
public class OOP05 {	
	public static void main(String[] args) {		
		
		Person5 person = new Person5(); // new Person5() -> 전달인자 없는 생성자 메서드 자동 호출
		System.out.println(person.info());
		
		Person5 person2 = new Person5(1, "김윤석", "010-5499-6712", "kys@example.com");
		System.out.println(person2.info());
		
		/////////////////////////////
		// 생성자 메서드 사용 사례
		String s = new String("TEST STRING");
		java.util.Scanner scanner = new java.util.Scanner(System.in);

	}

}










