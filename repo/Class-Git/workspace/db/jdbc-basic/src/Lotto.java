import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Lotto {
	
	private Scanner scanner = new Scanner(System.in);
	
	private ArrayList<int[]> numbersList = new ArrayList<>(); // 뽑힌 번호 배열을 저장하는 컬렉션 변수
	
	public void doStart() {

		String selection = ""; // 사용자 메뉴 입력 변수
		int[] numbers = new int[6]; // 뽑은 당첨 예상 번호를 저장할 배열 변수
		int sum = 0, average = 0; // 뽑은 숫자의 합과 평균을 저장할 변수

		MainLoop: // 레이블 구문 : 코드의 위치에 이름 붙이기
		while (true) {

			// 1. 메뉴 표시 + 사용자 입력
			selection = selectMenu();

			System.out.println();

			switch (selection) {
			case "1": // 3-1. 사용자가 1을 입력한 경우
				do {
					// 3-1-1. 번호 추출 (1 ~ 45, 중복X, Random, 6개)
					numbers = selectBasicNumbers();

					// 3-1-2. 평균 계산
					average = calculateMean(numbers);

				} while (average < 20 || average > 26);// 3. 평균 검사 (20 ~ 26, 실패하면 1부터 다시)

				// 4. 출력
				showNumbers(numbers, average);
				
				numbersList.add(numbers); 	// 뽑힌 로또 번호 세트(6개) 목록에 추가
								
				break;
				
			case "2":
				System.out.println("[ 당첨 예상번호 목록 ]");
				
				for (int[] numbers2 : numbersList) {
					// System.out.println(numbers2.toString()); // 배열의 toString() 사용 -> 클래스이름@hashCode
					for (int number : numbers2) {
						System.out.printf("[%2d]", number);
					}
					System.out.println();
				}
				
				break;
				
			case "3": // 당첨 번호 등록
				// 1. 기존 저장된 번호 삭제
				LottoDao dao = new LottoDao();
				dao.deleteAll();
				
				// 2. 새 번호 저장
				List<LottoDto> dataSet = loadLottoFromFile();
				dao.insertMany(dataSet);
				
				System.out.println("과거 당첨 번호를 데이터베이스에 저장했습니다.");
				break;
				
			case "4": // 당첨 번호 조회
				// 1. 회차 입력
				System.out.print("회차 번호 : ");
				int round = scanner.nextInt();
				scanner.nextLine(); // nextLine()과 함께 사용하는 경우 버퍼 정리 필요
				// 2. 회차로 번호 조회
				LottoDao dao2 = new LottoDao();
				List<LottoDto> result = dao2.selectLottoByRnd(round);				
				// 3. 출력
				if (result.size() == 0) {
					System.out.println("해당 회차의 데이터가 없습니다.");
				} else {
					for (LottoDto l : result) {
						System.out.println(l);
					}
				}
				break;

			case "9": // 3-2. 사용자가 9를 입력한 경우
				System.out.println("행운을 빕니다....");
				break MainLoop; // 1. 프로그램 종료 (while 종료)

			default:
				System.out.println("지원하지 않는 명령입니다.");
				break;
			}

		}

	}

	/**
	 * 사용자에게 선택 가능한 작업의 목록을 보여주는 메서드
	 */
	public String selectMenu() {
		System.out.println();
		System.out.println("******************************");
		System.out.println("* 1. 번호 뽑기.");
		System.out.println("* 2. 목록 보기.");
		System.out.println("* 3. 당첨 번호 데이터베이스 저장.");
		System.out.println("* 4. 당첨 번호 조회.");
		System.out.println("* 9. 종료.");
		System.out.println("******************************");

		System.out.print("원하는 작업 번호를 입력하세요 : ");
		String selection = scanner.nextLine();
		return selection;
	}

	/**
	 * 로또 규칙에 맞는 랜덤 번호를 추출하는 메서드
	 * 
	 * @return 추출된 로또 번호, 정수 배열
	 */
	public int[] selectBasicNumbers() {
		int[] numbers = new int[6];
		for (int i = 0; i < 6; i = i + 1) {
			numbers[i] = (int) (Math.random() * 45) + 1; // 1 ~ 45
			for (int j = 0; j < i; j = j + 1) {
				if (numbers[i] == numbers[j]) {
					// i = i - 1;//i의 위치를 감소시켜서 증감식의 증가 효과를 제거
					i = -1; // 처음부터 다시 뽑기
				}
			}
		}
		return numbers;
	}

	// 3-1-2번 평균 계산 영역의 코드를 메서드로 만들고 호출로 대체
	public int calculateMean(int[] numbers) {
		int sum = 0; // 각 숫자를 더하기 전에 기존에 저장된 값을 제거
		for (int i = 0; i < 6; i = i + 1) {
			sum = sum + numbers[i];
		}
		int average = sum / 6;

		return average;
	}

	// 4번 출력 영역의 코드를 메서드로 만들고 호출로 대체
	public void showNumbers(int[] numbers, int average) {
		System.out.print("SELECTED NUMBERS : ");
		for (int i = 0; i < 6; i = i + 1) {
			System.out.printf("[%2d]", numbers[i]);
		}
		System.out.printf("[AVERAGE : %2d]\n", average);
	}
	
	public List<LottoDto> loadLottoFromFile() {
		FileInputStream fis = null;		// 파일 데이터 읽기 기능 제공
		InputStreamReader isr = null;	// byte[] -> char[] (String)으로 변환 기능 제공
		BufferedReader br = null; 		// 한 행 단위로 텍스트 파일 읽기 기능 제공
		ArrayList<LottoDto> dataSet = new ArrayList<>(); // 읽은 데이터 저장 변수
		try {
			fis = new FileInputStream("winning-numbers.csv");
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); // 날짜 <-> 특정형식문자열
			while (true) {
				String line = br.readLine();
				if (line == null) break; // EOF ( End of File )
				
				String[] row = line.split(","); // "a,b,c" -> ["a", "b", "c"]
				
				LottoDto l = new LottoDto();
				l.setRnd(Integer.parseInt(row[0]));
				l.setLotteryDate(sdf.parse(row[1])); // "2022.08.27" -> Date
				l.setNo1(Integer.parseInt(row[2]));
				l.setNo2(Integer.parseInt(row[3]));
				l.setNo3(Integer.parseInt(row[4]));
				l.setNo4(Integer.parseInt(row[5]));
				l.setNo5(Integer.parseInt(row[6]));
				l.setNo6(Integer.parseInt(row[7]));
				l.setBonus(Integer.parseInt(row[8]));
				
				dataSet.add(l);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { br.close(); } catch (Exception ex) {}
			try { isr.close(); } catch (Exception ex) {}
			try { fis.close(); } catch (Exception ex) {}
		}
		
		return dataSet;
	}
	
	public static void main(String[] args) {
		
		Lotto lotto = new Lotto();
		lotto.doStart();
		
	}
}













