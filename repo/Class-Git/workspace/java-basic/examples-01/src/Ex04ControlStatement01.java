
public class Ex04ControlStatement01 {

	public static void main(String[] args) {
		
		//1. if statement
//		boolean b = true;
//		// boolean b = false;
//		
//		if (b) {
//			System.out.println("선택적 문장 실행 1"); // b가 true인 경우에만 실행
//			System.out.println("선택적 문장 실행 2"); // b가 true인 경우에만 실행
//		}
//		
//		System.out.println("항상 실행되는 문장 실행");

		java.util.Scanner scanner = new java.util.Scanner(System.in);		
		
		//2. 사용자입력 (숫자) -> 0보다 크면 "큽니다" 출력,  0보다 작으면 "작습니다" 출력, 0이면 "0입니다" 출력
//		
//		System.out.print("숫자 입력 : ");
//		int number = scanner.nextInt();
//		
//		if (number > 0) { // number > 0 : number가 0보다 크면 true, 아니면 false
//			System.out.println("0보다 큰 숫자입니다.");
//		} else if (number < 0){ 
//			System.out.println("0보다 작은 숫자입니다.");
//		} else if (number == 0) {
//			System.out.println("0입니다.");
//		}

		
		//3. 계산기 만들기
		//   ㄱ. 변수 3개 만들기 : 숫자(int), 연산자(String), 숫자(int)를 저장하는 변수
		//   ㄴ. 사용자입력 (숫자) 후 변수에 저장, 사용자입력 (연산자) 후 변수에 저장,  사용자입력 (숫자) 후 변수에 저장
		//   ㄷ. 연산자에 따라 해당 연산 수행 -> 결과를 변수에 저장
		//   ㄹ. 연산 결과 출력
		
		//ㄱ, ㄴ 작업 수행
		int operand1, operand2;
		String op;
		
		System.out.print("첫 번째 숫자를 입력하세요 : ");
		operand1 = scanner.nextInt();
		
		System.out.print("연산자를 입력하세요 (+, -, *, /, %) : ");
		// op = scanner.nextLine(); // nextLine : Console에서 입력 받은 데이터를 문자열로 변환 ( enter를 입력으로 인식 )
		op = scanner.next(); // next : Console에서 입력 받은 데이터를 문자열로 변환 ( enter를 입력으로 인식하지 않습니다. )
		
		System.out.print("두 번째 숫자를 입력하세요 : ");
		operand2 = scanner.nextInt();
		
		//ㄷ 작업 수행
		double result = 0;
		boolean valid = true;
		if (op.equals("+")) { // 문자열을 비교할 경우 ==를 사용할 수 없습니다. 대신 equals 메서드(명령)을 사용해야 합니다.
			result = operand1 + operand2;
		} else if (op.equals("-")) {
			result = operand1 - operand2;
		} else if (op.equals("*")) {
			result = operand1 * operand2; // ( * : 곱하기 연산 )
		} else if (op.equals("/")) {
			result = (double)operand1 / operand2; // ( / : 나누기 연산 ), int / int -> int, double / int -> double
		} else if (op.equals("%")) {
			result = operand1 % operand2; // ( % : 나머지 연산 )
		} else { // else : 위의 조건에 해당하지 않는 모든 경우 ( 여기서는 +, -, *, /가 아닌 모든 경우 )
			System.out.println("지원하지 않는 연산자입니다.");
			valid = false;
			// result의 값은 garbage
			
		}		
		
		// ㄹ 작업 수행
		if (valid == true) {
			System.out.printf("%d %s %d = %.2f\n", operand1, op, operand2, result); // %.2f : 실수, 소수점 2자리까지 출력
		}

		scanner.close(); // Scanner는 사용이 끝나면 닫아야 합니다. ( 반납 )		

	}

}

















