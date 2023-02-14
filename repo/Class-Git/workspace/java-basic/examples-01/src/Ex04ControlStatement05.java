
public class Ex04ControlStatement05 {

	public static void main(String[] args) {
		
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
		
		//    - 승부 계산 1
		String result = "DRAW"; 
		switch (userNumber) {
		case 1: 
			switch(comNumber) {
			case 2: result = "LOSE"; break;
			case 3: result = "WIN"; break;
			}
			break;
		case 2: 
			switch(comNumber) {
			case 3: result = "LOSE"; break;
			case 1: result = "WIN"; break;
			}
			break;
		case 3: 
			switch(comNumber) {
			case 1: result = "LOSE"; break;
			case 2: result = "WIN"; break;
			}
			break;
		}

		
		//    - 출력
		System.out.printf("[COMPUTER : %d][USER : %d][RESULT : %s]\n", comNumber, userNumber, result);
		
		scanner.close(); // 자원 반납
		

	}

}
















