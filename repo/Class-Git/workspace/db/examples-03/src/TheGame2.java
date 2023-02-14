import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

// 1. 가위 바위 보 게임을 클래스 버전으로 변환
//    -> ContactManager 참고

// 2. 게임 결과 관리
//    - 게임 결과를 저장할 변수 만들기 ( 문자열의 목록형 변수 : Collection Class 사용 )
//    - 게임 마다 게임 결과를 목록에 저장 ( case "1"의 showResult() 호출 밑에서 구현 )
//    - 게임 결과 보여주기 ( case "2": 만들고 내용 구현 )

//    - 게임 종료할 때 파일에 저장			( case "9"에서 처리 )
//    - 게임 시작할 때 파일에서 읽어오기 	( 생성자 또는 doGame 처음 또는 main 등에서 처리 )


public class TheGame2 {

	private java.util.Scanner scanner = new java.util.Scanner(System.in);
	
	private ArrayList<String> gameResults = new ArrayList<>();
	
	public void doGame() {
		
		try {
			FileInputStream fis = new FileInputStream("game-result.txt"); 	// byte[]
			InputStreamReader isr = new InputStreamReader(fis);				// char[]
			BufferedReader br = new BufferedReader(isr);					// \n 단위로 읽기
			
			while (true) {
				String line = br.readLine();
				
				if (line == null) break;	// End Of File (EOF)
				if (line.length() == 0) continue; // while의 처음으로 이동
				
				gameResults.add(line);
			}
			
			br.close();
			isr.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		outer: while(true) {
			
			String selection = selectMenu();
			
			System.out.println();
			
			switch(selection) {
			case "1": 
				int comNumber = makeComNumber();
				
				int userNumber = inputUserNumber(); 
				
				String result = makeDecision(userNumber, comNumber);
				
				showResult(comNumber, userNumber, result);
				
				gameResults.add(result);	// 게임 결과 저장				
				
				break;
				
			case "2": // 게임 결과 목록 표시
				for (String gameResult : gameResults) {
					System.out.println(gameResult);
				}
				
				break;
				
			case "9": // 프로그램 종료
				// 게임 결과 history 파일에 저장
				try {
					FileOutputStream fos = new FileOutputStream("game-result.txt"); // byte[]
					OutputStreamWriter writer = new OutputStreamWriter(fos);		// char[]
					for (String gameResult : gameResults) {
						writer.write(gameResult + "\n");
					}
					writer.close();
					fos.close();
				} catch (IOException e) {					
					e.printStackTrace(); // 예외 메시지를 콘솔에 출력
				}
				
				System.out.println("$$ 프로그램을 종료합니다. $$");
				//break; // switch문 종료
				break outer; // outer: while(true)문 종료
			default: 
				System.out.println("$$ 지원하지 않는 명령입니다. $$");
				break;
			} // end of switch
			
			System.out.println();
			
		} // end of while
		
		scanner.close();
		
		

	} // end of main
	
	public String selectMenu() {
		System.out.println("******************************");
		System.out.println("* 1. 가위/바위/보 게임 시작       *");
		System.out.println("* 2. 게임 결과 보기             *");
		System.out.println("* 9. 종료                     *");
		System.out.println("******************************");
		System.out.print("작업을 선택하세요 : ");
		String selection = scanner.next();
		
		return selection;
	}

	public int makeComNumber() {
		double n = Math.random() * 3; // 0 <= n < 3
		int comNumber = (int)(n + 1);
		return comNumber;
	}
	
	public int inputUserNumber() {
		String sUserNumber;
		do {
			System.out.print("가위/바위/보 입력 (가위=1, 바위=2, 보=3) : ");
			sUserNumber = scanner.next();
		} while (!sUserNumber.equals("1") &&
				 !sUserNumber.equals("2") &&
				 !sUserNumber.equals("3"));
		int userNumber = Integer.parseInt(sUserNumber); // Integer.parseInt("문자열") : "문자열"을 숫자로 변경
		return userNumber;
	}
	
	public String makeDecision(int userNumber, int comNumber) {
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
		return result;
	}
	
	public void showResult(int comNumber, int userNumber, String result) {
		// 출력				
		String com;
		if (comNumber == 1) 		com = "가위";
		else if (comNumber == 2) 	com = "바위";
		else 						com = "보";
		String user = userNumber == 1 ? "가위" : (userNumber == 2 ? "바위" : "보");
		System.out.printf("[COMPUTER : %2s][USER : %2s][RESULT : %s]\n", com, user, result);
	}
	
	public static void main(String[] args) {
		TheGame2 game = new TheGame2();
		game.doGame();
	}
	
}













