package com.herokuapp.kimilgukboot2.web.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc//API(/hello)경로로 접근하기 위한 샘플 MVC가 필요하다.
@SpringBootTest//스프링부트에 Junit5=주피터가 포함되어서 구현된다.
class HelloDtoTests {

	@Autowired//의존성주입(외부클래스를 스프링빈으로 등록 후 객체로생성)
	private MockMvc mockMvc;
	@Autowired
    private WebApplicationContext ctx;//웹 소스 대상 전체
	
	@BeforeEach//JUnit실행 전처리
	void setUpBeforeClass() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())//입출력데이터 출력해서 확인
                .build();
	}

	@Test
	void helloDto() throws Exception {
		MvcResult rt = mockMvc.perform(
				get("/hello/dto")
				.param("name", "오렌지")
				.param("amount", "100"))
		.andExpect(status().isOk())//expect는 비교대상 값이다
		.andReturn();
		//.andExpect(jsonPath("$.name", is("김일국")));//파라미터확인
		String json = rt.getResponse().getContentAsString();
		HelloDto helloDto = new ObjectMapper().readValue(json, HelloDto.class);//API로 전송된 값을 Dto 클래스에 자동으로 데이터 바인딩 시킨다.
		//결과출력방식: assertThat(비교대상 값).비교매소드(비교기준값)
		assertThat(helloDto.getName()).isEqualTo("오렌지");
		assertThat(helloDto.getAmount()).isEqualTo(100);
	}

}
