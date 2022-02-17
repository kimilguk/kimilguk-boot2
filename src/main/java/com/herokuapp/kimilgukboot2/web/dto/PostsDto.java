package com.herokuapp.kimilgukboot2.web.dto;

import java.time.LocalDateTime;

import com.herokuapp.kimilgukboot2.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor//생성자자 메소드를 자동을 생성한다
@Getter @Setter//Get, Set 메소드를 자동으로 생성한다.
public class PostsDto {
	//멤버변수는 Posts 와 일치한다.
	private long id;//게시글 번호
	private String title;//글 제목
    private String content;//글 내용
    private String author;//글 작성자 아이디
    private Long fileId;//첨부파일 번호
    private LocalDateTime createDate;
    private LocalDateTime modifieDate;
    //@서비스에서 저장 시 사용, DB엔티티 값을 Dto객체에 담아서 조회(자동생성코드사용)
	public PostsDto(Posts entity) {//엔티티 클래스를 사용해서 DB레코드를 가져온다  
		//super();상속 클래스가 없기 때문에 주석처리
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.author = entity.getAuthor();
		this.fileId = entity.getFileId();
		this.createDate = entity.getCreateDate();
		this.modifieDate = entity.getModifieDate();
	}
	//@컨트롤러에서 사용 빌더형식으로 임시저장(자동생성코드사용)
	@Builder
	public PostsDto(long id, String title, String content, String author, Long fileId) {
		//super();상속 클래스가 없기 때문에 주석처리
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.fileId = fileId;
	}
    //Posts 엔티티(DB)에 저장
	public Posts toEntity() {
		return Posts.builder()
				.title(title)
				.content(content)
				.author(author)
				.fileId(fileId)
				.build();
	}
	//테스트 출력용 toString() 자동생성코드사용(아래)
	@Override
	public String toString() {
		return "PostsDto [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", fileId="
				+ fileId + ", createDate=" + createDate + ", modifieDate=" + modifieDate + "]";
	}
}
