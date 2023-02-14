
abstract class TheAbstract { // 추상클래스 : instance 생성 불가능, 추상메서드를 가진 클래스
	
	public int x; 		// 추상클래스에 비추상 멤버 포함 가능
	public void m2() {}	// 추상클래스에 비추상 멤버 포함 가능
	
	public abstract void m(); // 추상메서드 : 본문이 없는 메서드
	
}

class TheConcrete extends TheAbstract {	// 추상 클래스는 상속을 통해서 사용(구체화)됩니다.
										// 추상 클래스를 상속한 클래스는 반드시 추상 메서드를 재정의(구현) 해야 합니다.

	@Override
	public void m() {
		System.out.println("추상 메서드 재정의 (구현)");		
	} 
	
}

public class OOP14 {

	public static void main(String[] args) {
		
		//1. 추상클래스의 인스턴스 생성 불가능 (참조 변수는 가능)
		TheAbstract a;
		// a = new TheAbstract(); // 오류
		
		//2. 추상클래스 사용 : 추상클래스타입 참조변수 = 자식클래스타입 인스턴스
		TheAbstract a2;
		a2 = new TheConcrete();
		a2.m();
		a2.m2();

	}

}
