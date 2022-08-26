### 스프링부트와 클라우드활용 강의용 깃 소스 입니다.
#### 무료 헤로쿠클라우드 사용중지에 따른 로컬PC에서 테스트 가능한 작업소스 입니다.
- [교수의 모든 주차_교시별 강의용소스 링크](https://github.com/kimilguk/kimilguk-boot2/branches/all)

#### 13주차 시작 전 아래항목을 2가지를 수정 후 실습을 진행 하시면 됩니다.
-1) src/main/resources/application.properties 파일상단 코드수정

```
중략...
#위 oauth-local.properties(suffix접미사생략)
#spring.profiles.include=db-postgres,oauth-heroku 이부분을 주석 처리 후 아래 내용 추가
spring.profiles.include=db-h2,oauth-local
중략...
```

-2) src/main/resources/import.sql 파일 추가

```
DELETE FROM SIMPLE_USERS
INSERT INTO SIMPLE_USERS VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, '$2a$12$gZ.yvR4/mbCM6ev5L88NOeCh3VeH07K3c5kErn02NwrlsCRyX6fMi', 'ADMIN', 'admin')
INSERT INTO SIMPLE_USERS VALUES (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, '$2a$12$gZ.yvR4/mbCM6ev5L88NOeCh3VeH07K3c5kErn02NwrlsCRyX6fMi', 'USER', 'user')
```