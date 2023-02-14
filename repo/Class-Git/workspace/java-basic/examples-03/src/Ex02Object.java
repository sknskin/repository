import java.io.Serializable;
import java.util.Scanner;

class MyClass /* extends Object */ implements Serializable { // extends Object를 생략해도 자동으로 Object를 상속하도록 처리됩니다.
	
	int x;
	
	@Override
	public boolean equals(Object obj) {
		MyClass other = (MyClass)obj;
		return this.x == other.x;
	}
	
	@Override
	public int hashCode() {
		return x;
	}

	@Override
	public String toString() { // 객체(인스턴스)의 정보를 간단한 문자열로 반환하는 메서드
		return String.format("[X : %d]", x);
	}
}
public class Ex02Object {

	public static void main(String[] args) {

		MyClass mc1 = new MyClass();
		mc1.x = 1;
		
		MyClass mc2 = new MyClass();
		mc2.x = 1;
		
		System.out.println(mc1 == mc2);
		System.out.println(mc1.equals(mc2)); // Object.equals() 기본 구현은 참조 비교
		
		//////////////////////////
		
		System.out.println(mc1.toString());	// Object.toString() 기본 구현은 클래스이름@주소 출력
		System.out.println(mc1);			// 문자열이 필요한 곳에서 자동으로 객체의 toString() 메서드 호출
		
		
		//////////////////////////
		
		// Object 형식의 참조 변수는 모든 인스턴스를 참조할 수 있습니다.
		Object obj = "Hello";
		obj = new MyClass();
		obj = new Scanner(System.in);

		Serializable sz = new MyClass();
		obj = sz;
		//////////////////////////
		
		System.out.println(mc1.hashCode());
		
	}

}
