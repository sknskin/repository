import java.util.Date;

public class EmpDeptDto {
	
	private int empNo;
	private String firstName;
	private String lastName;
	
	private String deptCode;
	private String deptName;
	
	private Date fromDate;
	private Date toDate;
	
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	@Override
	public String toString() {
//		return String.format("[%d][%s][%s][%s][%s][%s][%s]", 
//							 empNo, firstName, lastName, deptCode, deptName, fromDate, toDate);
		
		StringBuilder sb = new StringBuilder(100);
		if (empNo > 0) 			sb.append(String.format("[EMPNO : %d]", empNo));		 
		if (firstName != null) 	sb.append(String.format("[FIRST NAME : %s]", firstName));
		if (lastName != null) 	sb.append(String.format("[LAST NAME : %s]", lastName));
		if (deptCode != null)	sb.append(String.format("[DEPTNO : %s]", deptCode));
		if (deptName != null) 	sb.append(String.format("[DEPT NAME : %s]", deptName));
		if (fromDate != null) 	sb.append(String.format("[FROM : %s]", fromDate));
		if (toDate != null) 	sb.append(String.format("[TO : %s]", toDate));
		
		return sb.toString();
	}

}








