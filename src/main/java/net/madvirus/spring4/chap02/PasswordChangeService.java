package net.madvirus.spring4.chap02;


// AuthenticationService와 마찬가지로
// UserRepository로부터 User객체 구하고 
// User 객체의 changePassword() 통해 암호변경함
public class PasswordChangeService {

	private UserRepository userRepository;

	public PasswordChangeService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void changePassword(String userId, String oldPw, String newPw) {
		User user = userRepository.findUserById(userId);
		if (user == null)
			throw new UserNotFoundException();

		user.changePassword(oldPw, newPw);
	}

}
