package com.herokuapp.kimilgukboot2.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
//JpaRepository<엔티티클래스명, PK타입>을 상속하면 기본CRUD 메소드가 자동생성된다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
	//save(), findAll(),수정은 엔티티의 값만수정하면 DB값도 연동된다, delete() 
}
