package com.herokuapp.kimilgukboot2.domain.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//JpaRepository<엔티티클래스명, PK타입>을 상속하면 기본CRUD 메소드가 자동생성된다.
public interface UsersRepository extends JpaRepository<Users, Long> {
	//findBy~변수명은 자동으로 select 쿼리를 생성해 준다.
	Optional<Users> findByEmail(String email);//신규등록-업데이트 구분용
	//Optinal<객체>는 객체가 null 일때 에러를 발생시키는 것을 방지하는 자바8버전 부터 만들어진 클래스 이다.
}
