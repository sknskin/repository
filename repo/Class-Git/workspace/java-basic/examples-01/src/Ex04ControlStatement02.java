
public class Ex04ControlStatement02 {

	public static void main(String[] args) {
		
//		// 1-1. 난수 발생기 사용
//		double number = Math.random(); // Math.random() : 0 <= n < 1 범위의 난수 발생
//		System.out.println("난수 1 : " + number);
//		
//		// 1-2. 1 ~ 100 범위의 정수형 난수 만들기
//		double number2 = Math.random() * 99; // 0 ~ 99 (실수)
//		number2 = number2 + 1; // 1 ~ 100 (실수)
//		int iNumber = (int)number2;
//		System.out.println("난수 2 : " + iNumber);
		
//		// 2. 논리 연산
//		int a = 10, b = 20;		
//		// if (a == 10 && b == 20) {
//		// if (a == 10 && b == 30) {
//		// if (a == 10 || b == 30) {
//		if (a == 0 || b == 30) {
//			System.out.println("2. true");
//		}
		
//		//3. 중첩 선택문
//		int c = 10, d = 20;
//		if (c == 10) {
//			if (d == 20) {
//				System.out.println("3. true");
//			} 
//		}
		
		// Console 입력 도구 변수 만들기
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		// 3. 가위 바위 보 게임
		//    - (1, 2, 3) 범위의 정수 난수 발생 -> 변수에 저장 : 1 - 가위, 2 - 바위, 3 - 보
		//    - 사용자 입력 후 변수에 저장 : 1, 2, 3 범위의 정수 : 1 - 가위, 2 - 바위, 3 - 보
		//    - 테스트 코드 ( 두 숫자 출력 )
		double n = Math.random() * 3; // 0 <= n < 3
		int comNumber = (int)(n + 1);
		
		System.out.print("가위/바위/보 입력 (가위=1, 바위=2, 보=3) : ");
		int userNumber = scanner.nextInt();
		
		//System.out.printf("[COMPUTER : %d][USER : %d]\n", comNumber, userNumber);
		
		//    - 승부 계산 1
//		String result = "DRAW"; 
//		if (userNumber == 1) { // 사용자 : 가위
//			if (comNumber == 2) { // 컴퓨터 : 바위
//				result = "LOSE";
//			} else if (comNumber == 3) { // 컴퓨터 : 보
//				result = "WIN";
//			}
//		} else if (userNumber == 2) { // 사용자 : 바위
//			if (comNumber == 3) { // 컴퓨터 : 보
//				result = "LOSE";
//			} else if (comNumber == 1) { // 컴퓨터 : 가위
//				result = "WIN";
//			}
//		} else if (userNumber == 3) { // 사용자 : 보
//			if (comNumber == 1) { // 컴퓨터 : 가위
//				result = "LOSE";
//			} else if (comNumber == 2) { // 컴퓨터 : 바위
//				result = "WIN";
//			}
//		}
		
		//		- 승부 계산 2
		String result = "DRAW";
		if ( (userNumber == 1 && comNumber == 2) || 
			 (userNumber == 2 && comNumber == 3) || 
			 (userNumber == 3 && comNumber == 1) ) {
			result = "LOSE";
		} else if (userNumber == comNumber) {
			// DO NOTHING : result = "DRAW"
		} else {
			result = "WIN";
		}
		
		
		//    - 출력
		System.out.printf("[COMPUTER : %d][USER : %d][RESULT : %s]\n", comNumber, userNumber, result);
		
		scanner.close(); // 자원 반납
		

	}

}
















