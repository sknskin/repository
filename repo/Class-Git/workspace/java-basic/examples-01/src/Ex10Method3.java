
public class Ex10Method3 {
	
	static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {
		
		// 1번 메서드 호출 및 결과 확인
		// String name = inputName();
		// System.out.printf("당신의 이름은 %s입니다.\n", name);
		
		// 2번 메서드 호출 및 결과 확인
//		int rNumber = randomFromRange(15, 60);
//		System.out.printf("%d ~ %d 사이의 난수 : %d\n", 15, 60, rNumber);
		
		// 3번 메서드 호출 및 결과 확인
		double maxNumber = max(21, 6);
		System.out.printf("큰 값은 %f입니다.\n", maxNumber);
	}
	
	// 1. 사용자로부터 이름을 입력 받는 메서드 만들기
	static String inputName() {
		System.out.print("이름을 입력하세요 : ");
		String name = scanner.nextLine();
		return name;
	}
	
	// 2. from, to  두 개의 값을 받아서 from ~ to 사이의 정수 난수를 반환하는 메서드 만들기
	static int randomFromRange(int from, int to) {
		int rNumber = (int)(Math.random() * (to - from)) + from;
		return rNumber;
	}

	// 3. 두 값을 받아서 큰 수를 반환하는 메서드 만들기
	static double max(double value1, double value2) {
//		if (value1 > value2) {
//			return value1;
//		} else {
//			return value2;
//		}
		
		return (value1 > value2) ? value1 : value2; // 조건식 ? 조건식이 true일 사용할 값 : 조건식이 false일 때 사용할 값
	}
}








