import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

	
	public List<EmployeeDto> selectAllEmployees() {
		
		ArrayList<EmployeeDto> employees = new ArrayList<>();
		
		// 데이터베이스에서 데이터를 조회하고 employees에 채우는 작업 수행
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employees", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT emp_no, birth_date, first_name, last_name, gender, hire_date " +
					"FROM employees ";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				EmployeeDto employee = new EmployeeDto(); 	// 한 행당 Dto 객체 한 개 만들기
				employee.setEmpNo(rs.getInt(1));			// 현재 행의 1번째 열 데이터를 정수로 읽기
				employee.setBirthDate(rs.getDate(2));		// 현재 행의 2번째 열 데이터를 날짜로 읽기
				employee.setFirstName(rs.getString(3));
				employee.setLastName(rs.getString(4));
				employee.setGender(rs.getString(5));
				employee.setHireDate(rs.getDate(6));
				
				employees.add(employee);
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return employees;
		
	}

	public List<EmployeeDto> selectEmployeesByEmpNo(int empNo) {
		
		ArrayList<EmployeeDto> employees = new ArrayList<>();
		
		// 데이터베이스에서 데이터를 조회하고 employees에 채우는 작업 수행
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employees", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT emp_no, birth_date, first_name, last_name, gender, hire_date " +
					"FROM employees " +
					"WHERE emp_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);	// SQL의 1번째 ?를 대체할 데이터
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				EmployeeDto employee = new EmployeeDto(); 	// 한 행당 Dto 객체 한 개 만들기
				employee.setEmpNo(rs.getInt(1));			// 현재 행의 1번째 열 데이터를 정수로 읽기
				employee.setBirthDate(rs.getDate(2));		// 현재 행의 2번째 열 데이터를 날짜로 읽기
				employee.setFirstName(rs.getString(3));
				employee.setLastName(rs.getString(4));
				employee.setGender(rs.getString(5));
				employee.setHireDate(rs.getDate(6));
				
				employees.add(employee);
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return employees;
		
	}
	
	public List<EmployeeDto> selectEmployeesByName(String name) {
		
		ArrayList<EmployeeDto> employees = new ArrayList<>();
		
		// 데이터베이스에서 데이터를 조회하고 employees에 채우는 작업 수행
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employees", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT emp_no, birth_date, first_name, last_name, gender, hire_date " +
					"FROM employees " +
					"WHERE lower(first_name) LIKE ? " + // ? : 나중에 채워질 영역 표시
					"      OR " + 
					"      lower(last_name) LIKE ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");	// SQL의 1번째 ?를 대체할 데이터
			pstmt.setString(2, "%" + name + "%");	// SQL의 2번째 ?를 대체할 데이터
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				EmployeeDto employee = new EmployeeDto(); 	// 한 행당 Dto 객체 한 개 만들기
				employee.setEmpNo(rs.getInt(1));			// 현재 행의 1번째 열 데이터를 정수로 읽기
				employee.setBirthDate(rs.getDate(2));		// 현재 행의 2번째 열 데이터를 날짜로 읽기
				employee.setFirstName(rs.getString(3));
				employee.setLastName(rs.getString(4));
				employee.setGender(rs.getString(5));
				employee.setHireDate(rs.getDate(6));
				
				employees.add(employee);
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return employees;
		
	}
	
	public List<DepartmentDto> selectAllDepartments() {
		
		ArrayList<DepartmentDto> departments = new ArrayList<>();
		
		// 데이터베이스에서 데이터를 조회하고 employees에 채우는 작업 수행
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employees", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT dept_no, dept_name " +
					"FROM departments ";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				DepartmentDto d = new DepartmentDto(); 	// 한 행당 Dto 객체 한 개 만들기
				d.setDeptNo(rs.getString(1));			
				d.setDeptName(rs.getString(2));		
				
				departments.add(d);
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return departments;
		
	}


	public List<EmpDeptDto> selectEmployeeWithDepartmentByDeptCode(String deptCode) {
		
		ArrayList<EmpDeptDto> employees = new ArrayList<>();
		
		// 데이터베이스에서 데이터를 조회하고 employees에 채우는 작업 수행
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employees", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT d.dept_name, e.first_name, e.last_name "
					+ "FROM employees e, dept_emp de, departments d "
					+ "WHERE e.emp_no = de.emp_no and d.dept_no = de.dept_no and "
					+ "	  	 de.to_date = '9999-01-01' and "
					+ "      d.dept_no = ? "
					+ "ORDER BY e.first_name ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptCode);	// SQL의 1번째 ?를 대체할 데이터
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				EmpDeptDto employee = new EmpDeptDto(); 	// 한 행당 Dto 객체 한 개 만들기
				employee.setDeptName(rs.getString(1));
				employee.setFirstName(rs.getString(2));
				employee.setLastName(rs.getString(3));
				
				employees.add(employee);
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return employees;
		
	}

	public List<EmpDeptDto> selectEmployeeWithDepartmentByEmpNo(int empNo) {
		ArrayList<EmpDeptDto> employees = new ArrayList<>();
				
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employees", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT e.first_name, e.last_name, de.from_date, de.to_date, d.dept_name "
					+ "FROM employees e, dept_emp de, departments d "
					+ "WHERE e.emp_no = de.emp_no and d.dept_no = de.dept_no and e.emp_no = ? "
					+ "order by de.from_date ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);	// SQL의 1번째 ?를 대체할 데이터
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				EmpDeptDto employee = new EmpDeptDto(); 	// 한 행당 Dto 객체 한 개 만들기
				employee.setFirstName(rs.getString(1));
				employee.setLastName(rs.getString(2));
				employee.setFromDate(rs.getDate(3));
				employee.setToDate(rs.getDate(4));
				employee.setDeptName(rs.getString(5));
				
				employees.add(employee);
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return employees;
	}

	public List<EmployeeDto> selectManagerByDeptNo(String deptCode7) {
		ArrayList<EmployeeDto> employees = new ArrayList<>();
		
		// 데이터베이스에서 데이터를 조회하고 employees에 채우는 작업 수행
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employees", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT e.emp_no, e.birth_date, e.first_name, e.last_name, e.gender, e.hire_date " +
					"FROM employees e, dept_manager dm, departments d "
					+ "WHERE e.emp_no = dm.emp_no and d.dept_no = dm.dept_no and "
					+ "      dm.to_date = '9999-01-01' and "
					+ "      d.dept_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptCode7);	// SQL의 1번째 ?를 대체할 데이터
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				EmployeeDto employee = new EmployeeDto(); 	// 한 행당 Dto 객체 한 개 만들기
				employee.setEmpNo(rs.getInt(1));			// 현재 행의 1번째 열 데이터를 정수로 읽기
				employee.setBirthDate(rs.getDate(2));		// 현재 행의 2번째 열 데이터를 날짜로 읽기
				employee.setFirstName(rs.getString(3));
				employee.setLastName(rs.getString(4));
				employee.setGender(rs.getString(5));
				employee.setHireDate(rs.getDate(6));
				
				employees.add(employee);
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return employees;
	}
	

	
}
