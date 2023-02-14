import java.text.SimpleDateFormat;
import java.util.Date;

public class LottoDto {
	
	private int rnd;
	private Date lotteryDate;
	private int no1;
	private int no2;
	private int no3;
	private int no4;
	private int no5;
	private int no6;
	private int bonus;
	
	public int getRnd() {
		return rnd;
	}
	public void setRnd(int rnd) {
		this.rnd = rnd;
	}
	public Date getLotteryDate() {
		return lotteryDate;
	}
	public void setLotteryDate(Date lotteryDate) {
		this.lotteryDate = lotteryDate;
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
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		return String.format("[%4d][%2d][%2d][%2d][%2d][%2d][%2d][B : %2d][%s]", 
							 rnd, no1, no2, no3, no4, no5, no6, bonus, 
							 sdf.format(lotteryDate)); // Date -> "2022.08.27"
		
	}
	

}









