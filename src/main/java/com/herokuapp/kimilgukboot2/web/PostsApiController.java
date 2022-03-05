package com.herokuapp.kimilgukboot2.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.herokuapp.kimilgukboot2.config.auth.LoginUser;
import com.herokuapp.kimilgukboot2.config.auth.dto.SessionUser;
import com.herokuapp.kimilgukboot2.service.posts.PostsService;
import com.herokuapp.kimilgukboot2.web.dto.PostsDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor//final 매개변수가 있는 생성자메소드가 자동 생성된다
@RestController//반환값으로 json텍스트 내용부분만(body) 전송된다.
public class PostsApiController {
	//로그 출력 객체생성
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final PostsService postsService;//생성자로 주입
	//포스트매핑은 페이지 폼에서만 접근가능(보안)
	@PostMapping("/api/posts/save")//저장:Create
	public Long save(@RequestBody PostsDto requestDto,@LoginUser SessionUser user) {
		requestDto.setAuthor(user.getName());//해킹방지용 강제입력
		return postsService.save(requestDto);
	}
	//겟매핑은 페이지 URL에서만 접근가능(비보안)
	@GetMapping("/api/posts/{id}")//읽기:1개게시물 Read
	public PostsDto postOne(@PathVariable Long id) {
		return postsService.postsOne(id);
	}
	
	//전체게시물 읽기는 @RestController가 아닌 일반 @Controller에서 디자인 html 뷰파일과 함께 처리할 예정이다.
	
	//풋매핑은 페이지 폼에서만 접근가능(보안)
	@PutMapping("/api/posts/{id}")//수정:Update
	public Long update(@PathVariable Long id, @RequestBody PostsDto requestDto,@LoginUser SessionUser user) {
		requestDto.setAuthor(user.getName());//해킹방지용 강제입력
		return postsService.update(id, requestDto);
	}
	@DeleteMapping("/api/posts/{id}")
	public Long delete(@PathVariable Long id) {
		postsService.delete(id);
		return id;
	}
}
