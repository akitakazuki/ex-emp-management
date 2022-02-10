package jp.co.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class InsertAdministratorForm {
	@NotBlank(message="氏名を入力してください")
	private String name;
	@Email(message="メールアドレスの形式が異なります")
	@Size(min=1,max=127,message="メールアドレスを入力してください")
	private String mailAddress;
	@Size(min=4,max=16,message="パスワードを入力してください")
	private String password;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}
	
	
}
