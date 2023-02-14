
public class Ex06Array2 {

	public static void main(String[] args) {
		
		// [ 로또 번호 추출기 만들기 ]
		// 1. 정수 6개를 저장하는 배열 만들기
		// 2. 1 ~ 45 범위의 난수 6개 뽑아서 배열 변수에 저장
		// 3. 뽑힌 숫자의 평균 구해서 변수에 저장
		// 4. 뽑힌 숫자와 평균 출력하기
		// ==============================
		// 5. 중복이 발생하지 않도록 숫자 뽑기
		// 6. 메뉴 사용해서 사용자가 원하는 동안 반복
		// 7. 평균이 20보다 작거나 26보다 크면 다시 뽑기 (do ~ while)
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		outer: while(true) {
			
			System.out.println("******************************");
			System.out.println("* 1. 당첨 예상 번호 뽑기           *");
			System.out.println("* 9. 종료                      *");
			System.out.println("******************************");
			System.out.print("작업을 선택하세요 : ");
			String selection = scanner.next();
			
			System.out.println();
			
			switch(selection) {
			case "1": 
				int[] numbers = new int[6];
				int average = 0;
				do {
					for (int i = 0; i < numbers.length; i++) {
						numbers[i] = (int)(Math.random() * 45) + 1;
						// 중복 검사를 위한 반복문
						for (int j = 0; j < i; j++) { // 현재 뽑은 숫자의 위치보다 앞에 있는 숫자와 비교 (반복)
							if (numbers[i] == numbers[j]) { // 중복된 경우
								// i--; // 반복문의 i++의 반대효과를 적용해서 현재 위치를 다시 뽑게 만드는 구현
								i = -1; // 처음부터 다시 뽑는 구현
								break;
							}
						}
					}
					
					int sum = 0;
					for (int i = 0; i < numbers.length; i++) {
						sum += numbers[i]; // sum = sum + numbers[i];
					}
					average = sum / numbers.length;
				} while (average < 20 || average > 26);
				
				System.out.print("선택된 번호 : ");
				for (int i = 0; i < numbers.length; i++) {
					System.out.printf("[%2d]", numbers[i]);
				}
				System.out.printf("[AVERAGE : %2d]\n", average);
				
				break;
			case "9": 
				System.out.println("행운을 빕니다.");
				break outer;
			default: // 1도 아니고 9도 아닌 경우
				System.out.println("지원하지 않는 기능입니다.");
			}
			
			System.out.println();
		}

	}

}
