package com.herokuapp.kimilgukboot2.config.auth;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired//스프링부트의 DI(Dependency Injection 의존성주입)기능 사용
	private DataSource dataSource;//jdbc를 사용하는데 필요한 객체 생성
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.rolePrefix("ROLE_")//현재 테이블에 저장될 때 ADMIN, USER로 저장되기 때문에 생략된 부분 추가
			.usersByUsernameQuery("select username, password, enabled from simple_users where username = ?")//로그인 인증 쿼리
			.authoritiesByUsernameQuery("select username, role from simple_users where username = ?");//DB에서 권한 가져오는 쿼리
		/* 메모리용 인증과 권한 코딩
		 * auth.inMemoryAuthentication()
		 * .withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN")
		 * .and()
		 * .withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
		 * .and()
		 * .withUser("guest").password(passwordEncoder().encode("1234")).roles("GUEST");
		 */
	}
	
	//PasswordEndoder 메소드(비번암호화) 필수
	@Bean//클래스 뿐 아니라 메소드도 빈 애노테이션을 붙여서 스프링에서 사용가능한 클래스로 등록할 수 있다.
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http//아래 메소드의 실행 순서는 역순으로 진행된다.
		.csrf().disable()//Cross-site Request Forgery 취약점 사용안함
		.headers().frameOptions().disable().and()//h2-console 에서 iframe 하기 때문에
		.authorizeHttpRequests()//http 요청으로 권한설정을 시작
		.antMatchers("/posts/read/**").permitAll()
		.antMatchers("/api/**","/posts/**").hasAnyRole(Role.USER.name(),Role.ADMIN.name())
		.antMatchers("/**").permitAll()//우선 모든 경로는 권한 허용한다.
		.anyRequest().authenticated()//위 경로 Url 설정 이외의 모든 접속에 로그인 인증을 사용하겠다
		.and()
		.logout()//스프링 시큐리티에 내장된 로그아웃 사용 내장된 Url: /logout
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true)//로그아웃시 생성된 모든 세션 지우기
		.and()
		.formLogin()//스프링 시큐리티에 내장된 로그인 폼 사용 내장된 Url: /login
		.defaultSuccessUrl("/")//로그인 성공시 기본 이동 경로지정	
		.and()
		.oauth2Login()//OAuth2.0설정 시작명시
		.userInfoEndpoint()//네아로 로그인 성공 후 사용자 정보 가져오기 설정 자동생성
		.userService(null);//customOAuth2UserService,로그인 성공시 세션을 저장코딩을 추가할 OAuth2UserServic 시스템 클래스를 상속한 @서비스 만들예정 
	}
	
}
