package com.herokuapp.kimilgukboot2.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN")
		.and()
		.withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
		.and()
		.withUser("guest").password(passwordEncoder().encode("1234")).roles("GUEST");
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
		.defaultSuccessUrl("/");//로그인 성공시 기본 이동 경로지정		
	}
	
}
