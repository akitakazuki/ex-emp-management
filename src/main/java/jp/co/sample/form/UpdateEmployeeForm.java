package jp.co.sample.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateEmployeeForm {

	private String id ;
	@NotBlank(message="扶養人数を入力してください")
	private String dependentsCount ;
	
	public int getIntDentsCount() {
		return Integer.parseInt(dependentsCount);
	}
	
	public int getIntId() {
		return Integer.parseInt(id);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
	
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}
	

	
}
