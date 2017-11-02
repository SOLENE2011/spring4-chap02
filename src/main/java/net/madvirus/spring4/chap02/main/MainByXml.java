package net.madvirus.spring4.chap02.main;

import net.madvirus.spring4.chap02.AuthException;
import net.madvirus.spring4.chap02.AuthenticationService;
import net.madvirus.spring4.chap02.PasswordChangeService;
import net.madvirus.spring4.chap02.UserNotFoundException;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainByXml {

	public static void main(String[] args) {
		//스프링 컨테이너 생성하기
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:config.xml");
				// classpath에 위치한 config.xml파일을 스프링 설정으로 사용하겠다.
		
		AuthenticationService authSvc = ctx.getBean("authenticationService", AuthenticationService.class);
		// getBean() 이름과, 타입
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "1111");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "11111");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "111111");
		try {
			authSvc.authenticate("bkchoi2", "1111");
		} catch (UserNotFoundException ex) {
		}
		authSvc.authenticate("bkchoi", "1234");
		PasswordChangeService pwChgSvc = ctx.getBean(PasswordChangeService.class);
		// 빈의 이름을 지정하지 않고 타입만 전달하면 
		// 타입에 해당하는 빈을 구해서 return한다.
		
		pwChgSvc.changePassword("bkchoi", "1234", "5678");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "1234");
		authSvc.authenticate("bkchoi", "5678");
		ctx.close();
	}

	private static void runAuthAndCatchAuthEx(
			AuthenticationService authSvc, String userId, String password) {
		try {
			authSvc.authenticate(userId, password);
		} catch (AuthException ex) {
		}
	}
}
