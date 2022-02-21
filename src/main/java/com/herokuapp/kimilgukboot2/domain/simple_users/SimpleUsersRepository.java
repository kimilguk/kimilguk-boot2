package com.herokuapp.kimilgukboot2.domain.simple_users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//JpaRepository<엔티티클래스명, PK타입>을 상속하면 기본CRUD 메소드가 자동생성된다.
public interface SimpleUsersRepository extends JpaRepository<SimpleUsers, Long> {
	//save(), findAll(),수정은 엔티티의 값만수정하면 DB값도 연동된다, delete()
	//게시물 id와는 다르게 회원은 username 으로 단일 값을 가져와야 한다. 그래서, 아래 @Query 사용  
	//@쿼리 애노테이션을 사용할 때 @Param의 변수명과 쿼리의 :username 명이 일치해야 한다.
	@Query("SELECT p FROM SimpleUsers p where p.username = :username")
	SimpleUsers findByName(@Param("username") String username);
	//쿼리가 필요하지 않다. 단, findBy로 시작하고, username 변수와 이름이 동일하면, 쿼리가 자동으로 만들어진다.(스프링-jpa 데이터처리 규칙)
	Page<SimpleUsers> findByUsernameContaining(String keyword, Pageable pageble);
}
