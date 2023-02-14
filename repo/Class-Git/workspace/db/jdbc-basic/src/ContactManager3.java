import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

// 1. 데이터베이스와 테이블 만들기
//    데이터베이스는 market_db 사용
//    테이블 생성
//		create table tbl_contact
//		(
//			no int not null primary key auto_increment,
//    		name varchar(50) not null,
//    		phone varchar (20) null,
//    		email varchar (100) null
//		);
//
// 2. DTO 만들기 -> Contact 클래스 사용
//
// 3. Dao 만들기 : ContactDao 클래스
// 4. 등록 기능 수정
//    DAO에 void insertContact(Contact contact) 메서드 만들기 : JDBC 구현
//    ContactManager에서 위의 DAO 메서드 호출
//
// 5. 전체 목록 보기 기능 수정
//    Dao에 List<Contact> selectAllContacts() 메서드 만들기 : JDBC 구현
//    ContactManager에서 위의 Dao 메서드 호출 + 결과 출력
//
// 6. 검색 기능 수정
//    Dao에 List<Contact> selectContactsByName(String name) 메서드 만들기 : JDBC 구현
//    ContactManager에서 위의 Dao 메서드 호출 + 결과 출력

// 연락처 관리 기능 클래스
class ContactManager3 {
	
	private java.util.Scanner scanner = new java.util.Scanner(System.in);
	
	private ContactDao dao = new ContactDao();
		
	public void doManage() { // 프로그램의 주 실행 로직 구현
		
		while (true) {
			
			String selection = selectMenu();
			
			System.out.println();
			if (selection.equals("1")) { // 등록
				// 입력				
				Contact contact = inputContact();
				
				// 인스턴스를 데이터베이스에 저장				
				dao.insertContact(contact);
								
				System.out.println("$$$ 새 연락처를 등록했습니다");
				
			} else if (selection.equals("4")) { // 전체 목록 보기
								
				List<Contact> contacts = dao.selectAllContacts();
				showContacts(contacts);
				
			} else if (selection.equals("5")) { // 검색
				//1. 검색할 이름 입력
				System.out.print("검색할 이름 : ");
				String name = scanner.next();
				
				List<Contact> contacts = dao.selectContactsByName(name);
				showContacts(contacts);
				
			} else {
				System.out.println("$$$ 지원하지 않는 기능입니다.");
			}
			System.out.println();
		}		
	}
	
	public void showContacts(List<Contact> contacts) {
		System.out.println("[ 연락처 목록 ]");
		for (Contact contact : contacts) {
			System.out.println(contact); // 문자열이 필요한 곳에서 자동으로 toString() 호출
		}
	}

	public Contact inputContact() {
		// 1. Contact 인스턴스 만들기
		Contact contact = new Contact();
		// 2. 사용자 입력 -> 입력된 내용을 Contact 인스턴스에 저장
		System.out.println("[ 신규 등록 연락처 정보 입력 ]");		
		System.out.print("이름 : ");
		String name = scanner.next();
		contact.setName(name); // contact.name = name;
		System.out.print("전화번호 : ");
		String phone = scanner.next();
		contact.setPhone(phone);
		System.out.print("이메일 : ");
		String email = scanner.next();
		contact.setEmail(email);
		
		return contact;
	}
	
	/**
	 * 사용자에게 선택 가능한 기능 목록을 표시하고<br />
	 * 사용자의 선택을 입력받은 후 반환
	 * 
	 * @return 문자열 형식의 사용자 입력 값
	 */
	String selectMenu() {
		System.out.println("******************************");
		System.out.println("* 1. 연락처 등록                 *");
		System.out.println("* 2. 연락처 수정                 *");
		System.out.println("* 3. 연락처 삭제                 *");
		System.out.println("* 4. 연락처 목록                 *");
		System.out.println("* 5. 연락처 검색                 *");
		System.out.println("* 9. 종료                      *");
		System.out.println("******************************");
		System.out.print("작업을 선택하세요 : ");
		String selection = scanner.next();
		
		return selection;
	}
	
	
	public static void main(String[] args) {
		
		ContactManager3 contactManager = new ContactManager3();
		
		contactManager.doManage();
		
	}
}










