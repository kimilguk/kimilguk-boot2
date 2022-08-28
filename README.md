### 스프링부트와 클라우드활용 강의용 깃 소스 입니다.
#### 무료 헤로쿠클라우드 사용중지에 따른 구름ide 클라우드로 배포 가능한 작업소스 입니다.
- [교수의 모든 주차_교시별 강의용소스 링크](https://github.com/kimilguk/kimilguk-boot2/branches/all)

#### 우선 구름ide 클라우드에 로그인 한다.
- 회원가입은 카카오 계정으로 로그인 하면 된다.(아래)

#### 구름ide 클라우드에 배포 전 아래항목을 3가지를 수정 후 진행 하시면 됩니다.
- 단, 구름 ide 클라우드의 스프링부트 스택은 그래들 빌드 버전이 낮기 때문에 업그레이드를 진행한다(아래)

```
- 그래들 업그레이드 설치
터미널창>>  wget https://services.gradle.org/distributions/gradle-7.1.1-bin.zip -P /tmp
터미널창>> sudo unzip -d /opt/gradle /tmp/gradle-7.1.1-bin.zip
- 환경변수 추가(구름 컨테이너 기본설정 창에서 터미널-프로필에 아래내용 추가)
export GRADLE_HOME=/opt/gradle/gradle-7.1.1
export PATH=${GRADLE_HOME}/bin:${PATH}
```
-1) src/main/resources/application.properties 파일상단 코드수정

```
중략...
#spring.profiles.include=db-postgres,oauth-heroku
spring.profiles.include=db-h2,oauth-local
#구름ide 클라우드용 추가(아래) https://kimilguk.tistory.com/806
spring.jpa.open-in-view=false
중락...
#로깅레벨을 축소하면서 DB실행구문(SQL)이 나오지 않아서 추가-단, 구름ide 클라우드에서는 false로 한다(아래)
spring.jpa.show_sql=false
중략...
```

-2) src/main/resources/import.sql 파일 추가. 서버를 재시작 할 때마다 자동으로 초기화 된다.

```
DELETE FROM SIMPLE_USERS
INSERT INTO SIMPLE_USERS VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, '$2a$12$gZ.yvR4/mbCM6ev5L88NOeCh3VeH07K3c5kErn02NwrlsCRyX6fMi', 'ADMIN', 'admin')
INSERT INTO SIMPLE_USERS VALUES (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, '$2a$12$gZ.yvR4/mbCM6ev5L88NOeCh3VeH07K3c5kErn02NwrlsCRyX6fMi', 'USER', 'user')
```

-3) 프로젝트 루트 최상위의 build.gradle 에서 하단 내용 추가

```
중략...
sourceCompatibility = '8'//지정한 자바버전. 단, 구름 클라우드에서는 11을 8로 변경한다.
중략...
//단, 구름ide에서는 배포시 테스트는 제외하도록 지정(아래)
test {
    exclude '**/*'
}
//단, 구름ide에서는 KimilgukBoot2Application-plain.jar 파일까지 자동으로 생성되기 때문에 생성되지 않도록 처리한다(아래)
jar {
	enabled = false
}
```