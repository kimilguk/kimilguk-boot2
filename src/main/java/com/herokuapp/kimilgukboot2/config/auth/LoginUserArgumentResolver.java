package com.herokuapp.kimilgukboot2.config.auth;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.herokuapp.kimilgukboot2.config.auth.dto.SessionUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor//final 매개변수가 있는 생성자메소드가 자동 생성된다.
@Component//자동으로 스프링 빈으로 등록시키는 애노테이션
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final HttpSession httpSession;
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());//컨트롤러 클래스의 파라미터 타입에 포함되는지 비교
		return isUserClass;//즉, 컨틀로러의 메소드호출시 세션값을 포함하면 true 
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//인증객체생성
		String userName = authentication.getName();//admin, user, guest 중 1개가 들어간다.
		Role userAuthor = null;
		if(httpSession.getAttribute("user")==null && !"anonymousUser".equals(userName)) {//초기인증값이 없을 때 anonymousUser 값을 갖는다
			Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();//권한객체생성
			
			if(roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
				userAuthor = Role.ADMIN;
			} else if(roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
				userAuthor = Role.USER;
			} else {
				userAuthor = Role.GUEST;
			}
			logger.info("여기" + userAuthor.name() + ":" + userAuthor.getKey());
			// 세션 값 저장 핵심(아래)
			httpSession.setAttribute("user", new SessionUser(userName,userAuthor.getKey()));
		}
		//세션값을 가져와서 return한다.
		return httpSession.getAttribute("user");
	}

}
