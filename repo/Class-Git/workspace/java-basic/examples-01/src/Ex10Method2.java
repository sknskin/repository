
public class Ex10Method2 {

	public static void main(String[] args) {

		int result = add(10, 20);
		System.out.printf("%d + %d = %d\n", 10, 20, result);
		
		add(100, 200); // 반환 값이 있다고해서 호출 영역의 코드가 반드시 받아야하는 것은 아닙니다.
		// String result2 = add(100, 200);
		
		result = add2(10, 20, 30, 40); // 호출 시점에 전달인자의 갯수 결정
		System.out.println(result);
		
		result = add2(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println(result);
		
		// overloading : 이름은 같지만 전달인자의 자료형과 갯수를 사용해서 서로 다른 메서드로 구분하는 기능
		result = add(1, 2); // 전달인자 2개를 사용하는 add 호출
		System.out.println(result);
		
		result = add(1, 2, 3); // 전달인자 3개를 사용하는 add 호출
		System.out.println(result);
	
		result = add(1, 2, 3, 4); // 전달인자 4개를 사용하는 add 호출
		System.out.println(result);

	}
	
	// 전달인자는 여러 개 사용 가능, return은 단일 값만 반환 ( 여러 개의 값을 반환하려면 배열에 담아서 반환 )
	static int add(int a, int b) { //결과형 : 이 메서드가 return하는 값의 자료형 지정 ( void : 반환 값이 없음을 의미 )
		int result = a + b;
		return result; // result에 저장된 값을 호출한 곳으로 전달(반환)하고 메서드 종료하는 명령
		               
	}
	
	static int add(int a, int b, int c) {
		int result = a + b + c;
		return result;
	}
	static int add(int a, int b, int c, int d) {
		int result = a + b + c + d;
		return result; 
	}
	
	static int add2(int ... params) { // 전달인자의 갯수를 사전에 지정하지 않고 호출하는 시점에 전달된 데이터를 배열로 자동 변환해서 받는 형식
		int result = 0;
		for (int i = 0; i < params.length; i++) {
			result += params[i];
		}
		return result;
	}

}
