package com.herokuapp.kimilgukboot2.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.herokuapp.kimilgukboot2.domain.posts.Posts;
import com.herokuapp.kimilgukboot2.domain.posts.PostsRepository;
import com.herokuapp.kimilgukboot2.web.dto.PostsDto;

@AutoConfigureMockMvc//API(Url)경로로 접근하기 위한 샘플 MVC가 필요하다.
@SpringBootTest//스프링부트에 Junit5=주피터가 포함되어서 구현된다.
class PostsApiControllerTests {
	@Autowired//의존성주입(외부클래스를 스프링빈으로 등록 후 객체로생성)
	private MockMvc mockMvc;
	@Autowired
	private PostsRepository postsRepository;

	@Test
	void save() throws Exception {
		PostsDto requestDto = PostsDto.builder()
				.title("게시물제목")
				.content("게시물내용")
				.author("작성자")
				.build();
		mockMvc.perform(
				post("/api/posts/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(requestDto))
				)
		.andExpect(status().isOk());//기준값Ok(200)과 반환값 비교성공시 객체생성.
		List<Posts> postList = postsRepository.findAll();
		System.out.println("디버그" + postList.toString());
		System.out.println("디버그" + postList.get(0).getCreateDate());//등록일시가 null 이 나오는 이유는 Application.java에서 Auditing을 활성화 시키면 해결된다.@EnableJpaAuditing//JPA공통DB필드(변수)사용
	}
	//IndexController 의 postList()메소드 실행테스트(아래)
	@Test
	public void postList() throws Exception {
		mockMvc.perform(
				get("/posts")
				)
		.andExpect(status().isOk())
		.andDo(print());//반환성공 후 body 내용을 출력한다.
	}

}
