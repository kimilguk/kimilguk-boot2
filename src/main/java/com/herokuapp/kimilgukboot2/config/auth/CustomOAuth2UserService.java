package com.herokuapp.kimilgukboot2.config.auth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.herokuapp.kimilgukboot2.config.auth.dto.OAuthAttributes;
import com.herokuapp.kimilgukboot2.config.auth.dto.SessionUser;
import com.herokuapp.kimilgukboot2.domain.posts.PostsRepository;
import com.herokuapp.kimilgukboot2.domain.users.Users;
import com.herokuapp.kimilgukboot2.domain.users.UsersRepository;

import lombok.RequiredArgsConstructor;
//OAuth2UserService<네이버요청객체, 네이버사용자정보>
@RequiredArgsConstructor//final 매개변수가 있는 생성자메소드가 자동 생성된다
@Service//서비스 객체로 만든다
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private final UsersRepository usersRepository;//네이로인증정보를 DB에 저장하기 위해서
	private final HttpSession httpSession;//네아로인증도 로그인상태유지를 위해 세션에 저장하기 위해서
	@Override//네아로 API 요청시 자동 실행된다.
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService delegate = new DefaultOAuth2UserService();//OAuth2 객체생성
		OAuth2User oAuth2User = delegate.loadUser(userRequest);//네아로 사용자정보 객체생성
		String registrationId = userRequest.getClientRegistration().getRegistrationId();//현재 로그인 진행중인 서비스 구분 즉, naver, google, kakao 등 여러 외부 API를 구분하는 용도
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();//현재 로그인 진행중인 서비스의 응답값의 키 이름을 가져온다.(구글은 sub를 자동으로 가져온다. 단, 네이버는 id를 자동으로 가져오지 못한다.)
		OAuthAttributes attributes = OAuthAttributes.of(registrationId,userNameAttributeName,oAuth2User.getAttributes());//자바 클래스의 특징때문에 OAuthAttributes Dto클래스를 객체로 만들지 않고 내부의 of() 메소드를 직접 접근하려면, static 메소드로 변경해야 한다.
		Users user = usersRepository.findByEmail(attributes.getEmail())
				.map(entity->entity.update(attributes.getName(), attributes.getPicture()))
				.orElse(attributes.toEntity());//중복 이메일이라면 update, 그렇지 않다면 insert
		user = usersRepository.save(user);//update 또는 insert 쿼리 실행
		//로그인 유지를 위한 세션 생성처리(아래)
		httpSession.setAttribute("user", new SessionUser(user));//네아로때문에 SessionUser 생성자 메소드를 추가한다.
		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),//네아로 API 로그인 회원도 스프링시큐리티의 로그인 권한을 부여하도록 한다.  
				attributes.getAttributes(),
				attributes.getNameAttributeKey());
	}
	
}
