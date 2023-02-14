
public class Ex04ControlStatement09 {

	public static void main(String[] args) {
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		// 1-1. box 그리기 ( 크기는 : 20 x 10 또는 사용자 입력 )
		//      ********************
		//      ********************
		//      ********************
		//      ....
		//      ********************		
		// 1-2. box 그리기 ( 크기는 : 20 x 10 또는 사용자 입력 )
		//      ********************
		//      *                  *
		//      *                  *
		//      ....
		//      ********************
//		System.out.print("박스의 너비 : ");
//		int width = scanner.nextInt();
//		System.out.print("박스의 높이 : ");
//		int height = scanner.nextInt();
//		
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//				if (x == 0 || y == 0 || 
//					x == width - 1 || y == height - 1) {
//					System.out.print("*");
//				} else {
//					System.out.print(" ");
//				}
//			}
//			System.out.println();
//		}
		
		// 2-1. 직각삼각형 그리기 1
		for (int y = 1; y <= 10; y++) {
			for (int x = 1; x <= y; x++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		// 2-2. 직각삼각형 그리기 2
		for (int y = 1; y <= 10; y++) {
			for (int x = 1; x <= 10; x++) {
				if (x <= 10 - y) {
					System.out.print(" ");
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		
		
		scanner.close();
	}

}











