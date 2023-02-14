
public class Ex04Wrapper {

	public static void main(String[] args) {
		
		int a = 10;
		//Object obj = a; 	// 문제점?	// Auto Boxing
		Integer wi = new Integer(a);
		Object obj = wi;
		System.out.println("obj = " + obj);
		
		//int b = (int)obj; 	// 문제점?	// Auto Unboxing
		Integer wi2 = (Integer)obj;
		int b = wi2.intValue();
		System.out.println("b = " + b);
		
		//////////////////////////////////////////
		
		

	}

}
