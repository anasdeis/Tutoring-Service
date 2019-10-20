package ca.mcgill.ecse321.tutoringservice.dto;

import java.sql.Date;

import ca.mcgill.ecse321.tutoringservice.model.Login;
import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;

public abstract class PersonDto{
	private Integer personId;
	private String firstName;
	private String lastName;
	private Login loginInfo;
	private Date dateOfBirth;
	private String email;
	private Integer phoneNumber;
	private TutoringSystem tutoringSystem;

	public void setPersonId(Integer value) {
		this.personId = value;
	}

	public Integer getPersonId() {
		return this.personId;
	}

	public void setFirstName(String value) {
		this.firstName = value;
	}
	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String value) {
		this.lastName = value;
	}
	public String getLastName() {
		return this.lastName;
	}

	public Login getLoginInfo() {
		return this.loginInfo;
	}

	public void setLoginInfo(Login loginInfo) {
		this.loginInfo = loginInfo;
	}

	public void setDateOfBirth(Date value) {
		this.dateOfBirth = value;
	}
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setEmail(String value) {
		this.email = value;
	}
	public String getEmail() {
		return this.email;
	}

	public void setPhoneNumber(Integer value) {
		this.phoneNumber = value;
	}
	public Integer getPhoneNumber() {
		return this.phoneNumber;
	}

	public TutoringSystem getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystem tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

}
