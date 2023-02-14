
public class Ex10Method {

	public static void main(String[] args) {

		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		System.out.print("박스 그리기에 사용할 문자 : ");
		String s = scanner.next();
		System.out.print("박스의 너비 : ");
		int width = scanner.nextInt();
		System.out.print("박스의 높이 : ");
		int height = scanner.nextInt();
		
		// 메서드 호출 : 정의된 메서드를 불러서 메서드 내부의 코드가 실행되도록 하는 작업
		drawBox(s, width, height); // 메서드를 호출할 때 데이터를 보낼 수 있습니다. --> 전달인자
		
		scanner.close();

	}

	// 메서드 정의, 메서드 만들기 -> 메서드를 만드는 것이 메서드의 코드를 실행하는 것은 아닙니다.
	private static void drawBox(String s, int width, int height) { //호출과 함께 보내진 데이터를 받는 변수 --> 전달인자
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (x == 0 || y == 0 || 
					x == width - 1 || y == height - 1) {
					System.out.print(s);
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

}
