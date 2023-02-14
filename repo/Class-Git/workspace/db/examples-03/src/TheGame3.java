import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

// 1. 가위 바위 보 게임을 클래스 버전으로 변환
//    -> ContactManager 참고

// 2. 게임 결과 관리
//    - 게임 결과를 저장할 변수 만들기 ( GameResult의 ArrayList 사용 )
//    - 게임 마다 게임 결과를 목록에 저장 ( case "1"의 showResult() 호출 밑에서 구현 )
//    - 게임 결과 보여주기 ( case "2": 만들고 내용 구현 )

//	  - 게임 종료할 때 파일에 저장			( case "9"에서 처리 )
//	  - 게임 시작할 때 파일에서 읽어오기 	( 생성자 또는 doGame 처음 또는 main 등에서 처리 )

public class TheGame3 {

	private java.util.Scanner scanner = new java.util.Scanner(System.in);
	
	private ArrayList<GameResult> gameResults2 = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public TheGame3() { // 생성자 메서드 : 클래스이름과 동일, 결과형 없음, 자동 호출 (new 할 때 )
		
		if (!new File("game-result.dat").exists()) { // 파일이 없는 경우 ( exists() : 파일 존재 여부 확인 )
			return;
		}
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("game-result.dat");	// 파일에서 읽기
			ois = new ObjectInputStream(fis);				// byte[] -> Object 변환기
			gameResults2 = (ArrayList<GameResult>)ois.readObject();
			
			if (gameResults2.size() > 0) {
				GameResult gr = gameResults2.get(gameResults2.size() - 1); // 마지막 요소 가져오기
				GameResult.setNextNo(gr.getNo() + 1);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { ois.close(); } catch (Exception ex) {}
			try { fis.close(); } catch (Exception ex) {}
		}
	}
	
	public void doGame() {
		
		outer: while(true) {
			
			String selection = selectMenu();
			
			System.out.println();
			
			switch(selection) {
			case "1": 
				int comNumber = makeComNumber();
				
				int userNumber = inputUserNumber(); 
				
				String result = makeDecision(userNumber, comNumber);
				
				showResult(comNumber, userNumber, result);
								
				GameResult gr = new GameResult(0, new Date(), comNumber, userNumber, result);
				gameResults2.add(gr);
				
				break;
				
			case "2": // 게임 결과 목록 표시
				for (GameResult gameResult : gameResults2) {
					System.out.println(gameResult);
				}
				break;
				
			case "9": // 프로그램 종료
				
				FileOutputStream fos = null;
				ObjectOutputStream oos = null;
				
				try {
					fos = new FileOutputStream("game-result.dat");	// 파일에 쓰기
					oos = new ObjectOutputStream(fos);				// Object -> byte[] 변환기
					oos.writeObject(gameResults2);
					
				} catch (Exception ex) {
					ex.printStackTrace();	// 예외 메시지 출력 ( 테스트 전용 )
				} finally {
					try { oos.close(); } catch (Exception ex) {}
					try { fos.close(); } catch (Exception ex) {}
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
		TheGame3 game = new TheGame3();
		game.doGame();
	}
	
}













