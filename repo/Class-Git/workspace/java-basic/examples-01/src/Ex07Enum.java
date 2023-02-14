
// MALE, FEMALE 두 개의 값만 저장할 수 있는 자료형 만들기 ( 사용자 정의 자료형 )
enum Gender {
	MALE,	// 열거 상수
	FEMALE
}

enum RainbowColor {
	RED,
	ORANGE,
	YELLOW,
	GREEN,
	BLUE,
	NAVY,
	PURPLE
}

public class Ex07Enum {

	public static void main(String[] args) {
		
		Gender gender; // gender 변수에는 MALE, FEMALE만 저장 가능
		gender = Gender.MALE;
		System.out.println(gender);
		
		RainbowColor color;
		// color = Gender.MALE;
		color = RainbowColor.PURPLE;
		System.out.println(color);
		
		
		//int x = null; // primitive type에는 null을 저장할 수 없습니다.
		RainbowColor color2 = null; // 열겨형(enum)은 참조형이므로 null 저장 가능

	}

}
