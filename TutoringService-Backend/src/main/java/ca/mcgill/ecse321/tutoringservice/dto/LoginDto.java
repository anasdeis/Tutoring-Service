package ca.mcgill.ecse321.tutoringservice.dto;

public class LoginDto{
	private String userName;
	private String password;

	public void setUserName(String value) {
		this.userName = value;
	}
	public String getUserName() {
		return this.userName;
	}

	public void setPassword(String value) {
		this.password = value;
	}
	public String getPassword() {
		return this.password;
	}
}
