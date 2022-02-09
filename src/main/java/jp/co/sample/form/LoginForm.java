package jp.co.sample.form;

import javax.validation.constraints.Size;

public class LoginForm {
	@Size(min=1,max=127,message="メールアドレスを入力してください")
	private String mailAddress;
	@Size(min=4,max=16,message="4~16文字以内でパスワードを入力してください")
	private String password;
	
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
	}
	
	
}
