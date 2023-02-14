
public class Ex05ReferenceType01 {

	public static void main(String[] args) {
		
		// 1-1. primitive type 변수 사용
		int x1;
		x1 = 10;
		int x2 = 10;
		System.out.printf("%d / %d\n", x1, x2);
		System.out.printf("x1 == x2 : %b\n", x1 == x2); 		// primitive 타입의 비교연산자는 항상 값을 비교
		
		// 1-2. 참조형 변수 사용
		String s1;					// stack에 참조 변수 만들기 (주소를 저장하는 변수)
		s1 = new String("홍길동"); 	// heap에 데이터 저장 변수 만들기 + 만들어진 변수의 주소를 s1에 저장
		String s2 = new String("홍길동");
		
		System.out.printf("%s / %s\n", s1, s2);
		System.out.printf("s1 == s2 : %b\n", s1 == s2);			// 문자열의 비교연산자는 주소 비교
		System.out.printf("s1 == s2 : %b\n", s1.equals(s2));	// 문자열의 equals는 값을 비교 
		
		//2. null
		String s3; // s3에는 Garbage 데이터 저장
		//System.out.println(s3);
		s3 = null; // s3이 참조하는 값 변수가 없음 ( null : 참조하는 대상이 없음 )
		System.out.println(s3.toString()); // null 상태의 참조변수를 사용하면 NullPointerException 오류 발생

	}

}
