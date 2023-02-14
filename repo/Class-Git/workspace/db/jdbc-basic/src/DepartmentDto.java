
public class DepartmentDto {
	
	private String deptNo;
	private String deptName;
	
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Override
	public String toString() {
		return String.format("[%s][%s]", deptNo, deptName);
	}

	
}






