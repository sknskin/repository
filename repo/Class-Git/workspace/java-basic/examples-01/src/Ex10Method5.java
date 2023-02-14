
public class Ex10Method5 {
	
	static java.util.Scanner scanner = new java.util.Scanner(System.in);
	
	public static void main(String[] args) {
		
		String selection = "";		//사용자 메뉴 입력 변수
		int[] numbers = new int[6];	//뽑은 당첨 예상 번호를 저장할 배열 변수
		int sum = 0, average = 0;	//뽑은 숫자의 합과 평균을 저장할 변수
		
		MainLoop: //레이블 구문 : 코드의 위치에 이름 붙이기 
		while (true) {
			
			//1. 메뉴 표시 + 사용자 입력
			selection = selectMenu();
			
			System.out.println();
			
			switch (selection) {
			case "1": //3-1. 사용자가 1을 입력한 경우
				do {
					//3-1-1. 번호 추출 (1 ~ 45, 중복X, Random, 6개)
					numbers = selectBasicNumbers();
					
					//3-1-2. 평균 계산
					average = calculateMean(numbers);
					
				} while (average < 20 || average > 26);//3. 평균 검사 (20 ~ 26, 실패하면 1부터 다시)
				
				//4. 출력
				showNumbers(numbers, average);
				break;
				
			case "9": //3-2. 사용자가 9를 입력한 경우
				System.out.println("행운을 빕니다....");			  
				break MainLoop; //1. 프로그램 종료 (while 종료)
				
			default:
				System.out.println("지원하지 않는 명령입니다.");
				break;
			}
			
		}

	}
	
	/**
	 * 사용자에게 선택 가능한 작업의 목록을 보여주는 메서드
	 */
	static String selectMenu() {
		System.out.println();
		System.out.println("******************************");
		System.out.println("* 1. 번호 뽑기.");
		System.out.println("* 2. 목록 보기.");
		System.out.println("* 9. 종료.");
		System.out.println("******************************");
		
		System.out.print("원하는 작업 번호를 입력하세요 : ");
		String selection = scanner.nextLine();
		return selection;
	}
	
	/**
	 * 로또 규칙에 맞는 랜덤 번호를 추출하는 메서드
	 *  
	 * @return 추출된 로또 번호, 정수 배열
	 */
	static int[] selectBasicNumbers() {
		int[] numbers = new int[6];
		for (int i = 0; i < 6; i = i + 1) {
			numbers[i] = (int)(Math.random() * 45) + 1; // 1 ~ 45
			for (int j = 0; j < i; j = j + 1) {
				if (numbers[i] == numbers[j]) {
					//i = i - 1;//i의 위치를 감소시켜서 증감식의 증가 효과를 제거
					i = -1; //처음부터 다시 뽑기
				}
			}
		}
		return numbers;
	}
	
	//3-1-2번 평균 계산 영역의 코드를 메서드로 만들고 호출로 대체
	static int calculateMean(int[] numbers) {
		int sum = 0; //각 숫자를 더하기 전에 기존에 저장된 값을 제거
		for (int i = 0; i < 6; i = i + 1) {
			sum = sum + numbers[i];
		}
		int average = sum / 6;
		
		return average;
	}
	
	//4번 출력 영역의 코드를 메서드로 만들고 호출로 대체
	static void showNumbers(int[] numbers, int average) {
		System.out.print("SELECTED NUMBERS : ");
		for (int i = 0; i < 6; i = i + 1) {
			System.out.printf("[%2d]", numbers[i]);
		}
		System.out.printf("[AVERAGE : %2d]\n", average);
	}

}

















