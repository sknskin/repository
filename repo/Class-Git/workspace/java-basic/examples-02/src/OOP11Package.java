import java.util.Scanner;

import com.examples.oop1.A; // 이후에 A를 사용하면 com.examples.oop1.A 입니다.
import com.examples.oop2.B;

public class OOP11Package {

	public static void main(String[] args) {
		
		// 1.
		// A a = new A(); // 오류 : 패키지에 소속된 클래스는 패키지 이름까지 명시해야 합니다.		
		com.examples.oop1.A a = new com.examples.oop1.A();
		
		java.util.Scanner scanner = new java.util.Scanner(System.in); // Scanner는 java.util 패키지에 소속
		
		// 2. 패키지명 생량 방법
		A a2 = new A();
		B b2 = new B();
		
		Scanner scanner2 = new Scanner(System.in);

	}

}
