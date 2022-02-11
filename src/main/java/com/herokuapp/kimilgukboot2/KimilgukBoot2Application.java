package com.herokuapp.kimilgukboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//스프링에서 @애노테이션은 인터페이스 기반으로 클래스에 특별한 의미를 부여하거나, 기능을 주입한다.
//@EnableJpaAuditing//JPA공통DB필드(변수)사용 Config 패키지로 분리한다.
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class KimilgukBoot2Application {
	//현재 앱의 진입 메소드가 자동 생성 된다.
	public static void main(String[] args) {
		SpringApplication.run(KimilgukBoot2Application.class, args);
	}

}
