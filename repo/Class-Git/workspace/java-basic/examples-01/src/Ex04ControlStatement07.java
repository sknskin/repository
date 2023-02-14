
public class Ex04ControlStatement07 {

	public static void main(String[] args) {
		
		// 1 ~ 10까지 정수를 순차적으로 출력
//		int round = 1;
//		while ( round <= 10 ) {
//			// System.out.printf("[%02d] 라운드입니다.\n", round); // %02d : 두자리에 출력, 앞자리가 비면 0으로 채우기
//			System.out.printf("[%2d] 라운드입니다.\n", round); // %2d : 두자리에 출력
//			round++; // ==> round += 1;  ==> round = round + 1;
//		}

		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		// 1 ~ 사용자가 입력한 숫자까지 정수의 합 계산		
//		System.out.print("1보다 큰 정수 입력 : ");
//		int number = scanner.nextInt();
//		int idx = 1;
//		int sum = 0;
//		while (idx <= number) {
//			sum = sum + idx;
//			idx++;
//		}
//		
//		System.out.printf("%d ~ %d까지의 합 : %d\n", 1, number, sum);
		
		
		// 행운숫자 뽑기
		// - 1 ~ 45 사이의 숫자를 뽑아서 보여주기 
		// - 사용자가 원할 때까지 반복
		
//		while (true) { // 무한 반복
//			int number2 = (int)(Math.random() * 45) + 1; //1 ~ 45
//			System.out.printf("당신의 행운번호 : [%2d]\n", number2);
//			
//			System.out.print("계속할까요(y/n) : ");
//			String yn = scanner.next();
//			if (yn.equals("y") == false) {
//				break; // 반복문을 중단하는 명령
//			}
//		}
		
		/////////////////////////////////////////////////////////////////
		
		// 정수 입력 (0 ~ 100) 받아서 변수에 저장
		// 만약 범위를 벗어나면 다시 입력 받기
		int number3;
		do {
			System.out.print("0 ~ 100 범위의 숫자를 입력하세요 : ");
			number3 = scanner.nextInt();
		} while (number3 < 0 || number3 > 100); // 실행문의 결과를 조건식에서 사용하는 경우 do ~ while이 자연스럽습니다.
		
		System.out.printf("입력된 숫자 : %d\n", number3);
		
		scanner.close();

	}

}












