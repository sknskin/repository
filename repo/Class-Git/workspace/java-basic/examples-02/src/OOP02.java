
// 1-1. 클래스 만들기
//      좌표평면에서 한 점을 표현하는 클래스 만들기
//      이름 : ThePoint
//      필드 : x(int), y(int)
//      메서드 : info ( 좌표를 문자열로 반환 )
class ThePoint {
	int x;
	int y;
	
	String info() {
		return String.format("POSITION : [%d, %d]", x, y);
	}	
}

// 2-1. 클래스 만들기
//      부서 클래스 만들기
//      이름 : Department
//      필드 : ?
//      메서드 : info ( 부서정보를 문자열로 반환 )
class Department {
	int code;
	String title;
	String desc;
	
	String info() {
		return String.format("[%d][%s][%s]", code, title, desc);
	}
}

public class OOP02 {
	public static void main(String[] args) {
		// 1-2. ThePoint 클래스 사용 : 인스턴스 만들기 + 값 저장 + 출력
		ThePoint point = new ThePoint();
		point.x = 10;
		point.y = 20;
		//System.out.println(point.info());
		String info = point.info();
		System.out.println(info);
		
		// 2-2. Department 클래스 사용 : 인스턴스 만들기 + 값 저장 + 출력
		Department dept = new Department();
		dept.code = 100;
		dept.title = "IT";
		dept.desc = "시스템 개발 및 운영 지원 부서";
		
		System.out.println(dept.info());
		
		
	}

}
