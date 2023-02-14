import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ex09File {

	public static void main(String[] args) {

		String path = "C:\\Windows\\System32"; // 문자열에서 \\ -> \
		// System.out.println(path);
		
		File system32 = new File(path); // 지정된 경로의 디렉터리 또는 파일 정보 객체 만들기
		if (!system32.exists()) { // exists() : 존재 여부 확인
			System.out.println("존재하지 않는 경로입니다.");
			return;
		}
		
		if (!system32.isDirectory()) { // isDirectory() : 디렉터리 여부 확인
			System.out.println("디렉터리가 아닙니다.");
			return;
		}
		
		// listFiles() : 지정된 디렉터리 하위의 모든 파일과 디렉터리 목록 반환
		File[] filesAndDirs = system32.listFiles();
		//System.out.println(filesAndDirs.length);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		for (File fileOrDir : filesAndDirs) {
			Date d = new Date(fileOrDir.lastModified()); // tick -> date
			if (fileOrDir.isFile()) {				
				System.out.printf("%19s %10s %,11d %s\n", 
								  sdf.format(d),			// 마지막 수정 일자
								  "",
								  fileOrDir.length(),		// 파일 크기
								  fileOrDir.getName());		// 파일 이름
			} else if (fileOrDir.isDirectory()) {
				System.out.printf("%19s %10s %11s %s\n", 
								  sdf.format(d),			// 마지막 수정 일자
								  "<DIR>",
								  "",		// 파일 크기
								  fileOrDir.getName());		// 파일 이름
			} else {
				System.out.println("알 수 없는 자원입니다.");
			}
		}
		
	}

}














