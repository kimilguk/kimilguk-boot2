package com.herokuapp.kimilgukboot2.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter//엔티티 출력이 가능하게 구현된다
@MappedSuperclass//부모 엔티티 도메인을 자식클래스에도 포함시킨다 
//리스너로 감시하다가 엔티티등록,수정시 값이 자동으로 업데이트(자동화코딩)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
	@CreatedDate//엔티티가 저장될때 시간이 자동저장되게 구현한다
	private LocalDateTime createDate;
	
	@LastModifiedDate//엔티티가 수정될때 시간이 자동저장되게 구현한다
	private LocalDateTime modifieDate;
}
