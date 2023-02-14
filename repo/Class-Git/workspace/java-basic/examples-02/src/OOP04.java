
// 클래스의 멤버 변수를 모두 private으로 만들기
// getter, setter 만들기
// main 메서드 코드 수정하기

// 1-1. 클래스 만들기
//      좌표평면에서 한 점을 표현하는 클래스 만들기
//      이름 : ThePoint
//      필드 : x(int), y(int)
//      메서드 : info ( 좌표를 문자열로 반환 )
class ThePoint4 {
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public String info() {
		return String.format("POSITION : [%d, %d]", x, y);
	}	
}

// 2-1. 클래스 만들기
//      부서 클래스 만들기
//      이름 : Department
//      필드 : ?
//      메서드 : info ( 부서정보를 문자열로 반환 )
class Department4 {
	private int code;
	private String title;
	private String desc;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String info() {
		return String.format("[%d][%s][%s]", code, title, desc);
	}
}

public class OOP04 {
	public static void main(String[] args) {
		// 1-2. ThePoint 클래스 사용 : 인스턴스 만들기 + 값 저장 + 출력
		ThePoint4 point = new ThePoint4();
		point.setX(10);
		point.setY(20);
		//System.out.println(point.info());
		String info = point.info();
		System.out.println(info);
		
		// 2-2. Department 클래스 사용 : 인스턴스 만들기 + 값 저장 + 출력
		Department4 dept = new Department4();
		dept.setCode(100);
		dept.setTitle("IT");
		dept.setDesc("시스템 개발 및 운영 지원 부서");
		
		System.out.println(dept.info());
		
		
	}

}
