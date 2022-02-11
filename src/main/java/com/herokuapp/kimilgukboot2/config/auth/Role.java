package com.herokuapp.kimilgukboot2.config.auth;

import com.herokuapp.kimilgukboot2.domain.posts.PostsRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter//엔티티 출력이 가능하게 구현된다
@RequiredArgsConstructor//final 매개변수가 있는 생성자메소드가 자동 생성된다
public enum Role {
	GUEST("ROLE_GUEST","손님"),
	USER("ROLE_USER","일반사용자"),
	ADMIN("ROLE_ADMIN","관리자");
	private final String key;
	private final String title;
}
