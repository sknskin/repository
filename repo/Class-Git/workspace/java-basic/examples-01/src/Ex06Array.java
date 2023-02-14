
public class Ex06Array {

	public static void main(String[] args) {
		
		// 1. 배열 만들기 + 사용하기 ( 같은 자료형, 크기 지정, 참조형 )
		int[] ar1; // int 여러 개를 저장하는 배열의 참조 변수 만들기
		ar1 = new int[5]; // int 5개를 저장하는 배열 인스턴스를 만들고 주소를 ar1에 저장
		
		ar1[0] = 10; ar1[1] = 20; ar1[2] = 30; ar1[3] = 40; ar1[4] = 50; // 배열 각 요소에 값 저장 ( 배열의 순서번호는 0부터 시작 )
		System.out.println(ar1[0] + " / " + ar1[3]);
		
		System.out.println("=============================================");
		
		// 2. 배열 만들기 + 사용하기 2 ( 배열과 반복문, 특히 for문은 같이 사용되는 경우가 많습니다. )
		int[] ar2 = new int[10];
		for (int i = 0; i < ar2.length; i++) { // 배열.length : 배열 요소의 갯수
			ar2[i] = (int)(Math.random() * 900) + 100; // 3자리 정수
		}

		for (int i = 0; i < ar2.length; i++) {
			System.out.printf("ar2[%d] = %d\n", i, ar2[i]);
		}
		
		System.out.println("=============================================");
		
		// 3. 배열 만들기 + 사용하기 3 ( 값의 목록으로 배열 만들기, 초기화 )
		int[] ar3 = { 1, 2, 3, 4, 5 };
		for (int i = 0; i < ar3.length; i++) {
			System.out.printf("ar3[%d] = %d\n", i, ar3[i]);
		}
		
		System.out.println("=============================================");
		
		// 4. 배열 만들기 + 사용하기 4 ( 값의 목록으로 배열 만들기, 초기화 )
		int[] ar4;
		// ar4 = { 1, 2, 3, 4, 5 }; // 오류 : 목록 배열 초기화는 선언문에서만 가능
		ar4 = new int[]{ 1, 2, 3, 4, 5 }; 
		for (int i = 0; i < ar4.length; i++) {
			System.out.printf("ar4[%d] = %d\n", i, ar4[i]);
		}
		
		System.out.println("=============================================");
		
		// 5. 배열 요소의 초기값 (지정된 초기값으로 초기화)
		int[] ar5 = new int[5]; 
		for (int i = 0; i < ar5.length; i++) {
			System.out.printf("ar5[%d] = %d\n", i, ar5[i]);
		}
		
		System.out.println("=============================================");
		
		// 6. 다차원 배열
		int[][] ar6 = new int[7][5];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				ar6[i][j] = (int)(Math.random() * 900) + 100; // 3자리 정수
			}
		}
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.printf("[%5d]", ar6[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("=============================================");
		
		// 7. 향상된 for
		int[] ar7 = { 1, 2, 3, 4, 5 };
		for (int data : ar7) { // ar7 목록의 데이터를 처음부터 순차적으로 뽑아서 data 변수에 저장하고 실행문 실행
			System.out.printf("%d\n", data);
		}
	}

}























