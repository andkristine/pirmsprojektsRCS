package lv.accenture.bootcamp.spring;

public class User {
	
	
	private String fullName;
	private String number;
	private String email;

	public User(String fullName, String number, String email) {
		this.fullName = fullName;
		this.number = number;
		this.email = email;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}
