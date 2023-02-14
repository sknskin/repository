import java.util.Date;

public class NumberSet {
	
	private int round;
	private int no1, no2, no3, no4, no5, no6;
	private int avg;
	private Date gameDate;	
	
	public NumberSet() {}	
	public NumberSet(int round, int no1, int no2, int no3, int no4, int no5, int no6, int avg, Date gameDate) {
		this.round = round;
		this.no1 = no1;
		this.no2 = no2;
		this.no3 = no3;
		this.no4 = no4;
		this.no5 = no5;
		this.no6 = no6;
		this.avg = avg;
		this.gameDate = gameDate;
	}
	public NumberSet(int round, int[] numbers, Date gameDate) {
		this.round = round;
		this.no1 = numbers[0];
		this.no2 = numbers[1];
		this.no3 = numbers[2];
		this.no4 = numbers[3];
		this.no5 = numbers[4];
		this.no6 = numbers[5];
		int sum = 0;
		for (int number : numbers) {
			sum += number;
		}
		this.avg = sum / numbers.length;
		this.gameDate = gameDate;
	}

	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getNo1() {
		return no1;
	}
	public void setNo1(int no1) {
		this.no1 = no1;
	}
	public int getNo2() {
		return no2;
	}
	public void setNo2(int no2) {
		this.no2 = no2;
	}
	public int getNo3() {
		return no3;
	}
	public void setNo3(int no3) {
		this.no3 = no3;
	}
	public int getNo4() {
		return no4;
	}
	public void setNo4(int no4) {
		this.no4 = no4;
	}
	public int getNo5() {
		return no5;
	}
	public void setNo5(int no5) {
		this.no5 = no5;
	}
	public int getNo6() {
		return no6;
	}
	public void setNo6(int no6) {
		this.no6 = no6;
	}
	public int getAvg() {
		return avg;
	}
	public void setAvg(int avg) {
		this.avg = avg;
	}
	public Date getGameDate() {
		return gameDate;
	}
	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}
	
	@Override
	public String toString() {
		return String.format("[%4d ROUND][%2d][%2d][%2d][%2d][%2d][%2d][AVG:%2d][%s]", 
					  		 round, no1, no2, no3, no4, no5, no6, avg, gameDate);
	}

}










