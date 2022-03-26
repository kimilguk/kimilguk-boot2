package basic;
/**
 * 1개의 앱은 1개의 작업=스레드로 실행된다.
 * 1개의 앱에 여러개의 작업=다중작업을 동시에 실행할 때 다중 스레드를 사용한다.
 * @author kimilguk
 * Basic07_Thread.java
 */
class MyThread extends Thread {
	public MyThread(String szName) {
		super(szName);
	}
	public void run() {//MyThread스레드를 start()호출해야지만, 자동실행되는 메서드 
		for(int cnt=0;cnt<50;cnt++) {
			System.out.print(this.getName() + " ");
		}
	}
}
public class Basic07_Thread {

	public static void main(String[] args) {
		// 메인스레드 시작 진입점 main()
		//(new ThreadApp()).start();//아래 2줄과 이곳 1줄과 같습니다.
		MyThread myThread1 = new MyThread("myThread1");
		MyThread myThread2 = new MyThread("myThread2");
		MyThread myThread3 = new MyThread("myThread3");
		myThread1.start();//스레드1번 마치고, 2번, 3번 순으로 실행(동기통신)되지 않고,
		myThread2.start();//순서가 없이 백그라운드로 실행됨
		myThread3.start();//그래서 동시에 실행되는 것처럼 보인다.
		System.out.println("언제 출력될까요?");//주로 네트웍 비동기 통신프로그램에 사용됨.
	}

}
