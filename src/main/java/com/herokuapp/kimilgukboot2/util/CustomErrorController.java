package com.herokuapp.kimilgukboot2.util;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
	@RequestMapping(value="/error")
	public String handleError(HttpServletRequest request, Model model) {
		
		//에러코드를 가져온다.
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		//에러코드에 매칭되는 상세정보를 가져온다.
		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
		if(status != null) {
			//이전 페이지로 돌아가는 링크 데이터 생성
			String referer = request.getHeader("Referer");
			//error.mustache 로 보내는 데이터를 model 에 담는다
			model.addAttribute("status", status.toString());
            model.addAttribute("message", httpStatus.getReasonPhrase());//http 응답구문 저장
            model.addAttribute("timestamp", new Date());
            model.addAttribute("prevPage", referer);//에러 이전페이지 저장
		}
		return "/error/error";//error폴더에 error.mustache를 만든다. 
	}
}
