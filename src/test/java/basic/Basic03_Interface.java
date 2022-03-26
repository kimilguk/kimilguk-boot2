package basic;
/**
 * 인터페이스를 상속한 클래스로 객체의 다형성을 구현한다.
 * 인터페이스(스프링에서 주로사용)를 사용하는 목적은 두꺼운책에서 목차를 만드는 것과 똑같은 역할.
 * @author kimilguk
 * Basic03_Interface.java
 */
//인터페이스 실습(아래) 명세표만-메서드명만 있고, 구현내용이 없는 구조 
interface CalcAddSub {//더하기,빼기 명세서
	void add(double dx, double dy);
	void sub(double dx, double dy);
}
interface CalcMulDiv {//곱하기,나누기 명세서
	void mul(double dx, double dy);
	void div(double dx, double dy);
}
//임플리먼트:구현하다는 의미 뒤에 있는 인터페이스를 상속하여 명세만 있는 메서드를 재 사용할 수 있다.
class AllCalc implements CalcAddSub, CalcMulDiv {

	@Override
	public void mul(double dx, double dy) {
		// 구현내용
		System.out.println("곱하기 결과는 " + dx*dy);
	}

	@Override
	public void div(double dx, double dy) {
		// 구현내용
		System.out.println("나누기 결과는 " + dx/dy);
	}

	@Override
	public void add(double dx, double dy) {
		// 구현내용
		System.out.println("더하기 결과는 " + (dx+dy) );
	}

	@Override
	public void sub(double dx, double dy) {
		// 구현내용
		System.out.println("빼기 결과는 " + (dx-dy) );
	}
	
}
public class Basic03_Interface {

	public static void main(String[] args) {
		// 자바프로그램은 항상 main() 진입점에서 실행하시는 겁니다. 단, 웹프로그램은 해당않됨.
		AllCalc allCalc = new AllCalc();//실행 오브젝트변수 allCalc 생성
		allCalc.add(5, 5);//메서드를 호출할때, 5,5 / 10,10 이렇게 매개변수가 고정이 되지 않고,
		allCalc.sub(10, 10);//동적으로 묶이는방식을 동적바인딩이라고 함.
		allCalc.mul(5, 5);
		allCalc.div(5, 5);
	}

}
