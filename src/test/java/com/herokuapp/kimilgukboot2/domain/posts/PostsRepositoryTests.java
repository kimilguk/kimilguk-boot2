package com.herokuapp.kimilgukboot2.domain.posts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest//스프링부트에 Junit5=주피터가 포함되어서 구현된다.
class PostsRepositoryTests {
	/*
	 * private: 다른 클래스에서 가로채 사용하지 못하도록(보안)
	 * static: 실행 앱(인스턴스)에서 하나의 값만 저장해서 사용하도록(고정)
	 * final: 현재 클래스에서만 사용(무상속)
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired//클래스를 스프링 빈으로 등록한다.
	PostsRepository postsRepository;
	
	@AfterEach
	void tearDown() throws Exception {
		postsRepository.deleteAll();//test()메소드 이후 Posts테이블 저장소 삭제
	}

	@Test
	void test() {//빌더형식의 엔티티값을 save메소드를 이용해서 DB에 저장 
		postsRepository.save(
				Posts.builder()
				.title("게시글제목2")
				.content("게시글내용2")
				.author("user")
				.build()
				);
		//DB posts 테이블에 저장된 값 출력(아래)
		List<Posts> postsList = postsRepository.findAll();
		Posts posts = postsList.get(0);
		logger.debug("등록된 레코드수:"+postsRepository.count());
		logger.info("디버그:"+posts.toString());
		//위 debug 메소드는 출력이 되지 않는다. 로깅 레벨을 기본값이 info 이다.
		//로깅레벨을 수정하려면, application.properties 파일에서 아래 내용 추가한다.
		//로깅레벨(많은내용 출력순서): debug>info>warn>error
		//logging.level.root=debug
	}

}
