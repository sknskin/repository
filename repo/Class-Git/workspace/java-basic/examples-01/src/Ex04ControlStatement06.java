
public class Ex04ControlStatement06 {

	public static void main(String[] args) {
		
		// [ 점수 계산기 만들기 ]
		// 1. 점수 3개 입력 (0 ~ 100) 받아서 변수(score1, score2, score3)에 저장
		//    테스트 코드
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		System.out.print("첫 번째 점수 : ");
		int score1 = scanner.nextInt();
		System.out.print("두 번째 점수 : ");
		int score2 = scanner.nextInt();
		System.out.print("세 번째 점수 : ");
		int score3 = scanner.nextInt();
		// System.out.printf("[%d][%d][%d]\n", score1, score2, score3);
		
		// 2. 합계와 평균을 계산해서 변수(total, average)에 저장
		//    테스트 코드
		int total = score1 + score2 + score3;
		double average = total / 3.; // 3. ==> 3.0
		// System.out.printf("[TOTAL : %d][AVERAGE : %.2f]\n", total, average);				
		
		// 3. 평균에 따라 등급 계산해서 변수(grade / char or String)에 저장 --> if ~ else 구문 사용
		//    ( 90~100 : A, 80~89 : B, 70~79 : C, 60~69 : D, 나머지 : F )
		String grade1 = "A";
		char grade2 = 'A';
		boolean valid = true;
		
		switch((int)average / 10) { // 1. switch는 실수 평가 불가능 -> 정수로 형변환, 2. 범위표현이 불가능하기 때문에 10으로 나눈 값 사용
		case 10: 
		case  9: grade1 = "A"; grade2 = 'A'; break;
		case  8: grade1 = "B"; grade2 = 'B'; break;
		case  7: grade1 = "C"; grade2 = 'C'; break;
		case  6: grade1 = "D"; grade2 = 'D'; break;
		case  5: 
		case  4: 
		case  3: 
		case  2: 
		case  1: 
		case  0: grade1 = "F"; grade2 = 'F'; break;
		default : valid = false; break;
		}
		
		// 4. 출력
		if (valid) {
			System.out.printf("[SCORE1 : %d][SCORE2 : %d][SCORE 3 : %d][TOTAL : %d][AVERAGE : %f][GRADE1 : %s][GRADE2 : %c]",
							  score1, score2, score3, total, average, grade1, grade2);
		} else {
			System.out.println("점수가 잘못 입력되었습니다.");
		}
		
		scanner.close();

	}

}
