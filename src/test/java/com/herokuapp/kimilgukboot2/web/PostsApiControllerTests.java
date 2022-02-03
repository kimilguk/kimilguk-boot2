package com.herokuapp.kimilgukboot2.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.herokuapp.kimilgukboot2.domain.posts.PostsRepository;

@AutoConfigureMockMvc//API(Url)경로로 접근하기 위한 샘플 MVC가 필요하다.
@SpringBootTest//스프링부트에 Junit5=주피터가 포함되어서 구현된다.
class PostsApiControllerTests {
	@Autowired//의존성주입(외부클래스를 스프링빈으로 등록 후 객체로생성)
	private MockMvc mockMvc;
	@Autowired
    private WebApplicationContext ctx;//웹 소스 대상 전체
	@Autowired
	private PostsRepository postsRepository;

	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())//입출력데이터 출력해서 확인
                .build();
	}

	@Test
	void save() {
		
	}

}
