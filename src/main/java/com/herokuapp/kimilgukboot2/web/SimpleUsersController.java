package com.herokuapp.kimilgukboot2.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.herokuapp.kimilgukboot2.config.auth.LoginUser;
import com.herokuapp.kimilgukboot2.config.auth.dto.SessionUser;
import com.herokuapp.kimilgukboot2.domain.posts.Posts;
import com.herokuapp.kimilgukboot2.domain.simple_users.SimpleUsers;
import com.herokuapp.kimilgukboot2.service.posts.PostsService;
import com.herokuapp.kimilgukboot2.service.simple_users.SimpleUsersService;
import com.herokuapp.kimilgukboot2.util.ScriptUtils;
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
	public String simpleUsersSavePost(HttpServletResponse response,SimpleUsersDto requestDto) throws IOException {
		SimpleUsersDto usersDto = null;
		try {
		usersDto = simpleUsersService.findByName(requestDto.getUsername());
		}catch(Exception e){
		}
		if(usersDto == null) {
			simpleUsersService.save(requestDto);
			ScriptUtils.alertAndMovePage(response, "저장 되었습니다.", "/simple_users/list");
		}else {
			ScriptUtils.alertAndBackPage(response, "중복 아이디가 존재합니다. 아이디를 다시 입력해 주세요.");
		}
		
		return null;//"redirect:/simple_users/list";//저장 후 절대경로로 페이지이동
	}
	@GetMapping("/simple_users/list")//회원목록 디자인보기
	public String simpleUsersList(@RequestParam(value="keyword", defaultValue="")String keyword, @PageableDefault(size=5,sort="id",direction=Sort.Direction.DESC) Pageable pageable, Model model,@LoginUser SessionUser user) {
		if(user != null) {
			//회원등록 가능한 상태인지 확인하는 용도
			model.addAttribute("sessionUserName", user.getName());
		}
		Page<SimpleUsers> usersList = simpleUsersService.usersList(keyword, pageable);
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
	public String simpleUsersUpdatePost(HttpServletResponse response,SimpleUsersDto requestDto) throws IOException {
		simpleUsersService.update(requestDto.getId(), requestDto);
		ScriptUtils.alertAndMovePage(response, "수정 되었습니다.", "/simple_users/update/"+requestDto.getUsername());
		return null;//"redirect:/simple_users/update/"+requestDto.getUsername();
	}
	@PostMapping("/simple_users/delete")//회원삭제 API실행
	public String simpleUsersDelete(HttpServletResponse response,SimpleUsersDto requestDto) throws IOException {
		simpleUsersService.delete(requestDto.getId());
		ScriptUtils.alertAndMovePage(response, "삭제 되었습니다.", "/simple_users/list");
		return null;//"redirect:/simple_users/list";//삭제 후 절대경로로 페이지 이동
	}
}
