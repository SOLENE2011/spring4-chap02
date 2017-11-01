package net.madvirus.spring4.chap02.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.madvirus.spring4.chap02.User;

@Configuration
// (:배열이라는의미) 스프링은 @Configuration 붙은 클래스를 설정정보로 사용한다.
public class Memo {

	@Bean
	// Bean 객체를 생성하는 메서드에 적용된다.
	// Bean 메서드는 final이면 안되고 하위클래스에서 재정의 할수 있도록 하기때문에 private여서는 안됨
	public User user1() {
		return new User("hyoseon", "1234");
	}
	
}
