package com.herokuapp.kimilgukboot2.domain.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//JpaRepository<엔티티클래스명, PK타입>을 상속하면 기본CRUD 메소드가 자동생성된다
public interface ManyFileRepository extends JpaRepository<ManyFile, Long> {
	//save(), findAll(),수정은 엔티티의 값만수정하면 DB값도 연동된다, delete()
	//단, 관계형 쿼리는 기본 CRUD메소드에 없으므로, 개발자가 아래처럼 생성해야 한다.
	@Query(value = "SELECT * FROM many_file p where p.posts_id = :posts_id ORDER BY p.id DESC", nativeQuery = true)
    List<ManyFile> fileAllDesc(@Param("posts_id") Long id);
	//native쿼리는 위 처럼 파라미터가 필요한 쿼리가 사용가능하고, 주로 다중 파라미터값을 쿼리에 바인딩 할 때 직관적인 코딩으로 많이 사용한다.
}
