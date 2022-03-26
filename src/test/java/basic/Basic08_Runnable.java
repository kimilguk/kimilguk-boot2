package basic;
/**
 * 주로 통신 프로그램에서 사용하는 Runnable 인터페이스를 상속한 스레드 학습
 * @author kimilguk
 * Basic08_Runnable.java
 */
public class Basic08_Runnable implements Runnable {

	@Override
	public void run() {
		// 러너블 인터페이스 구현클래스RunnableApp을 start()하면 자동 실행
		System.out.println("러너블은 앱 시작시 자동 실행됩니다.!");
	}

	public static void main(String[] args) {
		// Runnable 인터페이스를 구현하는 내용
		new Thread(new Basic08_Runnable()).start();
	}

}
