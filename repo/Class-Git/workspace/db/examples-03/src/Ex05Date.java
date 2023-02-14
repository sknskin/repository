import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ex05Date {

	public static void main(String[] args) {
		
		Date now = new Date();
		System.out.println(now);
		
		// 날짜 형식을 원하는 형식의 문자열로 변환하는 도구 ( 반대 변환도 가능 )
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
		String sNow = sdf.format(now); // 날짜 -> 문자열
		System.out.println(sNow);
		
		///////////////////////
		
		Calendar calendar = Calendar.getInstance();
		System.out.printf("%04d-%02d-%02d", calendar.get(Calendar.YEAR),
				 							calendar.get(Calendar.MONTH) + 1,
				 							 calendar.get(Calendar.DAY_OF_MONTH));

	}

}







