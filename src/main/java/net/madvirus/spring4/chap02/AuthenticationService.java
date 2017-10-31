package net.madvirus.spring4.chap02;


// 아이디와 암호 입력받아 인증 수행
// UserRepository로부터 User객체 구한 뒤
// User 객체의 matchPassword() 메서드 이용해 일치여부판단.
// 일치하지 않는 경우 AuthFailLogger의 insertBadPw() 메서드 실행해서 실패기록 남김.
public class AuthenticationService {

	private UserRepository userRepository;
	private AuthFailLogger failLogger;

	public AuthInfo authenticate(String id, String password) {
		User user = userRepository.findUserById(id);
		if (user == null)
			throw new UserNotFoundException();

		if (!user.matchPassword(password)) {
			failLogger.insertBadPw(id, password);
			throw new AuthException();
		}

		return new AuthInfo(user.getId());
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setFailLogger(AuthFailLogger failLogger) {
		this.failLogger = failLogger;
	}

}