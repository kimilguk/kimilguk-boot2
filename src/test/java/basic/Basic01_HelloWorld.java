package basic; //패키지는 폴더와 같고, 개발자가 같은 기능을 모아서 분류하는 기능으로 사용
/**
 * 자바기초의 시작으로 헬로월드를 출력하는 프로그램
 * 데이터 저장용 변수의 종류를 학습한다.
 * 이클립스에서 자동 줄 바꿈 단축기 Alt+Shift+Y
 * @author kimilguk
 * Basic01_HelloWorld.java
 */
public class Basic01_HelloWorld {
	//static final String letter = "static:이미 메모리에 등록됨";
	public static void main(String[] args) {
		//main 메서드는 자바프로그램의 시작지점을 명시한다. 
		System.out.println("헬로월드");
		//변수는 크게 기본형과 참조형(클래스)로 구분한다.
		//기본형은 숫자(int,double,long,float)
		int number1 = 1;//정수
		long number2 = 1l;//큰정수
		double number3 = 1d;//실수(소수점표시)
		float number4 = 1f;//큰실수
		System.out.println("숫자형 : int="+number1+" long="+number2+" double="+number3+" float="+number4);
		//기본형 문자 char, 논리값 boolean
		char english = 'a';//단 따옴표만 가능하다.
		boolean logic = true;
		System.out.println("문자형 : char="+english+" boolean="+logic);
		
		//참조형은 Integer, Long, Double, Float, String, Boolean
		//기본형과 같은 데이터가 들어간다. 차이점은 클래스로 구성되고, null(공백) 값이 허용된다는 점이다. 그래서, null Exception 예외처러가 필요한 프로그램에 많이 사용한다.
		String letter = "abc";//기본상속으로 java.lang.Object 사용하기 때문에 import 설정없이 사용가능하다.
		System.out.println("참조형 : "+letter);
		
		//클래스를 공부하기 전에 키워드에 대해서 학습
		//영역제한(private<default<protected<public)
		//상수처리(static-앱실행시 메모리에 등록됨=객체화됨, final-현재클래스에서 1번만 등록사용):상수란 1번 값이 등록되면,앱 종료 전까지 변하지 않고 사용하겠다고 명시하는 역할.
		//위 상수 키워드가 붙지 않으면 일반변수:변수값을 여러번 수정이 가능하다.
		letter = "변수값 수정";
		System.out.println("참조형 : "+letter);
	}

}
