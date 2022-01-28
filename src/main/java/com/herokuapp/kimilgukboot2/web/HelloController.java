package com.herokuapp.kimilgukboot2.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//Json 텍스트값을 반환하는 RestAPI 방식으로 빈이 등록 됨 
public class HelloController {
	@GetMapping("/hello")//웹 요청을 받는 API로 지정
	public String hello() {
		return "Hello";
	}
}
