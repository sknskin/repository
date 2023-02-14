import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// 1. 프로그램이 종료될 때 연락처 정보를 파일에 저장
// 2. 프로그램이 시작될 때 연락처 정보를 파일로부터 읽어오기

// 연락처 관리 기능 클래스
class ContactManager2 {
	
	private java.util.Scanner scanner = new java.util.Scanner(System.in);
		
	private ArrayList<Contact> contacts; // = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public ContactManager2() { // 생성자 메서드 : 클래스 이름과 같은 이름, 결과형 없음
		
		File f = new File("contacts.dat");
		if (f.exists()) {		
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try {
				fis = new FileInputStream("contacts.dat");
				ois = new ObjectInputStream(fis);
				contacts = (ArrayList<Contact>)ois.readObject();
			} catch (Exception ex) {
				contacts = new ArrayList<>();
				ex.printStackTrace();
			} finally {
				try { ois.close(); } catch (Exception ex) {}
				try { fis.close(); } catch (Exception ex) {}
			}
		} else {
			contacts = new ArrayList<>();
		}
	}
	
	public void doManage() { // 프로그램의 주 실행 로직 구현
		
		
		
		while (true) {
			
			String selection = selectMenu();
			
			System.out.println();
			if (selection.equals("1")) { // 등록
				// 등록 기능 구현				
				Contact contact = inputContact();
				
				// 3. 2의 인스턴스를 conatcts 리스트에 저장 
				contacts.add(contact);
								
				System.out.println("$$$ 새 연락처를 등록했습니다");
				
			} else if (selection.equals("4")) { // 전체 목록 보기
				
				showAllContacts();
				
			} else if (selection.equals("5")) { // 검색
				//1. 검색할 이름 입력
				System.out.print("검색할 이름 : ");
				String name = scanner.next();
				
				showSearchedContacts(name);
				
			} else if (selection.equals("9")) {
				// 연락처 정보를 파일에 저장
				FileOutputStream fos = null;
				ObjectOutputStream oos = null;
				try {
					fos = new FileOutputStream("contacts.dat");
					oos = new ObjectOutputStream(fos);
					oos.writeObject(contacts);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try { oos.close(); } catch (Exception ex) {}
					try { fos.close(); } catch (Exception ex) {}
				}
				
				System.out.println("$$$ 연락처 관리 프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("$$$ 지원하지 않는 기능입니다.");
			}
			System.out.println();
		}		
	}
	
	public void showSearchedContacts(String name) {			
		System.out.println("[ 검색된 연락처 목록 ]");
		for (Contact contact : contacts) {
			if (contact.getName().contains(name)) { 	// 부분 일치 검색 -> contains 사용
				System.out.println(contact); // 문자열이 필요한 곳에서 자동으로 toString() 호출
			}
		}
	}
	
	public void showAllContacts() {
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
		int cnt = contacts.size(); // 연락처 갯수
		contact.setNo(cnt + 1); // 마지막 연락처의 다음 순서 번호 사용
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
		
		ContactManager2 contactManager = new ContactManager2();
		
		contactManager.doManage();
		
	}
}










