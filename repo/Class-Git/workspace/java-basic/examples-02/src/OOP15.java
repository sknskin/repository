
interface TheInterface {
	
	/* public static final */ int X = 10; // 모든 field는 자동으로 public static final
	
	/* public abstract */ void m1(); // 모든 메서드는 public abstract
	/* public abstract */ void m2();
	
}
interface TheInterface2 {
	
}

class TheImplement implements TheInterface, TheInterface2 {	// 인터페이스는 다른 클래스를 통해 구현 ( 다중 인터페이스 구현 가능 )
															// 구현 클래스는 인터페이스의 모든 추상 메서드를 재정의해야 합니다.

	@Override
	public void m1() {
		System.out.println("m1");
	}

	@Override
	public void m2() {
		System.out.println("m2");
	} 
	
}

public class OOP15 {

	public static void main(String[] args) {
		
		// 1. interface는 instance 생성 불가능 / 참조 변수는 선언 가능
		TheInterface ti;
		// ti = new TheInterface(); // 오류 : 인터페이스의 인스턴스 생성 불가능
		
		// 2. 
		TheInterface ti2 = new TheImplement();
		ti2.m1();
		ti2.m2();

	}

}
