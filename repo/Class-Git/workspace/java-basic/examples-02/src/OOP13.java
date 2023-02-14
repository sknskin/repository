
class TheShape {
	public void draw() {
		System.out.println("TheShape.draw");
	}
}

class TheRectangle extends TheShape {
	public void draw() {
		System.out.println("TheRectangle.draw");
	}
}
class TheOval extends TheShape {
	public void draw() {
		System.out.println("TheOval.draw");
	}
}
class TheLine extends TheShape {
	public void draw() {
		System.out.println("TheLine.draw");
	}
}

public class OOP13 {

	public static void main(String[] args) {
		
		// instanceMethod(); // 오류 : static method 내부에서는 인스턴스형 멤버 사용할 수 없습니다.
		
		// 1. 전달인자의 다형성
		TheRectangle r = new TheRectangle();
		doDraw(r);		
		TheOval o = new TheOval();
		doDraw(o);		
		TheLine l = new TheLine();
		doDraw(l);
		
		System.out.println("===============================");
		
		// 2. 배열의 다형성
		//    Rectangle 4개, Line 4개, Oval 4개 만들어서 사용 --> 배열로 만들어서 사용
		//    참고 : int[] ar = new int[10] -> 이 배열에는 int만 저장 가능
		TheShape[] shapes = new TheShape[12]; // TheShape 배열을 만들면 이 배열에는 TheShape 인스턴스와 자식 인스턴스를 모두 저장할 수 있습니다.
		for (int i = 0; i < shapes.length; i++) { // 배열.length : 배열 요소의 갯수 (여기서는 12개)
			if (i%3 == 0) {
				shapes[i] = new TheRectangle(); // Shape 참조변수 -> Rectangle 인스턴스 ( 부모참조 -> 자식인스턴스 )
			} else if (i%3 == 1) {
				shapes[i] = new TheOval();
			} else {
				shapes[i] = new TheLine();
			}
		}
		for (int i = 0; i < shapes.length; i++) {
			shapes[i].draw(); // shapes[i] 변수가 참조하는 인스턴스의 draw() 메서드 호출
		}
		
	}
	
	void instanceMethod() {}
	
	static void doDraw(TheShape s) {
		s.draw();
	}
//	static void doDraw(TheRectangle r) {
//		r.draw();
//	}
//	static void doDraw(TheOval o) {
//		o.draw();
//	}
//	static void doDraw(TheLine l) {
//		l.draw();
//	}
}


















