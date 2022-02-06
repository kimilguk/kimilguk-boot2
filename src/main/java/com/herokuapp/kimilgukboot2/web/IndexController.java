package com.herokuapp.kimilgukboot2.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.herokuapp.kimilgukboot2.domain.posts.Posts;
import com.herokuapp.kimilgukboot2.service.posts.PostsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor//final 매개변수가 있는 생성자메소드가 자동 생성된다
@Controller//일반컨트롤러는 반환값으로 출력할 페이지를 지정한다
public class IndexController {
	//로그 출력 객체생성
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final PostsService postsService;//생성자로 주입
	
	@GetMapping("/posts/save")//Url주소와 posts-save.mustache를 매핑시킨다.
	public String postsSave() {
		return "posts/posts-save";
	}
	//@GetMapping("/posts")//전체게시물 Read
	@GetMapping("/")//접근 Api Url을 도메인 루트로 변경한다.
	public String postList(@PageableDefault(size=5,sort="id",direction=Sort.Direction.DESC) Pageable pageable, Model model) {
		Page<Posts> postsList = postsService.postsList(pageable);
		model.addAttribute("postsList", postsList);//게시글목록 5개
		model.addAttribute("currPage", postsList.getPageable().getPageNumber());//현재페이지번호
		model.addAttribute("pageIndex", postsList.getTotalPages());//전체페이지개수
		model.addAttribute("prevCheck", postsList.hasPrevious());//이전페이지 있는지 체크
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());//이전페이지번호 사용
		model.addAttribute("nextCheck", postsList.hasNext());//다음페이지 있는지 체크
		model.addAttribute("next", pageable.next().getPageNumber());//다음페이지번호 사용
		//return "posts/post-list";//출력할 페이지명 posts폴더/post-list.mustache파일(html디자인템플릿)
		//index.mustache 파일이 templates 폴더 최상위에 있기 때문에 경로없이 파일명을 사용한다.
		return "index";//index.mustache에서 확장자가 생략된다.
	}
}
