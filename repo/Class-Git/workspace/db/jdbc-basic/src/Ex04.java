import java.util.List;
import java.util.Scanner;

public class Ex04 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("검색할 직원의 이름 : ");
		String name = scanner.nextLine();		
		
		EmployeeDao dao = new EmployeeDao();
		List<EmployeeDto> employees = dao.selectEmployeesByName(name);
		
		for (EmployeeDto employee : employees) {
			System.out.println(employee);
		}
		
	}

}
