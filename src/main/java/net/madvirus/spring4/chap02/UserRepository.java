package net.madvirus.spring4.chap02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// User객체를 보관하며 ID 이용해서 User객체 찾는 기능 제공
public class UserRepository {

	private Map<String, User> userMap = new HashMap<>();

	public User findUserById(String id) {
		return userMap.get(id);
	}

	public void setUsers(List<User> users) {
		for (User u : users)
			userMap.put(u.getId(), u);
	}
}
