package com.herokuapp.kimilgukboot2.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.herokuapp.kimilgukboot2.config.auth.LoginUser;
import com.herokuapp.kimilgukboot2.config.auth.dto.SessionUser;
import com.herokuapp.kimilgukboot2.domain.posts.Posts;
import com.herokuapp.kimilgukboot2.domain.simple_users.SimpleUsers;
import com.herokuapp.kimilgukboot2.service.posts.PostsService;
import com.herokuapp.kimilgukboot2.service.simple_users.SimpleUsersService;
import com.herokuapp.kimilgukboot2.web.dto.SimpleUsersDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor//final 매개변수가 있는 생성자메소드가 자동 생성된다
@Controller//일반컨트롤러는 반환값으로 출력할 페이지를 지정한다
public class SimpleUsersController {
	private final SimpleUsersService simpleUsersService;
	@GetMapping("/simple_users/save")//회원생성 디자인보기
	public String simpleUsersSave(Model model,@LoginUser SessionUser user) {
		if(user != null) {
			//회원등록 가능한 상태인지 확인하는 용도
			model.addAttribute("sessionUserName", user.getName());
		}
		return "simple_users/save";//save.mustache 생략
	}
	@PostMapping("/simple_users/save")//회원생성 API실행
	public String simpleUsersSavePost(SimpleUsersDto requestDto) {
		simpleUsersService.save(requestDto);
		return "redirect:/simple_users/list";//저장 후 절대경로로 페이지이동
	}
	@GetMapping("/simple_users/list")//회원목록 디자인보기
	public String simpleUsersList(@PageableDefault(size=5,sort="id",direction=Sort.Direction.DESC) Pageable pageable, Model model,@LoginUser SessionUser user) {
		if(user != null) {
			//회원등록 가능한 상태인지 확인하는 용도
			model.addAttribute("sessionUserName", user.getName());
		}
		Page<SimpleUsers> usersList = simpleUsersService.usersList(pageable);
		model.addAttribute("usersList", usersList);//회원목록 5개
		model.addAttribute("currPage", usersList.getPageable().getPageNumber());//현재페이지번호
		model.addAttribute("pageIndex", usersList.getTotalPages());//전체페이지개수
		model.addAttribute("prevCheck", usersList.hasPrevious());//이전페이지 있는지 체크
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());//이전페이지번호 사용
		model.addAttribute("nextCheck", usersList.hasNext());//다음페이지 있는지 체크
		model.addAttribute("next", pageable.next().getPageNumber());//다음페이지번호 사용
		return "simple_users/list";
	}
	@GetMapping("/simple_users/update/{username}")//회원상세 디자인보기
	public String simpleUsersUpdate(@PathVariable String username,Model model,@LoginUser SessionUser user) {
		model.addAttribute("simple_user", simpleUsersService.findByName(username));
		return "simple_users/update";
	}
	@PostMapping("/simple_users/update")//회원수정 API실행
	public String simpleUsersUpdatePost(SimpleUsersDto requestDto) {
		simpleUsersService.update(requestDto.getId(), requestDto);
		return "redirect:/simple_users/update/"+requestDto.getUsername();
	}
	@PostMapping("/simple_users/delete")//회원삭제 API실행
	public String simpleUsersDelete(SimpleUsersDto requestDto) {
		simpleUsersService.delete(requestDto.getId());
		return "redirect:/simple_users/list";//삭제 후 절대경로로 페이지 이동
	}
}
