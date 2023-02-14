
public class Ex04ControlStatement10 {

	public static void main(String[] args) {
		
		// [ 개선된 가위/바위/보 게임 ]
		// 1. 기존 가위/바위/보 게임 규칙은 그대로 적용
		// 2. 사용자가 원하는 동안 계속 게임 실행
		//    - 메뉴 시스템 적용해서 구현
		// 3. 입력 유효성 검사 적용
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		//outer: for(;true;) {
		outer: while(true) {
			
			System.out.println("******************************");
			System.out.println("* 1. 가위/바위/보 게임 시작       *");
			System.out.println("* 9. 종료                     *");
			System.out.println("******************************");
			System.out.print("작업을 선택하세요 : ");
			String selection = scanner.next();
			
			System.out.println();
			
			switch(selection) {
			case "1": 
				double n = Math.random() * 3; // 0 <= n < 3
				int comNumber = (int)(n + 1);
				
				String sUserNumber;
				do {
					System.out.print("가위/바위/보 입력 (가위=1, 바위=2, 보=3) : ");
					sUserNumber = scanner.next();
				} while (sUserNumber.equals("1") == false &&
						 sUserNumber.equals("2") == false &&
						 sUserNumber.equals("3") == false);
				int userNumber = Integer.parseInt(sUserNumber); // Integer.parseInt("문자열") : "문자열"을 숫자로 변경
				
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
				// 출력				
				String com;
				if (comNumber == 1) 		com = "가위";
				else if (comNumber == 2) 	com = "바위";
				else 						com = "보";
				String user = userNumber == 1 ? "가위" : (userNumber == 2 ? "바위" : "보");
				System.out.printf("[COMPUTER : %2s][USER : %2s][RESULT : %s]\n", com, user, result);
				
				break;
			case "9": // 프로그램 종료
				System.out.println("$$ 프로그램을 종료합니다. $$");
				//break; // switch문 종료
				break outer; // outer: while(true)문 종료
			default: 
				System.out.println("$$ 지원하지 않는 명령입니다. $$");
				break;
			}
			
			System.out.println();
			
		}
		
		scanner.close();
		
		

	}

}





