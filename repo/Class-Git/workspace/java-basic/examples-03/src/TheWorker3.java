
// Thread를 만드는 방법 2 : Thread class 상속
public class TheWorker3 extends Thread {
	
	public void doWork() {
		
		System.out.println("2. doWork의 처음");
		System.out.println("Worker : " + Thread.currentThread().getId());
		
		try {
			Thread.sleep(1000 * 10);// 10초간 실행 중지 -> 깨어날 때 예외 발생
		} catch (InterruptedException e) {} 
		
		System.out.println("3. doWork의 끝");
		
	}

	@Override
	public void run() {
		doWork();
	}

}
