package com.herokuapp.kimilgukboot2.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter//선언된 모든 변수의 get메소드를 자동생성
@RequiredArgsConstructor//final로 선언된 변수가 객체를 만들때 생성자의 Args(매개변수)가 자동추가된다.
public class HelloDto {
	//멤버변수 선언
	private final String name;
	private final int amount;
	public HelloDto() {//매개변수가 없는 생성자 메소드를 default 생성자라고 한다.
		this.name = "";
		this.amount = 0;
	}
}
