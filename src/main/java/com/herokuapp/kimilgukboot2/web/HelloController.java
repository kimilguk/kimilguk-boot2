package com.herokuapp.kimilgukboot2.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.herokuapp.kimilgukboot2.web.dto.HelloDto;

@RestController//Json 텍스트값을 반환하는 RestAPI 방식으로 빈이 등록 됨 
public class HelloController {
	@GetMapping("/hello")//웹 요청을 받는 API로 지정
	public String hello() {
		return "Hello";
	}
	
	@GetMapping("/hello/dto")//API로 웹 요청시 데이터를 전송받는 기능을 @RequestParam 애노테이션으로 자동 구현한다. 
	public HelloDto helloDto(@RequestParam("name") String name,@RequestParam("amount") int amount) {
		return new HelloDto(name, amount);//롬복의 자동 생성자 사용
	}
}
