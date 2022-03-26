package basic;
/**
 * 프로그램 오류시 중단없이 에러를 건너띄고 실행시키는 역할을 학습한다.
 * @author kimilguk
 * Basic05_Exception.java
 */
public class Basic05_Exception {

	public static void main(String[] args) {
		String[] stringArray = {"10", "2a", "100"};//2a는 강제로 예외사항 발생하기 위해서
		int indexValue = 0;
		for(int cnt=0;cnt<=2;cnt++) {
			/*//예외처리 Exception처리를 하지 않으면, 반복문 2회전에서 프로그램이 종료됩니다.
			indexValue = Integer.parseInt(stringArray[cnt]);//배열의 문자값을 int형변환
			System.out.println(cnt + " 번째 배열에 저장된 숫자는 = " + indexValue);
			*/
			//아래처럼 예외처리를 하면, 프로그램종료되지 않고, 예외에 대한 메세지만 출력하고, 계속 실행됨.
			try {
				indexValue = Integer.parseInt(stringArray[cnt]);//배열의 문자값을 int형변환
				System.out.println((cnt+1) + " 번째 배열에 저장된 숫자는 = " + indexValue);
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("에러메세지 출력 = " + e.toString());
				System.out.println("배열의 크기가 올바르지 않습니다.");
			}catch(NumberFormatException e) {
				System.out.println("에러메시지 출력 = " + e.toString());
				System.out.println("개발자가 지정한 에러메세지는 숫자 변환시 에러가 발생 되었음.");
			}finally {
				System.out.println("finally는 try~catch 결과와 상관없이 항상 실행");
			}
			
		}//for문 종료

	}

}
