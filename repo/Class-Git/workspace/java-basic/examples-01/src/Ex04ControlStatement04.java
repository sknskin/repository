
public class Ex04ControlStatement04 {

	public static void main(String[] args) {
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);		
		
		// [ 계산기 만들기 ]
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
		
		switch(op) { // op의 값을 읽고 일치하는 case를 찾는 형식, op의 값은 정수 계열 및 문자열만 가능 (실수 처리 불가능)
		case "+":
			result = operand1 + operand2;
			break; // break : 즉시 switch문을 종료하는 명령
		case "-":
			result = operand1 - operand2;
			break;
		case "*":
			result = operand1 * operand2;
			break;
		case "/":
			result = (double)operand1 / operand2;
			break;
		case "%":
			result = operand1 % operand2;
			break;
		default: // 위 case에 해당하지 모든 경우 ( if문의 else 기능 )
			System.out.println("지원하지 않는 연산자입니다.");
			valid = false;
		}
		
		// ㄹ 작업 수행
		if (valid == true) {
			System.out.printf("%d %s %d = %.2f\n", operand1, op, operand2, result); // %.2f : 실수, 소수점 2자리까지 출력
		}

		scanner.close(); // Scanner는 사용이 끝나면 닫아야 합니다. ( 반납 )		

	}

}

















