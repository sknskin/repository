
public class OOP10Constants {
	
	int x1;
	final int y1 = 10; // final : 값을 변경할 수 없는 공간 : 상수, 필드 초기화 or 생성자 초기화 가능
	final int y2;
	static final int z1 = 20; // static final 값을 변경할 수 없는 공간 (상수), 필드 초기화만 가능
	
	public OOP10Constants() {
		y2 = 10;
	}
	
	public void method() {
		// y1 = 100; // 오류 : final field는 변경 불가능
		// y2 = 100; // 오류 : final field는 변경 불가능
		// z1 = 100;  // 오류 : final field는 변경 불가능
	}
	

	public static void main(String[] args) {
		
		System.out.println(Math.E);
		System.out.println(Math.PI);
		System.out.println(Integer.MAX_VALUE);
		

	}

}
