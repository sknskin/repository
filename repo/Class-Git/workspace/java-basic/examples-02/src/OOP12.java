

class TheParent {
	private String p;
	void m() {
		System.out.println("TheParent.m");
	}
	public TheParent() { // 생성자 메서드 (constructor) : 인스턴스를 만들 때(new 할 때) 자동으로 호출
		System.out.println("TheParent's constructor");
	}
}

class TheChild extends TheParent { // TheParent 상속 : TheParent의 모든 멤버를 자동으로 포함
	
	// 상속 받는 클래스는 두 가지 중 한 가지는 포함
	// 1. 확장 : 새로운 멤버 변수 또는 메서드 추가
	// 2. 변경 : 기존 메서드 변경
	
	String c;
	void m2() {
		// p = "Parent's member"; // 오류 : 부모의 private 멤버는 사용할 수 없습니다.
		System.out.println("TheChild.m2");
	}
	public TheChild() { // 생성자 메서드 (constructor) : 인스턴스를 만들 때(new 할 때) 자동으로 호출
		System.out.println("TheChild's constructor");
	}
	
	@Override
	void m() { // 메서드 재정의 : 메서드이름, 전달인자, 결과형은 부모의 메서드와 동일하게 -> 내용은 다르게
		System.out.println("자식 클래스에서 재정의된 m()");
		// super.m(); // 부모클래스의 메서드를 명시적으로 호출
	}
}

public class OOP12 {

	public static void main(String[] args) {
		
		// 1. 
		TheChild c1 = new TheChild(); // TheParent 생성 -> TheChild 생성
		c1.m(); // 상속받았기 때문에 TheParent의 멤버 m() 사용 가능
		c1.m2(); // 확장한 멤버 사용
		
		// 2. 참조타입과 인스턴스 타입이 다를 수 있습니다. ( 부모참조 -> 자식인스턴스 )
		TheParent p2 = new TheChild();
		// TheChild c2 = (TheChild)new TheParent(); // 허용되지 않습니다. ( 자식참조 -> 부모인스턴스 )
		TheChild c3 = (TheChild)p2; // (자식참조 -> 부모)인 것처럼 보이지만 실제로는 (자식참조 -> 자식인스턴스) 상황
		
		TheParent p3 = new TheParent();
		if (p3 instanceof TheChild) { // instanceof : 형변환 가능한지 확인 ( 안전한 형변환 도구 )
			c3 = (TheChild)p3;
		} else {
			System.out.println("형변환 불가 !!!!");
		}
		
		// 3. 재정의 메서드 사용
		TheParent p4 = new TheParent();
		TheChild c4 = new TheChild();
		p4.m();	// 부모 클래스의 메서드 호출
		c4.m(); // 자식 클래스에서 재정의한 메서드 호출
		
		System.out.println("============================");
		
		// 4. (재정의) 메서드 호출 기준
		TheParent p5;
		p5 = new TheChild();
		p5.m(); // 부모 메서드 or 자식 메서드 -> 자식 메서드 (인스턴스 타입 기준)
		p5 = new TheParent();
		p5.m();
		
		

	}

}
