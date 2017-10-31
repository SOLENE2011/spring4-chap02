package net.madvirus.spring4.chap02;


// AuthenticationService가 인증에 실패한 기록을 남기기 위한 목적(console출력)
public class AuthFailLogger {

	private int threshold;
	private int failCounts;

	public void insertBadPw(String userId, String inputPw) {
		System.out.printf("AuthFail [type=badpw, userid=%s, pw=%s]\n", userId, inputPw);
		failCounts++;
		if (threshold > 0 && failCounts > threshold) {
			notifyTooManyFail();
			failCounts = 0;
		}
	}

	private void notifyTooManyFail() {
		System.out.println("너무 많은 로그인 시도 실패");
	}

	public void setThreshold(int thresold) {
		this.threshold = thresold;
	}

}
