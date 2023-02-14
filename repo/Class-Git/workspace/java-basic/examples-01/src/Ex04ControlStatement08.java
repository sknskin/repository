
public class Ex04ControlStatement08 {

	public static void main(String[] args) {
		
		// 1. 1 ~ 10까지 출력
//		for (int i = 1; i <= 10; i++) { // i++ ==> i += 1 ==> i = i + 1		
//			System.out.printf("이번 회차는 %d입니다.\n", i);
//		}
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		// 2. 1 ~ 사용자가 입력한 숫자까지의 합
//		System.out.print("숫자를 입력하세요 : ");
//		int number = scanner.nextInt();
//		int sum = 0;
//		for (int i = 1; i <= number; i++) {
//			sum += i; // sum = sum + i;
//		}
//		System.out.printf("%d ~ %d까지의 합 : %d\n", 1, number, sum);
		
		// 3. 명명된 반복문 break
//		outer: for (int i = 0; i < 5 ; i++) {
//			inner: for (int j = 0; j < 5; j++) {
//				System.out.printf("[%d][%d]\n", i, j);
//				if (j == 2) {
//					//break; // j 반복문 (내부 반복문) break;
//					break outer; // outer 이름이 붙은 반복문 break
//				}
//			}
//		}
		
		// 4. 사용자입력 (숫자, 1 ~ 9) -> 입력된 숫자의 구구단 출력
		//    5 * 1 =  5
		//    5 * 2 = 10
		//    ...
		//    5 * 9 = 45
//		System.out.print("출력할 구구단의 단 : ");
//		int dan = scanner.nextInt();
//		for (int i = 1; i <= 9; i++) {
//			//System.out.println(dan + " * " + i + " = " + (dan * i) );
//			System.out.printf("%d * %d = %2d\n", dan, i, dan*i);
//		}
		
		// 5. 전체 구구단 출력 ( for문 중첩해서 구현 )
		//    1 * 1 = 1   2 * 1 =  2 .... 9 * 1 =  9
		//    1 * 2 = 2   2 * 2 =  4 .... 9 * 2 = 18
		//    ...
		//    1 * 9 = 9   2 * 9 = 18 .... 9 * 9 = 81
		for (int y = 1; y <= 9; y++) { // 종축 방향 반복
			for (int x = 1; x <= 9; x++) { // 횡축 방향 반복
				System.out.printf("[%d*%d=%2d] ", x, y, x*y);
			}
			System.out.println();
		}
		

		
		scanner.close();
	}

}
















