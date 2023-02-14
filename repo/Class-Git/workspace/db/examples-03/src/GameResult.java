import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameResult implements Serializable {
	
	private static int nextNo = 1; // 번호표 기계 역할 ( 모든 인스턴스가 공유하는 유일한 변수 )
	public static int getNextNo() {
		return nextNo;
	}
	public static void setNextNo(int nextNo) {
		GameResult.nextNo = nextNo;
	}

	private int no;
	private Date gameDate;
	private int comNo;
	private int userNo;
	private String result;

	public GameResult() { // 생성자 메서드 : new GameResult() 할때 자동 호출
		this.no = nextNo;
		nextNo++;
	}
	public GameResult(int no, Date gameDate, int comNo, int userNo, String result) {
		this(); // this() : 같은 클래스에 있는 전달인자 없는 생성자 메서드 호출
		// this.no = no;
		this.gameDate = gameDate;
		this.comNo = comNo;
		this.userNo = userNo;
		this.result = result;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Date getGameDate() {
		return gameDate;
	}
	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}
	public int getComNo() {
		return comNo;
	}
	public void setComNo(int comNo) {
		this.comNo = comNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		
		// 날짜(Date) 형식의 데이터를 특정한 형식의 문자열로 변경하는 도구 (역방향도 가능)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
		
		return String.format("[%05d][%s][%d][%d][%s]", 
							 no, sdf.format(gameDate), comNo, userNo, result);
		
	}

}

















