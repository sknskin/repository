
public class Ex02DataTypeAndVariables {

	public static void main(String[] args) {
		
		int x; // 4byte 정수형 공간, 이름은 x
		x = 10; // 변수에 데이터 저장
		System.out.println(x);
		
		double y = 11.11; 	// 변수 만들기 + 데이터 저장 (초기화)
		double z = y;		// y변수에 저장된 데이터를 읽어서 z 변수에 저장
		System.out.println(z);
		
		int o = 1, p = 2, q = 3; // 같은 자료형의 변수는 한번에 선언 가능
		
		int s;
		// int t = s + 10; //오류 : 초기화되지 않은 변수 사용할 수 없습니다.
		
		int s2 = 100;
		int t2 = s2 + 10; 
		

	}

}
